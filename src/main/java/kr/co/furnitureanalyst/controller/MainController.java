package kr.co.furnitureanalyst.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.furnitureanalyst.dao.SearchDao;
import kr.co.furnitureanalyst.service.MainService;
import kr.co.furnitureanalyst.service.SearchService;
import kr.co.furnitureanalyst.service.UserService;
import kr.co.furnitureanalyst.vo.FurnitureVo;
import kr.co.furnitureanalyst.vo.UserVo;

@Controller
public class MainController {

	@Autowired
	UserService userService;
	@Autowired
	MainService mainService;
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	SearchDao searchDao;
	@Autowired
	SearchService searchService;
	@RequestMapping("main")
	public String main(HttpSession session, Model model){
		//searchDao.TestMongotemp();
		//ArrayList<String> brandList=null;
		//ArrayList<String> tagList=null;
		//String[] brandList=null;
		
		//searchDao.tagSearchTest("침대",brandList, tagList);
		//searchService.tagSearch("침대", null, null);
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		if(userVo!=null) {
		List<FurnitureVo> userPreList1 = mainService.userPreference(userVo, 1);
		List<FurnitureVo> userPreList2 = mainService.userPreference(userVo, 2);
		String userPre1 = mainService.userPreValue(userVo, 1);
		String userPre2 = mainService.userPreValue(userVo, 2);
		model.addAttribute("userPreList1", userPreList1);
		model.addAttribute("userPreList2", userPreList2);
		model.addAttribute("userPre1", userPre1);
		model.addAttribute("userPre2", userPre2);
		}
		/*MainDao mainDao = new MainDao();
		mainDao.userPreSearch("asd");*/
		//브랜드랜덤으로 하나 뽑아서  랜덤3개
		model.addAttribute("furTag", searchService.findFurTag());
		model.addAttribute("oneBrandFurniture",mainService.randomFurnitureByBrand());
		model.addAttribute("randomFurniture",mainService.randomFurniture());
		//아무거나 무작위 4개
		try {
			UserVo userInfo =(UserVo)session.getAttribute("authuser");
			String UserId = userInfo.getUserId();
		}catch(Exception e){
			List<FurnitureVo> bestList = mainService.bestfur();
			model.addAttribute("bestList",bestList);
		}
		return "main/index";
	}
}