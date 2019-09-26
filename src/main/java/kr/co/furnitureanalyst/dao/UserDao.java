package kr.co.furnitureanalyst.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import kr.co.furnitureanalyst.vo.TagVo;
import kr.co.furnitureanalyst.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private SqlSession sqlSession;
	Logger log = Logger.getLogger(this.getClass());

	// MongoDB Result
	ArrayList<String> tempVal = new ArrayList<String>();

	// MongoDB JSON Query Result
	Block<Document> printBlock = new Block<Document>(){
		@Override
		public void apply(final Document document){
			tempVal.add(document.toJson());
		}
	};
	
	// GSON PARSE >> tagVo
	public TagVo gsonTagVo(ArrayList<String> tempVal, int i) {
		Gson gson = new Gson();
		return gson.fromJson(tempVal.get(i), TagVo.class);
	}

	// MongoDB Connect
	public MongoCollection<Document> mongoConnect(String collectionName){
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.1.57:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("merbau");
		return database.getCollection(collectionName);
	}
	
	public List<TagVo> findUserStat(String userId, String fieldName, int limit){
		MatchOperation match = Aggregation.match(new Criteria("userId").is(userId));
		UnwindOperation unwind = Aggregation.unwind(fieldName);
		SortOperation sort = Aggregation.sort(Sort.Direction.DESC, fieldName+".tagVal");
		ProjectionOperation project = Aggregation.project(fieldName+".tagName").andExclude("_id").andInclude("$" + fieldName + ".tagName").andInclude("$" + fieldName + ".tagVal");
		if(limit == 0){
			Aggregation aggregation = Aggregation.newAggregation(match, unwind, sort, project);
			AggregationResults<TagVo> result = mongoTemplate.aggregate(aggregation, "userStat", TagVo.class);
			return result.getMappedResults();
		}else{
			LimitOperation limitVal = Aggregation.limit(limit);
			Aggregation aggregation = Aggregation.newAggregation(match, unwind, sort, project, limitVal);
			AggregationResults<TagVo> result = mongoTemplate.aggregate(aggregation, "userStat", TagVo.class);
			return result.getMappedResults();
		}
	}

	public void join(UserVo userVo) {
		sqlSession.insert("user.insertUser", userVo);
		// mongoTemplate.insert("userVo);
	}

	public UserVo getUser(UserVo userVo) {
		return sqlSession.selectOne("user.selectUserByIdPw", userVo);
	}

	public void mongoJoinUpdateTag(String tagName, String userId){
		Criteria criteria = new Criteria("userId");
		criteria.is(userId);
		Query query = new Query(criteria);
		Update update = new Update();
		update.addToSet("userLikeTag", new Document("tagName" , tagName).append("tagVal", 10));
		mongoTemplate.updateFirst(query, update, "userStat");
		
	}
	
	public void mongoJoin(UserVo userMongoVo){
		mongoTemplate.insert(userMongoVo, "userStat");
	}

	public void updatePassword(UserVo userVo){
		sqlSession.update("user.updatePassword", userVo);
	}

	public UserVo selectUser(String userId) {
		// TODO Auto-generated method stub
		// 몽고디비 자동 '-' ->대문자 실험
		return sqlSession.selectOne("user.selectUserByUserId", userId);
	}

	public Map findUserStat(String userId){
		System.out.println("userDao finduserStat 메소드 진입");
		Criteria criteria = new Criteria("userId");
		// criteria.is(userId);
		criteria.is(userId);
		Query query = new Query(criteria);

		log.debug(mongoTemplate.findOne(query, Map.class, "userStat").toString());
		return mongoTemplate.findOne(query, Map.class, "userStat");
	}

	public void furnitureListByUserLike(ArrayList userLikeArrList) {
	}

	public List<Map> findFurnitureByFurNameList(ArrayList userLikeArrList) {
		System.out.println("userDao findfurniture메소드 진입");
		Criteria criteria = new Criteria("_id");
		log.debug(userLikeArrList.toString());
		criteria.in(userLikeArrList);

		Query query = new Query(criteria);
		log.debug(Query.query(criteria));

		return mongoTemplate.find(query, Map.class, "fur");
	}
	
	// 유저가 가진 태그 추출
	public List<String> getTagName(String id, String fieldName){
		MongoCollection<Document> collection = mongoConnect("userStat");
		collection.aggregate(
			Arrays.asList(
				Aggregates.match(Filters.eq("userId",id)),
				Aggregates.unwind("$"+fieldName),
				Aggregates.sort(Filters.eq(fieldName,1)),
				Aggregates.project(
					Projections.fields(
						Projections.excludeId(),
						Projections.computed("tagName", "$"+fieldName+".tagName"),
						Projections.computed("tagVal","$"+ fieldName + ".tagVal"))))
			).forEach(printBlock);
		
		List<String> tagList = new ArrayList<String>();
		for(int i=0; i<tempVal.size(); i++) {
			tagList.add(gsonTagVo(tempVal, i).getTagName());
		}
		tempVal.clear();
		return tagList;
	}
	
	// 머문 시간에 따른 유저 취향 반영
	public void timeChk(String id, List<String> userTag, List<String> listTag){
		for(int i=0; i<listTag.size(); i++) {
			boolean chk = userTag.contains(listTag.get(i));
			Criteria creteriaFinal = null;
			if(chk == true){
				Criteria criteriaId = new Criteria("_id");
				Criteria criteriaTagName = new Criteria("userLikeTag.tagName");
				criteriaId.is(id);
				criteriaTagName.is(listTag.get(i));
				creteriaFinal = new Criteria().andOperator(criteriaId,criteriaTagName);
				Query query = new Query(creteriaFinal);
				
				Update update = new Update().inc("userLikeTag.$.tagVal", 0.25);
				mongoTemplate.updateFirst(query, update, "userStat");
			}else{
				Criteria criteriaId = new Criteria("_id");
				Criteria criteriaTagName = new Criteria("userLikeTag.tagName");
				criteriaId.is(id); 
				criteriaTagName.ne(listTag.get(i));
				creteriaFinal = new Criteria().andOperator(criteriaId,criteriaTagName);
				Query query = new Query(creteriaFinal);
				Update update = new Update();
				update.addToSet("userLikeTag", new Document("tagName" , listTag.get(i)).append("tagVal", 0.25));
				mongoTemplate.updateFirst(query, update, "userStat");
			}
		}
	}
	
	// 유저 좋아요 확인
	public void userLikeAdd(String userId, String furId){
		Criteria criteria = new Criteria("userId");
		criteria.is(userId);
		Update update = new Update().addToSet("userLikeList", furId);
		Query query = new Query(criteria);
		mongoTemplate.updateFirst(query, update, "userStat");
	}
	
	// 유저 좋아요 취소
	public void userLikeDel(String userId, String furId){
		Criteria criteria = new Criteria("userId");
		criteria.is(userId);
		Update update = new Update().pull("userLikeList", furId);
		Query query = new Query(criteria);
		mongoTemplate.updateFirst(query, update, "userStat");
	}
	
	// userStat 값 추가
	public void userStatUpdate(String userId, List<String> userTag, List<String> furTag, String fieldName, float value){
		for(int i=0; i<furTag.size(); i++){
			boolean chk = userTag.contains(furTag.get(i));
			Criteria creteriaFinal = null;
			if(chk == true){
				// 찾는 대상이 있을 경우
				Criteria criteriaId = new Criteria("userId");
				Criteria criteriaTagName = new Criteria(fieldName + ".tagName");
				criteriaId.is(userId);
				criteriaTagName.is(furTag.get(i));
				creteriaFinal = new Criteria().andOperator(criteriaId,criteriaTagName);
				Query query = new Query(creteriaFinal);
				Update update = new Update().inc(fieldName + ".$.tagVal", value);
				mongoTemplate.updateFirst(query, update, "userStat");
			}else{
				// 찾는 대상이 없을 경우 값 추가
				Criteria criteriaId = new Criteria("userId");
				Criteria criteriaTagName = new Criteria(fieldName + ".tagName");
				criteriaId.is(userId);
				criteriaTagName.ne(furTag.get(i));
				creteriaFinal = new Criteria().andOperator(criteriaId,criteriaTagName);
				Query query = new Query(creteriaFinal);
				Update update = new Update();
				update.addToSet(fieldName, new Document("tagName" , furTag.get(i)).append("tagVal", value));
				mongoTemplate.updateFirst(query, update, "userStat");
			}
		}
	}
	
	// 좋아요 시, 해당 가구에 좋아요 카운트 조절
	public void furLikeEdit(String furId, float value){
		Criteria criteria = new Criteria("_id");
		criteria.is(furId);
		Update update = new Update().inc("furLike", value);
		Query query = new Query(criteria);
		mongoTemplate.updateFirst(query, update, "fur");
	}
}