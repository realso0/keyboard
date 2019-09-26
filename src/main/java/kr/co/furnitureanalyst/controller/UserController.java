package kr.co.furnitureanalyst.controller;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.furnitureanalyst.service.UserService;
import kr.co.furnitureanalyst.vo.TagVo;
import kr.co.furnitureanalyst.vo.UserVo;

@RequestMapping("user")
@Controller
public class UserController {

	@Autowired
	UserService userService;

	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("joinform")
	public String joinform() {
		return "user/joinform";
	}

	@RequestMapping("join")
	public String join(@ModelAttribute UserVo newUserVo, @RequestParam("year") String year,
			@RequestParam("month") String month, @RequestParam("day") String day) {
		//System.out.println("컨트롤러 join진입");
		newUserVo.setBirthday(year + "-" + month + "-" + day);

		userService.join(newUserVo);

		return "redirect:/main";
	}

	@RequestMapping("loginform")
	public String loginform() {
		System.out.println("로긴폼 메소드진입");
		return "user/login";
	}

	@RequestMapping("login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = userService.login(userVo);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/main";// "main/index"; 포워드로 하면 css가 안나왔는데 이유를 알수없음
		} else {

			return "redirect:/user/loginform?flag=1";
		}

	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println("asds");

		return "redirect:/main";
	}

	@RequestMapping("myinfo")
	public String myinfo(HttpSession session, Model model) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");

		// List<Map> furnitureList= userService.myInfoLike(userVo);
		// model.addAttribute(furnitureList);
		List<TagVo> tagList = userService.myInfoStat(userVo,"userLikeTag", 0);
		String tagName = new String(); //[TagVo [tagName=모던, tagVal=21.0]를 tagName=모던,빈티지,유니크 형태로 만들어주기 tagVal=21.0,15.0,7.0
		String tagVal = new String();
		for(int i=0; i<tagList.size();i++) {
			tagName+= tagList.get(i).getTagName();
			tagVal+=Float.toString(tagList.get(i).gettagVal());
			if(i!=tagList.size()-1){
				tagName += ",";
				tagVal += ",";
			}
		}
		log.debug("tagName="+tagName.toString());
		log.debug("tagVal="+tagVal.toString());

		model.addAttribute("userLikeTag", userService.myInfoStat(userVo,"userLikeTag",5)) ;
		model.addAttribute("userLikeType", userService.myInfoStat(userVo,"userLikeType", 5)) ;
		model.addAttribute("userLikeBrand", userService.myInfoStat(userVo,"userLikeBrand", 5)) ;
		model.addAttribute("tagName",tagName);
		model.addAttribute("tagVal", tagVal);
		
		//model.addAttribute("furnitureList", userService.myInfoLike(userVo));

		
		return "user/myinfo";
	}

	@RequestMapping("myinfoset")
	public String myinfoset() {

		return "user/myinfo_set";
	}

	@RequestMapping("myinfolike")
	public String myInfoLike(HttpSession session, Model model){
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		List<Map> furnitureList= userService.myInfoLike(userVo);
		log.debug(furnitureList.get(0).getClass());
		log.debug(furnitureList.getClass()+furnitureList.toString());
		log.debug("*************************"+furnitureList.get(0).get("furImage").getClass());
		model.addAttribute("furnitureList", furnitureList);
		return "user/myinfo_like";
	}
	
	@RequestMapping("myinfostat") //통계를위한 자료(유저의 선호 가구분류,선호 가구브랜드,선호태그 데이터 보내줌)
	public String myInfoStat(HttpSession session, Model model) {
		UserVo userVo = (UserVo) session.getAttribute("authUser");

		// List<Map> furnitureList= userService.myInfoLike(userVo);
		// model.addAttribute(furnitureList);
		//model.addAttribute("userStat", userService.myInfoStat(userVo)) ;
		
		//model.addAttribute("furnitureList", userService.myInfoLike(userVo));

		return "user/myinfo_stat";
	}

	@RequestMapping("updatepassword")
	public String updatePassword(@ModelAttribute UserVo userVo, Model model) {

		userService.updatePassword(userVo);
		return "redirect:/user/myinfo_set";
	}

}
