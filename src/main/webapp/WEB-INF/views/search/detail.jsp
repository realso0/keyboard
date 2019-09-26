<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>Merbau - detail</title>
<!-- 아이콘 :  <link rel="shortcut icon" href="movLogo.ico"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pageUp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/swiper.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detail.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageUp.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>
var stayTimeChk_cnt = 0;
var userViewTag = [];
$(document).ready(function(){
	// user like
	var userId = "<c:out value='${authUser.userId}'/>";
	var furId = "<c:out value='${furnitureMap._id}'/>";
	var likeChk = <c:out value="${likeChk}"/>;
	var furBrand = "<c:out value='${furnitureMap.furBrand}'/>";
	var furType = "<c:out value='${furnitureMap.furType}'/>";
	var furTagTemp = "<c:out value='${furnitureMap.furTag}'/>";
	var furTag = furTagTemp.replace("[", "").replace("]", "").replace(/, /g, '/');
	console.log("furTag = " + furTag);
	$(".likeBtn").click(function(){
		if(likeChk == true){
			likeCheck(likeChk, userId, furId, furBrand, furType, furTag);
			likeChk = false;
		}else{
			likeCheck(likeChk, userId, furId, furBrand, furType, furTag);
			likeChk = true;
		}
	});	
	// user value chk
	/* var userName = '<c:out value="${authUser.userId}"/>';
	if(userName != ""){
		// get tag value
		var userViewTag = $(".styleTag li").text();
		// list null chk
		if(userViewTag != ""){
			stayTime = setInterval(function(){
				stayTimeChk_cnt+= 1;
				if(stayTimeChk_cnt == 6){
					clearInterval(stayTime);
				}
				stayTimeChk(userName, userViewTag);
			}, 30000);
		}
	} */

	// -------------------------------------------------------------------- //
	// function

	// 머문 시간에 따른 stayTimeChk
	function stayTimeChk(userName, userViewTag){
		$.ajax({
			url : "${pageContext.request.contextPath}/apiUser/time",
			type : "post",
			data : {userName : userName, userViewTag : userViewTag},
			dataType : "json",
			success : function(){
			},
			error : function(XHR, status, error){
				console.error(status + " : " + error);
			}
		});
	}
	
	// user like Check
	function likeCheck(likeChk, userId, furId, furBrand, furType, furTag){
		if(likeChk == true && userId != ""){
			userLike(userId, false, furId, furBrand, furType, furTag);
		}else if(likeChk == false && userId == ""){
			alert("로그인 해주세요.")
			return false;
		}else if(likeChk == false && userId != ""){
			userLike(userId, true, furId, furBrand, furType, furTag);
		}
	}
	
	// user like action
	function userLike(userId, stat, furId, furBrand, furType, furTag){
		$.ajax({
			url : "${pageContext.request.contextPath}/apiUser/userLike",
			type : "post",
			data : {userId:userId, stat:stat, furId:furId, furBrand:furBrand, furType:furType, furTag:furTag},
			success : function(likeChk){
				if(stat == true){
					$(".likeBtn i").removeClass("likeOff");
					$(".likeBtn i").addClass("likeOn");
				}else{
					$(".likeBtn i").removeClass("likeOn");
					$(".likeBtn i").addClass("likeOff");
				}
			},
			error : function(XHR, status, error){
				console.error(status + " : " + error);
			}
		});
	}
})
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<c:import url="/WEB-INF/views/includes/pageUp.jsp"></c:import>
	<div class="pc_fullWrap clf">
		<div class="content clf imgContent">
			<div class="sizeLimit imgArea clf">
				<div class="itemImg"><img src="${furnitureMap.furImage}" alt=""></div>
			</div>
		</div>
		<div class="content clf infoContent">
			<div class="sizeLimit infoArea clf">
				<a href="${furnitureMap.furLink}" class="pcUrlBtn" target="_blank"><i class="fas fa-tags"></i><span>보러가기</span></a>
				<c:choose>
					<c:when test="${likeChk == true}">
						<div class="likeBtn"><i class="fas fa-heart likeOn"></i></div>
					</c:when>
					<c:otherwise>
						<div class="likeBtn"><i class="fas fa-heart likeOff"></i></div>
					</c:otherwise>
				</c:choose>
				<div class="basicInfoWrap">
					<h3>${furnitureMap.furType}</h3>
					<h4>${furnitureMap.furName}</h4>
					<p><fmt:formatNumber value="${furnitureMap.furMinPrice}" pattern="#,###"/>원 ~ <fmt:formatNumber value="${furnitureMap.furMaxPrice}" pattern="#,###"/>원</p>
				</div>
				<div class="textInfoWrap">
					<table>
						<tr>
							<th>브랜드</th>
							<td>${furnitureMap.furBrand}</td>
							<th>색상</th>
							<td>${furnitureMap.furColor}</td>
						</tr>
					</table>
				</div>
				
				<div class="typeTagWrap">
					<table class="tagView">
						<tr>
							<th><i class="fab fa-gratipay"></i><span>스타일</span></th>
							<td>
								<ul class="typeTagList styleTag clf">
									<c:forEach var="tag" items="${furnitureMap.furStyTag}" varStatus="status">
									<li>${tag}</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
						<tr>
							<th><i class="fas fa-map-marker-alt"></i><!-- <i class="fas fa-smile"></i> --><span>긍정</span></th>
							<td>
								<ul class="typeTagList advanTag clf">
									<c:forEach var="tag" items="${furnitureMap.furPosTag}" varStatus="status">
									<li>${tag}</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
						<tr>
							<th><i class="fas fa-frown"></i><span>부정</span></th>
							<td>
								<ul class="typeTagList weakTag clf">
									<c:forEach var="tag" items="${furnitureMap.furNegTag}" varStatus="status">
									<li>${tag}</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</table>
					<p class="tagPickBtn"><span>스타일 직접 고르기</span><i class="fas fa-caret-down"></i></p>
					<div class="tagPickArea">
						<h4>이 제품의 스타일은 ...</h4>
						<ul class="tagPickList clf">
							<li>모던</li>
							<li>시크</li>
							<li>유니크</li>
							<li>앤틱</li>
							<li>프로방스</li>
						</ul>
						<form action="" class="tagPickForm">
							<input class="tagPickTxt" type="hidden" readonly>
							<input class="tagPickSubmit formBtn" type="submit" value="확인">
							<input class="tagPickCancel formBtn" type="reset" value="취소">
						</form>
					</div>
				</div>
				
				<div class="infoGraphWrap">
					<table class="graphTable">
						<tr>
							<th>구매 연령대</th>
							<th>구매 성별</th>
						</tr>
						<tr>
							<td>
								<table class="ageGraph">
									<tr>
										<th class="graphTitle"></th>
										<td><p class="graphBar" style="width:${furnitureMap.furBuyAge.age10 }%"></p><span class="graphName"></span></td>
									</tr>
									<tr>
										<th class="graphTitle"></th>
										<td><p class="graphBar" style="width:${furnitureMap.furBuyAge.age20 }%"></p><span class="graphName"></span></td>
									</tr>
									<tr>
										<th class="graphTitle"></th>
										<td><p class="graphBar" style="width:${furnitureMap.furBuyAge.age30 }%"></p><span class="graphName"></span></td>
									</tr>
									<tr>
										<th class="graphTitle"></th>
										<td><p class="graphBar" style="width:${furnitureMap.furBuyAge.age40 }%"></p><span class="graphName"></span></td>
									</tr>
									<tr>
										<th class="graphTitle"></th>
										<td><p class="graphBar" style="width:${furnitureMap.furBuyAge.age50 }%"></p><span class="graphName"></span></td>
									</tr>
									<tr>
										<th class="graphTitle"></th>
										<td><p class="graphBar" style="width:${furnitureMap.furBuyAge.age60 }%"></p><span class="graphName"></span></td>
									</tr>
								</table>
							</td>
							<td>
								<input type="hidden" class="genderValue" value="${furnitureMap.furBuyGender.man },${furnitureMap.furBuyGender.woman }">
								<div id="piechart" style="width:100%"></div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="itemRecommWrap">
					<h4>Maybe ...</h4>
					<!-- Swiper -->
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<c:forEach items="${furAssociate}" var="furAssociate">	
							<div class="swiper-slide"><a href="${pageContext.request.contextPath}/search/detail?furId=${furAssociate._id}"><img src="${furAssociate.furImage}" alt=""></a></div>
							</c:forEach>
						</div>
					</div>
					<!-- Swiper JS -->
					<script src="${pageContext.request.contextPath}/resources/js/swiper.min.js"></script>
					<!-- Initialize Swiper -->
					<script>
						var swiper = new Swiper('.swiper-container', {
							slidesPerView : 5,
							spaceBetween : 30,
							breakpoints : {
								1024 : {
									slidesPerView : 6,
									spaceBetween : 10,
								},
								768 : {
									slidesPerView : 3,
									spaceBetween : 10,
								},
								640 : {
									slidesPerView : 3,
									spaceBetween : 10,
								},
								320 : {
									slidesPerView : 2,
									spaceBetween : 10,
								}
							}
						});
					</script>
				</div>
			</div>
		</div>
	</div>
	<div class="content clf actionContent">
		<a href="${furnitureMap.furLink}" class="urlBtn" target="_blank"><i class="fas fa-tags"></i><span>보러가기</span></a>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/detail.js"></script>
</html>