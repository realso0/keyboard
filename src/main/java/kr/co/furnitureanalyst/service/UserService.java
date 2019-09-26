package kr.co.furnitureanalyst.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.furnitureanalyst.dao.UserDao;
import kr.co.furnitureanalyst.vo.TagVo;
import kr.co.furnitureanalyst.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	Logger log = Logger.getLogger(this.getClass());

	public void join(UserVo userVo) {
		userDao.join(userVo);
		UserVo userMongoVo = new UserVo();
		userMongoVo.setUserId(userVo.getUserId());
		String[] temp = userVo.getUserLikeTag();
		userDao.mongoJoin(userMongoVo);
		for(int i=0; i<temp.length; i++){
			temp[i] = temp[i].substring(1);
			userDao.mongoJoinUpdateTag(temp[i], userVo.getUserId());
		}
	}
	
	// 원본
	/*public void join(UserVo userVo) {
		userDao.join(userVo);
		UserVo userMongoVo = new UserVo(); // 몽고db에 넣을 vo 생성
		userMongoVo.setUserId(userVo.getUserId());
		String[] temp = userVo.getUserLikeTag();
		for (int i = 0; i < temp.length; i++) { // 태그리스트 형태변환 # 빼기
			temp[i] = temp[i].substring(1);
		}
		userMongoVo.setUserLikeTag(temp);
		userDao.mongoJoin(userMongoVo);
	}*/

	public UserVo login(UserVo userVo){
		return userDao.getUser(userVo);
	}

	public void updatePassword(UserVo userVo) {
		userDao.updatePassword(userVo);
	}

	public boolean idCheck(String userId) {
		boolean result;
		UserVo userVo = userDao.selectUser(userId);
		if (userVo == null)
			result = true;
		else
			result = false;
		return result;
	}

	public List<Map> myInfoLike(UserVo userVo){
		Map map = userDao.findUserStat(userVo.getUserId()); // 몽고디비userStat 에서 특정유저의 다큐먼트 find
		log.debug(map.toString());
		ArrayList userLikeArrList = new ArrayList<String>();
		userLikeArrList = (ArrayList<String>) map.get("userLikeList"); // map 에서 좋아요리스트만 뽑아내기
		if(userLikeArrList==null){
			return null;
		}
		List<Map> listMap = userDao.findFurnitureByFurNameList(userLikeArrList);
		List<Map> likefurList = new ArrayList<Map>();
		for(int i=0; i<userLikeArrList.size(); i++){
			for(int j=0; j<listMap.size(); j++){
				if(userLikeArrList.get(i).equals(listMap.get(j).get("_id"))){
					likefurList.add(listMap.get(j));
				}
			}
		}
		return likefurList;
	}

	public List<TagVo> myInfoStat(UserVo userVo, String fieldName, int limit){
		List<TagVo> likeList=userDao.findUserStat(userVo.getUserId(),fieldName, limit);
		if(fieldName=="userLikeBrand"){
			int sum=0;
			for(int i=0; i<likeList.size();i++){
				sum+=likeList.get(i).gettagVal();
			}
			for(int i=0; i<likeList.size();i++){
				int percent=(int)(likeList.get(i).gettagVal()/sum*100);
				likeList.get(i).settagVal(percent);
			}
		}
		log.debug("myinfostat 메쏘드:"+likeList.toString());
		/*Map map=userDao.findUserStat(userVo.getUserId());
		
		log.debug("asdsadsdasdsd"+map.get("userLikeTag").getClass());
		ArrayList<Map> arrList =(ArrayList<Map>)map.get("userLikeTag");
		
		//정렬 순서 1: 어레이리스트 형태는 건드리지 않고 정렬 
		
		for (int i=0;i<arrList.size();i++) { //맵 내림차순 정렬 
			
			for (int j=i+1; j<arrList.size();j++)
			{
				Map tempMap=null;
				Integer temp=0;
				Double first=(Double)arrList.get(i).get("tagVal");
				Double second=(Double)arrList.get(j).get("tagVal");
				Integer first=(Integer)arrList.get(i).get("tagVal");
				Integer second=(Integer)arrList.get(j).get("tagVal");

				//log.debug(arrList.get(i).get("tagVal").getClass());
				if(first < second) {
					tempMap=arrList.get(i);
					arrList.set(i, arrList.get(j));
					arrList.set(j, tempMap);
					
				}
			}
		}
		map.put("userLikeTag", arrList);*/
		//LinkedHashMap linkHashMap = (LinkedHashMap)map.get("userLikeTag");
		//log.debug("asdasdjjjjjjjjjjjjjjjjjjjj"+arrList.toString());
		
		// TODO Auto-generated method stub
		//System.out.println("userService myinfostat 메소드진입 ");
		//Map map = userDao.findUserStat(userVo.getUserId());
		//System.out.println(map.toString());
		//System.out.println(map.keySet());
		//String userLikeBrand=(String)map.get("userLikeBrand");
		//String userLikeList=(String)map.get("userLikeList");
		//System.out.println(userLikeBrand);
		
		//return 	map;
		return likeList;
	}
	
	// 머문 시간에 따른 취향 반영
	public void timeChk(String id, List<String> listTag){
		List<String> userTag = userLikeTag(id, "userLikeTag");
		userDao.timeChk(id, userTag, listTag);
	}
	
	// 유저가 좋아하는 태그 구하기
	public List<String> userLikeTag(String id, String FieldName){
		return userDao.getTagName(id, FieldName);
	}
	
	// 좋아요 기능
	public void userLike(String userId, boolean stat, String furId, String furBrand, String furType, String furTag){
		// Brand
		List<String> furBrandList = new ArrayList<String>();
		furBrandList.add(furBrand);
		List<String> userFurBrand = userDao.getTagName(userId, "userLikeBrand");
		// Type
		List<String> furTypeList = new ArrayList<String>();
		furTypeList.add(furType);
		List<String> userFurType = userDao.getTagName(userId, "userLikeType");
		// Tag
		List<String> furTagList = new ArrayList<String>(Arrays.asList(furTag.split("/")));
		List<String> userFurTag = userDao.getTagName(userId, "userLikeTag");
		
		if(stat == true){
			userDao.userLikeAdd(userId, furId);
			userDao.furLikeEdit(furId, 1);
			userDao.userStatUpdate(userId, userFurBrand, furBrandList, "userLikeBrand", 5);
			userDao.userStatUpdate(userId, userFurType, furTypeList, "userLikeType", 5);
			userDao.userStatUpdate(userId, userFurTag, furTagList, "userLikeTag", 5);
		}else{
			userDao.userLikeDel(userId, furId);
			userDao.furLikeEdit(furId, -1);
			userDao.userStatUpdate(userId, userFurBrand, furBrandList, "userLikeBrand", -5);
			userDao.userStatUpdate(userId, userFurType, furTypeList, "userLikeType", -5);
			userDao.userStatUpdate(userId, userFurTag, furTagList, "userLikeTag", -5);
		}
	}
}
