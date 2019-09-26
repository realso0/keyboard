package kr.co.furnitureanalyst.dao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
import com.mongodb.client.model.Sorts;

import kr.co.furnitureanalyst.vo.FurnitureVo;
@Repository
public class MainDao {
	@Autowired
	MongoTemplate mongotemplate;
	Logger log = Logger.getLogger(this.getClass());
	
	// MongoDB Connection
	public MongoCollection<Document> mongoConnect(String collectionName){
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.1.57:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("merbau");
		return database.getCollection(collectionName);
	}
	
	ArrayList<String> gsonList = new ArrayList<String>();
	// MongoDB Result
	ArrayList<String> tempVal = new ArrayList<String>();
	// MongoDB JSON Query Result
	Block<Document> printBlock = new Block<Document>(){
       @Override
       public void apply(final Document document){
    	   tempVal.add(document.toJson());
    	   gsonList.add(document.toJson());
       }
	};
	
	// 유저 취향값 구하기
	public String userPreValue(String userId, int i){
		MongoCollection<Document> collection = mongoConnect("userStat");
		collection.aggregate(
			Arrays.asList(
				Aggregates.match(Filters.eq("userId", userId)),
				Aggregates.unwind("$userLikeTag"),
				Aggregates.sort(Filters.eq("userLikeTag.tagVal", -1)),
				Aggregates.project(Projections.fields(
					Projections.excludeId(),
					Projections.computed("tagName","$userLikeTag.tagName"))),
				Aggregates.limit(i)
			)
		).forEach(printBlock);
		String val = "";
		if(i == 2){
			val = tempVal.get(1).split("\"")[3];
		}else if(i == 1){
			val = tempVal.get(0).split("\"")[3];
		}
		tempVal.clear(); // 값 비워주기
		return val;
	}
	
	// 취향값에 따른 제품 리스트 4개 구하기
	public List<FurnitureVo> userPreSearch(String userPreVal){
		MongoCollection<Document> collection = mongoConnect("fur");
		// 성능에 대한 고려 필요
		collection.aggregate(
			Arrays.asList(
				//Aggregates.match(Filters.eq("furTag.tagName", userPreVal)),
				Aggregates.project(Projections.fields(
					Projections.include("_id"),
					Projections.include("furName"),
					Projections.include("furMinPrice"),
					Projections.include("furImage")
				)),
				Aggregates.sample(3)
			)
		).forEach(printBlock);
		List<FurnitureVo> userPreItem = new ArrayList<FurnitureVo>();
		for(int i=0; i<tempVal.size(); i++){
			userPreItem.add(gsonUserPre(tempVal, i));
		}
		tempVal.clear();
		return userPreItem;
	}
	
	public List<FurnitureVo> furbest(){
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.1.57:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("merbau");
		MongoCollection<Document> collection = database.getCollection("fur");
		collection.aggregate(
				Arrays.asList(
					// ***************** 임시방편
					Aggregates.match(Filters.eq("furBrand", "일룸")),
					Aggregates.match(Filters.eq("furTag", "만족")),
					// ***************** 임시방편 종료
					Aggregates.project(
						Projections.fields(
							Projections.include("furName"),
							Projections.include("furMinPrice"),
							Projections.include("furImage"),
							Projections.computed("fursum", new Document().append("$add", Arrays.asList(
									new Document("$ifNull",Arrays.asList("$furViewCount",0)),
									new Document("$ifNull",Arrays.asList("$furLikeCount",0))
									))))),
					Aggregates.sort(Sorts.descending("fursum")),
					Aggregates.project(Projections.exclude("fursum")),
					Aggregates.limit(10),
					Aggregates.sample(6))).forEach(printBlock);
		List<FurnitureVo> furItem = new ArrayList<FurnitureVo>();
		for(int fursu=0; fursu < gsonList.size(); fursu++) {
			furItem.add(gsonfurList(gsonList, fursu));
		}
		System.out.println("furItem : " + furItem);
		gsonList.clear();
		tempVal.clear(); 
		return furItem;
	}

	public FurnitureVo gsonUserPre(ArrayList<String> tempVal, int i){
		Gson gson = new Gson();
		return gson.fromJson(tempVal.get(i), FurnitureVo.class);
	}
	public FurnitureVo gsonfurList(ArrayList<String> gsonList,int fursu) {
		Gson gson = new Gson();
		return gson.fromJson(gsonList.get(fursu), FurnitureVo.class);
	}

	public List<FurnitureVo> randomFurnitureByBrand(String brandName) {
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.1.57:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("merbau");
		MongoCollection<Document> collection = database.getCollection("fur");
		collection.aggregate(
				Arrays.asList(
					Aggregates.match(Filters.eq("furBrand", brandName)),
					Aggregates.project(Projections.fields(Projections.exclude("furColor"))),
					Aggregates.sample(3))).forEach(printBlock);
		List<FurnitureVo> furItem = new ArrayList<FurnitureVo>();
		for(int fursu=0; fursu < gsonList.size(); fursu++) {
			furItem.add(gsonfurList(gsonList, fursu));
		}
		log.debug("furbest()="+furItem.toString());
		gsonList.clear();
		tempVal.clear(); 
		return furItem;
	}

	public List<FurnitureVo> randomFurniture(){
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.1.57:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("merbau");
		MongoCollection<Document> collection = database.getCollection("fur");
		collection.aggregate(
			Arrays.asList(
				Aggregates.project(
					Projections.fields(Projections.exclude("furColor"))),
				Aggregates.sample(4))).forEach(printBlock);
		List<FurnitureVo> furItem = new ArrayList<FurnitureVo>();
		for(int fursu=0; fursu < gsonList.size(); fursu++){
			furItem.add(gsonfurList(gsonList, fursu));
		}
		gsonList.clear();
		tempVal.clear();
		return furItem;
	}
}