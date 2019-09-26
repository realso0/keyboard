<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> <%-- substring 사용을 위해서 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>Merbau</title>
<!-- 아이콘 :  <link rel="shortcut icon" href="movLogo.ico"> -->
<link rel="stylesheet" href="./resources/css/reset.css">
<link rel="stylesheet" href="./resources/css/common.css">
<link rel="stylesheet" href="./resources/css/font.css">
<link rel="stylesheet" href="./resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="./resources/css/pageUp.css">
<link rel="stylesheet" href="./resources/css/header.css">
<link rel="stylesheet" href="./resources/css/index.css">
<script src="./resources/js/jquery-1.11.3.min.js"></script>
<script src="./resources/js/index.js"></script>
<script src="./resources/js/pageUp.js"></script>
<script>
$(document).ready(function(){
	var userPre1 = "<c:out value='${userPre1}'/>";
	var userPre2 = "<c:out value='${userPre2}'/>";
	if(userPre1 != "" && userPre2 != ""){
		userPre(1, userPre1);
		userPre(2, userPre2);
	}
	
	function userPre(nth, userPreValue){
		switch(userPreValue){
			case "프로방스" 	: 	changeBg(nth, "#FDF88F", "colorString"); 	break;
			case "유니크" 		: 	changeBg(nth, "#FF4B90", "colorLight"); 	break;
			case "빈티지" 		: 	changeBg(nth, "#C6975C", "colorLight"); 	break;
			case "모던" 		:	changeBg(nth, "#2f2f2f", "colorLight"); 	break;
			case "북유럽" 		:	changeBg(nth, "#F6E1C7", "colorStrong"); 	break;
			default 			:   changeBg(nth, "#607d8b", "colorLight");		break;
		}
	}
	
	function changeBg(nth, colorCode, textColor){
		if(nth == 1){
			$(".userPreBox").css({"background":colorCode});
			$(".userPreArea .userPreHeader h3").addClass(textColor);
		}else{
			$(".userPreBox2").css({"background":colorCode});
			$(".userPreArea2 .userPreHeader h3").addClass(textColor);
		};
	}
	
	// 추천 가구 브랜드별
	var furLikeBrand = "<c:out value='${oneBrandFurniture[0].furBrand}'/>";
	switch(furLikeBrand){
		case "이케아" 		: 	$(".brandHeader h4").html("이케아는 가치를 중시하고</br>당신에게 더 나은 생활을 할 수 있는 가구를 지향합니다"); 	break;
		case "한샘" 		: 	$(".brandHeader h4").html("한샘의 패키지 설계는 가구가 아니라</br>공간을 제안합니다"); 								break;
		case "소프시스" 	: 	$(".brandHeader h4").html("소프시스는 늘 좋은 가구를</br>합리적인 가격을 위해 노력합니다."); 						break;
		case "일룸" 		:	$(".brandHeader h4").html("일룸은 진정성 있는 가구를 만들기 위해</br>끊임없이 고민하고 연구합니다."); 				break;
	}
});

</script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<c:import url="/WEB-INF/views/includes/pageUp.jsp"></c:import>
	<div class="content bgArea">
		<div class="searchBgBox"></div>
		<div class="sizeLimit searchArea">
			<h2>선택은 더 쉽게, 취향은 더 정확하게<br>멀바우와 함께 하세요</h2>
			<div class="searchUI">
				<input type="text" class="searchInput" placeholder="search" readonly>
				<button type="submit" class="searchBtn"><i class="fas fa-search"></i></button>
			</div>
			<div class="filterWrap">
				<div class="filter clf">
					<h4>종류 (한 개만 선택 가능)</h4>
					<ul class="furnitureKind tagSelectWrap">
						<li>침대</li>
						<li>의자</li>
						<li>소파</li>
						<li>식탁/의자</li>
						<li>서랍장</li>
						<li>장롱/붙박이장</li>
						<li>책상</li>
						<li>화장대</li>
						<li>테이블</li>
						<li>거실장</li>
						<li>책장</li>
						<li>행거</li>
						<li>선반</li>
					</ul>
					<h4>브랜드</h4>
					<ul class="brandKind tagSelectWrap">
						<li>이케아</li>
						<li>일룸</li>
						<li>한샘</li>
						<li>소프시스</li>
					</ul>
					<h4>취향</h4>
					<ul class="statKind tagSelectWrap">
						<c:forEach items="${furTag.posTag}" var="posTag">	
							<li>${posTag}</li>
						</c:forEach>
						<c:forEach items="${furTag.styTag}" var="styTag">	
							<li>${styTag}</li>
						</c:forEach>
						<li>북유럽</li>
						<li>내추럴</li>
						<li>빈티지</li>
						<li>프로방스/로맨틱</li>
						<li>클래식/엔틱</li>
						<li>유니크</li>
						<li>보헤미안</li>
					</ul>
					<form action="${pageContext.request.contextPath}/search/searchresult">
						<p class="filterCloseBtn">필터 닫기</p>
						<input type="submit" class="tagSearchBtn" value="# 지금 검색하기">
						<input type="hidden" class="searchTxt kindTxt" name="furType">
						<input type="hidden" class="searchTxt brandTxt" name="furBrand">
						<input type="hidden" class="searchTxt statTxt" name="furTag">
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- MERBAU'S PICK -->
	<div class="content">
		<div class="sizeLimit mPickArea clf">
			<div class="mPickHeader clf">
				<h3><strong>Merbau's</strong> PICK</h3>
				<hr/>
			</div>
			<div class="mPickWrap clf">
				<ul class="clf">
					<c:forEach items="${bestList}" var="bestList">	
					<li>
						<a href="${pageContext.request.contextPath}/search/detail?furId=${bestList._id}">
							<div class="imgWrapper"><div class="imgBox"><img src="${bestList.furImage}" alt=""></div></div>
							<dl>
								<c:choose>
									<c:when test="${fn:length(bestList.furName) > 15}">
										<dt>${fn:substring(bestList.furName,0,15)} ...</dt>
									</c:when>
									<c:otherwise>
										<dt>${bestList.furName}</dt>
									</c:otherwise>
								</c:choose>
								<dd><strong><fmt:formatNumber value="${bestList.furMinPrice}" pattern="#,###"/></strong>원</dd>
							</dl>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<%-- user Pre - authUser not null --%>
	<c:if test="${authUser != null}">
	<div class="content userPreBg">
		<div class="userPreBox"></div>
		<div class="sizeLimit userPreArea">
			<div class="userPreHeader clf">
				<h3>당신의 취향에 맞는<br><strong>${userPre1}</strong> 가구를 둘러볼까요?</h3>
			</div>
			<div class="userPreList">
				<ul class="clf">
					<c:forEach items="${userPreList1}" var="userPreList1">
					<li>
						<a href="${pageContext.request.contextPath}/search/detail?furId=${userPreList1._id}">
							<img src="${userPreList1.furImage}" alt="">
							<dl>
								<c:choose>
									<c:when test="${fn:length(userPreList1.furName) > 10}">
										<dt>${fn:substring(userPreList1.furName,0,10)}</dt>
									</c:when>
									<c:otherwise>
										<dt>${userPreList1.furName}</dt>
									</c:otherwise>
								</c:choose>
								<dd><fmt:formatNumber value="${userPreList1.furMinPrice}" pattern="#,###"/></dd>
							</dl>
						</a>
					</li>
					</c:forEach>
					<li>
						<a href="#">
							<p><span>+</span> 더보기</p>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	</c:if>
	<%-- user Pre - authUser not null --%>
	<c:if test="${authUser != null}">
	<div class="content userPreBg">
		<div class="userPreBox2"></div>
		<div class="sizeLimit userPreArea2">
			<div class="userPreHeader clf">
				<h3>당신의 취향에 맞는<br><strong>${userPre2}</strong> 가구를 둘러볼까요?</h3>
			</div>
			<div class="userPreList2">
				<ul class="clf">
					<c:forEach items="${userPreList2}" var="userPreList2">
					<li>
						<a href="${pageContext.request.contextPath}/search/detail?furId=${userPreList2._id}">
							<img src="${userPreList2.furImage}" alt="">
							<dl>
								<c:choose>
									<c:when test="${fn:length(userPreList2.furName) > 10}">
										<dt>${fn:substring(userPreList2.furName,0,10)}</dt>
									</c:when>
									<c:otherwise>
										<dt>${userPreList2.furName}</dt>
									</c:otherwise>
								</c:choose>
								<dd><fmt:formatNumber value="${userPreList2.furMinPrice}" pattern="#,###"/></dd>
							</dl>
						</a>
					</li>
					</c:forEach>
					<li>
						<a href="#">
							<p><span>+</span> 더보기</p>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	</c:if>
	
	<!-- YOU'RE BRAND -->
	<div class="content brandBg">
		<div class="sizeLimit brandArea">
			<div class="brandHeader">
				<h3>It's ${oneBrandFurniture[0].furBrand} For You</h3>
				<h4>${oneBrandFurniture[0].furBrand}는 가치를 중시하고<br>당신에게 더 나은 생활을 할 수 있는 가구를 지향합니다</h4>
			</div>
			<ul class="brandList clf">
				<c:forEach var="i" items="${oneBrandFurniture}">
				<li>
					<a href="${pageContext.request.contextPath}/search/detail?furId=${i._id}">
						<div class="imgWrapper"><div class="imgBox"><img src="${i.furImage}" alt=""></div></div>
						<dl>
							<dt>${i.furName}</dt>
							<dd>
							<c:choose>
  								<c:when test="${i.furMinPrice eq i.furMaxPrice}">
     						   		<fmt:formatNumber value="${i.furMinPrice}" pattern="#,###"/>
   								</c:when>
   								<c:otherwise>
   									<fmt:formatNumber value="${i.furMinPrice}" pattern="#,###"/> ~ <fmt:formatNumber value="${i.furMaxPrice}" pattern="#,###"/>
							    </c:otherwise>
							</c:choose>
							</dd>
						</dl>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<div class="content anyBg">
		<div class="anyBgBox"></div>
		<div class="sizeLimit anyArea">
			<div class="anyHeader">
				<h3>Today,<br>ANYTHING</h3>
				<h4>오늘은 어떤 것이든 좋아요.</h4>
				<p>· · ·</p>
			</div>
			<div class="anyList">
				<ul class="clf">
				<c:forEach var="i" items="${randomFurniture}">
				<li>
					<a href="${pageContext.request.contextPath}/search/detail?furId=${i._id}">
						<div class="imgWrapper"><div class="imgBox"><img src="${i.furImage}" alt=""></div>
						<dl>
							<c:choose>
								<c:when test="${fn:length(i.furName) > 10}">
									<dt>${fn:substring(i.furName,0,10)} ...</dt>
								</c:when>
								<c:otherwise>
									<dt>${i.furName}</dt>
								</c:otherwise>
							</c:choose>
							<dd>
							<c:choose>
 							  	<c:when test="${i.furMinPrice eq i.furMaxPrice}">
    						   		<fmt:formatNumber value="${i.furMinPrice}" pattern="#,###"/>
  									</c:when>
								<c:otherwise>
						    		<fmt:formatNumber value="${i.furMinPrice}" pattern="#,###"/> ~ <fmt:formatNumber value="${i.furMaxPrice}" pattern="#,###"/>
						    	</c:otherwise>
							</c:choose>
							</dd>
						</dl>
					</a>
				</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>