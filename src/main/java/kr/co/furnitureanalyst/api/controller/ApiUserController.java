package kr.co.furnitureanalyst.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.furnitureanalyst.service.UserService;
import kr.co.furnitureanalyst.vo.UserVo;

@RequestMapping("apiUser")
@Controller
public class ApiUserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="time", method=RequestMethod.POST)
	public void timeChk(HttpSession session, @RequestParam("userName") String userName, @RequestParam("userViewTag") String userViewTag){
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		String[] listEX = userViewTag.replace("#", " ").trim().split(" ");
		List<String> listTag = new ArrayList<String>(Arrays.asList(listEX));
		//userService.timeChk(userVo.getUserId(),listTag);
	}
	
	@ResponseBody
	@RequestMapping(value="userLike",method=RequestMethod.POST)
	public void userLike(@RequestParam("userId") String userId, @RequestParam("stat") boolean stat,
						 @RequestParam("furId") String furId, @RequestParam("furBrand") String furBrand,
						 @RequestParam("furType") String furType, @RequestParam("furTag") String furTag){
		System.out.println("비동기 통신 userLike 진입");
		userService.userLike(userId, stat, furId, furBrand, furType, furTag);
	}
}