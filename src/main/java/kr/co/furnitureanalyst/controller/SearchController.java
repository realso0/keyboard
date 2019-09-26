package kr.co.furnitureanalyst.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.furnitureanalyst.service.SearchService;
import kr.co.furnitureanalyst.vo.FurnitureVo;
import kr.co.furnitureanalyst.vo.UserVo;

@RequestMapping("search")
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;
	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("searchresult")//검색결과창
	public String searchResultForm(@RequestParam("furType") String furType, @RequestParam("furBrand") String[] furBrand,
			@RequestParam("furTag") String[] furTag, Model model) {
		//log.debug(furType.toString() + Arrays.toString(furBrand) + Arrays.toString(furTag));
		//log.debug("*********"+searchService.tagSearch(furType, furBrand, furTag).toString());
		model.addAttribute("furnitureList", searchService.tagSearch(furType, furBrand, furTag));
		return "search/search_result";
	}

	@RequestMapping("detail")//제품 상세보기 
	public String searchDetailForm(@RequestParam("furId") String furId, Model model, HttpSession session) {
		Map<?, ?> map = searchService.furnitureByfurId(furId);
		model.addAttribute("furnitureMap",map);
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		List<FurnitureVo> furAssociate = searchService.furAssociate((String)map.get("furType"));
		boolean likeChk = false;
		if(userVo != null){
			String userId = userVo.getUserId();
			likeChk = searchService.userLikeListComparison(userVo.getUserId(), furId);
			//searchService.furnitureUpHit(furTag); //가구 조회수 1증가
			//(1)furniture 콜렉션 조회수 1증가
			//searchService.furnitureUpHit(furName);
			//(2)furniture의 브랜드,가구분류,태그 userstat 브랜드 가구분류 카운트0.25씩추가 태그점수0.25추가 (가구 정보는
			//테이블에서 받을것임 )
			//searchService.updateUserStatByLike(furBrand, furTag, furType, userId);
		}
		model.addAttribute("likeChk", likeChk);
		model.addAttribute("furAssociate", furAssociate);
		return "search/detail";
	}
}