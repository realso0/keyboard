package kr.co.furnitureanalyst.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import kr.co.furnitureanalyst.vo.FurnitureVo;
import kr.co.furnitureanalyst.vo.TagVo;

@Repository
public class SearchDao {

	@Autowired
	MongoTemplate mongoTemplate;
	Logger log = Logger.getLogger(this.getClass());

	public List<Map> tagSearch(String furType, ArrayList<String> brandList, ArrayList<String> tagList) {
		ArrayList<Criteria> criteriaList = new ArrayList<Criteria>(); // 안들어감 배열로 바꿔줘야됨 몇개들어올지몰라서 일단 어레이리스트로만듦
		Criteria criteriafurType = null;
		// criteriafurType.and("furType").is(furType).and("furBrand").in(brandList);
		if (!furType.isEmpty()) {
			criteriafurType = new Criteria();
			criteriafurType.and("furType").is(furType);
		}
		Criteria criteriafurBrand = null;
		if (!brandList.isEmpty()) {
			criteriafurBrand = new Criteria();
			criteriafurBrand.and("furBrand").in(brandList);
		}

		Criteria creteriaSub = null;
		Criteria creteriaFinal = null;
		
		if (!tagList.isEmpty()) {
			creteriaSub = new Criteria();
			creteriaSub.and("furTag").in(tagList);
		}
		/*if (!tagList.isEmpty()) {

			for (int i = 0; i < tagList.size(); i++) {
				criteriaList.add(Criteria.where("furTag." + tagList.get(i)).exists(true)); // 태그 존재 여부 크리터리아 생성
			}

			Criteria[] criteriaArray = criteriaList.toArray(new Criteria[criteriaList.size()]); // 태그 존재여부 크리터리아 리스트
																								// ->배열

			creteriaSub = new Criteria().orOperator(criteriaArray); // 태그존재여부 크리터리아 or로 묶음
		}*/
		if (criteriafurType != null && criteriafurBrand != null && creteriaSub != null) // 모든태그 종류 눌렀을때
			creteriaFinal = new Criteria().andOperator(criteriafurType, criteriafurBrand, creteriaSub); // 모든 크리터리아 and로
																										// 묶음

		else if (criteriafurType != null && criteriafurBrand != null && creteriaSub == null) // 가구분류,브랜드 눌렀을때
			creteriaFinal = new Criteria().andOperator(criteriafurType, criteriafurBrand); // 모든 크리터리아 and로 묶음

		else if (criteriafurType == null && criteriafurBrand != null && creteriaSub != null) // 브랜드,취향 눌렀을때
			creteriaFinal = new Criteria().andOperator(criteriafurBrand, creteriaSub); // 모든 크리터리아 and로 묶음

		else if (criteriafurType != null && criteriafurBrand == null && creteriaSub != null) // 가구분류,취향 눌렀을때
			creteriaFinal = new Criteria().andOperator(criteriafurType, creteriaSub); // 모든 크리터리아 and로 묶음

		else if (criteriafurType != null && criteriafurBrand == null && creteriaSub == null) // 가구만 눌렀을대
			creteriaFinal = new Criteria().andOperator(criteriafurType); // 모든 크리터리아 and로 묶음

		else if (criteriafurType == null && criteriafurBrand != null && creteriaSub == null) // 브랜드만 눌렀을때
			creteriaFinal = new Criteria().andOperator(criteriafurBrand); // 모든 크리터리아 and로 묶음

		else if (criteriafurType == null && criteriafurBrand == null && creteriaSub != null) // 취향만 눌렀을때
			creteriaFinal = new Criteria().andOperator(creteriaSub); // 모든 크리터리아 and로 묶음

		
		Query query = new Query(creteriaFinal);
		query.limit(9);
		log.debug(Query.query(creteriaFinal));
		return mongoTemplate.find(query, Map.class, "fur");

		// **********************태그 검색 부분을 태그의 점수가 높은녀석으로 정렬후 상위순으로 뽑는거 추구 구현해야됨
		// **********************

	}
	
	public void tagSearchTest(String furType, ArrayList<String> brandList, ArrayList<String> tagList) {
		log.debug(brandList.toString()+"///////////"+tagList.toString());
		ArrayList<Criteria> criteriaList = new ArrayList<Criteria>(); // 안들어감 배열로 바꿔줘야됨 몇개들어올지몰라서 일단 어레이리스트로만듦
		Criteria criteriafurType = null;
		if (!furType.isEmpty()) {
			criteriafurType = new Criteria();
			criteriafurType.and("furType").is(furType);
		}
		Criteria criteriafurBrand = null;
		if (!brandList.isEmpty()) {
			criteriafurBrand = new Criteria();
			criteriafurBrand.and("furBrand").in(brandList);
		}
	}

	public void furnitureUpHit(String furName) {
		Criteria criteria = new Criteria("furName");
		// criteria.is(userId);
		criteria.is(furName);
		Update update = new Update().inc("furViewCount", 1);
		Query query = new Query(criteria);
		mongoTemplate.updateFirst(query, update, "furniture");
	}
	
	public Map furnitureByfurId(String furId) {
		Criteria criteria = new Criteria("_id");
		// criteria.is(userId);
		criteria.is(furId);
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, Map.class,"fur");
	}
	
	public void TestMongotemp() {
		// TODO Auto-generated method stub
		Criteria criteriafurType = new Criteria();
		criteriafurType.and("furType").is("침대");
		Criteria criteria = new Criteria("_id");
		// criteria.is(userId);
		criteria.is("742782779650001310");
		Query query = new Query(criteriafurType);
		List<Map> map= mongoTemplate.find(query, Map.class,"fur");
		log.debug("TestMongotemp:"+map.toString());
	}

	public void updateUserStatByLike(String[] furBrand, String[] furTag, String furType, String userId) {
		/*나중에 고칠때  */
		Criteria criteria = new Criteria("userId");
		// criteria.is(userId);
		criteria.is(userId);
		
		Update update = new Update().inc("userLikeType.$" + furType, 0.25);
		for (int i = 0; i < furBrand.length; i++)
			update.inc("userLikeBrand." + furBrand[i], 0.25);
		for (int i = 0; i < furTag.length; i++)
			update.inc("userLikeTag." + furTag[i], 0.25);
		update.inc("userLikeTag.서유럽", 1);
		Query query = new Query(criteria);
		mongoTemplate.updateFirst(query, update, "userStat");
	}

	// 유저의 userLikeList
	public List<String> userLikeList(String userId){
        MatchOperation match = Aggregation.match(new Criteria("userId").is(userId));
        UnwindOperation unwind = Aggregation.unwind("userLikeList");
        ProjectionOperation project = Aggregation.project("userLikeList");
        Aggregation aggregation = Aggregation.newAggregation(match, unwind, project);
        AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "userStat", Map.class);
        List<Map> resultList = result.getMappedResults();
		List<String> list = new ArrayList<String>();
		for(int i=0; i<resultList.size(); i++){
			list.add((String)resultList.get(i).get("userLikeList"));
		}
		return list;
	}

	public Map<String,String[]> findFurTag() {
		Criteria criteria = new Criteria("_id");
		criteria.is("5b232334994a2503600c4421");
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, Map.class,"furTag");
	};
	
	// detail 페이지 내 maybe
	public List<FurnitureVo> furAssociate(String furType){
		MatchOperation match = Aggregation.match(new Criteria("furType").is(furType));
		LimitOperation limit = Aggregation.limit(6);
		Aggregation aggregation = Aggregation.newAggregation(match, limit);
		AggregationResults<FurnitureVo> result = mongoTemplate.aggregate(aggregation, "fur", FurnitureVo.class);
		return result.getMappedResults();
	}
}