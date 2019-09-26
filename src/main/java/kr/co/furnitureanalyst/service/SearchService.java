package kr.co.furnitureanalyst.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import kr.co.furnitureanalyst.dao.SearchDao;
import kr.co.furnitureanalyst.vo.FurnitureVo;

@Service
public class SearchService {

	@Autowired
	SearchDao searchDao;

	public List<Map> tagSearch(String furType, String[] furBrand, String[] furTag) {
		// TODO Auto-generated method stub
		// criteria.is 인자로 어레이가 안되고 어레이리스트만 되서 아래처럼 형변환 .
		ArrayList<String> brandList = new ArrayList<String>(Arrays.asList(furBrand));
		ArrayList<String> tagList = new ArrayList<String>(Arrays.asList(furTag));
		//List<Map> mapList=searchDao.tagSearch(furType,brandList,tagList);
		//뽑아온 {북유럽=3.0, 모던=2.0}을 북유럽,모던 으로 바꿔주기
		//Map map= mapList.get(0);
		//String a=map.get("furTag");
		//LinkedHashMap<String, Double> hMap = (LinkedHashMap<String,Double>)map.get("furTag");
		//String[] keyArr=(String[])hMap.keySet().toArray();
		//for문작성해서 각태그 
		//mapList.set(4, tag);                       //추후에 점수 높은 상위순 태그만 출력하는거 구현해야됨 
		return searchDao.tagSearch(furType,brandList,tagList);
	}

	public void furnitureUpHit(String furName) {
		searchDao.furnitureUpHit(furName);
	}
	
	public Map furnitureByfurId(String furId) {
		return searchDao.furnitureByfurId(furId);
	}

	public void updateUserStatByLike(String[] furBrand, String[] furTag, String furType, String userId) {
		//furTag에서 자료가공 
		for(int i=0; i<furTag.length; i++) {
			String[] temp=furTag[i].split("=");
			furTag[i]=temp[0];
		}
		searchDao.updateUserStatByLike(furBrand,furTag,furType,userId);
	}
	
	// 가구 아이디와 유저 좋아요 리스트와 대조
	public boolean userLikeListComparison(String userId, String furId){
		List<String> userLikeList = userLikeList(userId);
		return userLikeList.contains(furId);
	}
	
	// 유저 좋아요 리스트
	public List<String> userLikeList(String userId){
		return searchDao.userLikeList(userId);
	}

	public Map<String,String[]> findFurTag() {
		// TODO Auto-generated method stub
		
		return searchDao.findFurTag();
	}
	
	public List<FurnitureVo> furAssociate(String furType){
		return searchDao.furAssociate(furType);
	}
	
/*	public String[] findFurNegTag() {
		// TODO Auto-generated method stub
		
		return searchDao.findFurNegTag();
	}
	
	public String[] findFurNeuTag() {
		// TODO Auto-generated method stub
		
		return searchDao.findFurNeuTag();
	}
	
	public String[] findFurStyTag() {
		// TODO Auto-generated method stub
		
		return searchDao.findFurStyTag();
	}*/
}
