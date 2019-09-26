package kr.co.furnitureanalyst.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.furnitureanalyst.dao.MainDao;
import kr.co.furnitureanalyst.vo.FurnitureVo;
import kr.co.furnitureanalyst.vo.UserVo;

@Service
public class MainService {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	MainDao mainDao;
	
	// 유저 취향값에 따른 리스트 반환
	public List<FurnitureVo> userPreference(UserVo userVo, int i){
		String userId = userVo.getUserId();
		String userPreVal = userPreValue(userId, i);
		System.out.println("메인서비스의 userPreference = " + userPreVal);
		return mainDao.userPreSearch(userPreVal);
	}
	// 유저 취향값 구하기
	public String userPreValue(String userId, int i){
		return mainDao.userPreValue(userId, i);
	}
	
	// 유저 취향값만 구하기
	public String userPreValue(UserVo userVo, int i){
		return mainDao.userPreValue(userId(userVo), i);
	}
	
	// User Id
	public String userId(UserVo userVo){
		return userVo.getUserId();
	}
	public List<FurnitureVo> randomFurnitureByBrand() {
		log.debug("randomFurnitureByBrand 메소드진입");
		String[] brandList = {"이케아","일룸","한샘","소프시스"};
		Random random = new Random();
		mainDao.randomFurnitureByBrand(brandList[random.nextInt(brandList.length)]);
		return mainDao.randomFurnitureByBrand(brandList[random.nextInt(brandList.length)]);
	}
	
	public List<FurnitureVo> randomFurniture(){
		return mainDao.randomFurniture();
	}
	
	public List<FurnitureVo> bestfur(){
		return mainDao.furbest();
	}
}
