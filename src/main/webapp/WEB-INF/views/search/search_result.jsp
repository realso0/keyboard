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
<title>Merbau - search Result</title>
<!-- 아이콘 :  <link rel="shortcut icon" href="movLogo.ico"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pageUp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/search_result.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageUp.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/search_result.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<c:import url="/WEB-INF/views/includes/pageUp.jsp"></c:import>
	
	<div class="content clf">
		<div class="sizeLimit searchArea clf">
			<div class="searchUI">
				<input type="text" class="searchInput" placeholder="search">
				<button type="submit" class="searchBtn"><i class="fas fa-search"></i></button>
			</div>
			<div class="filterWrap">
				<div class="filter clf">
					<h4>종류 (한 개만 선택 가능)</h4>
					<!-- <h5 class="filterInfo">가구 종류는 한 개만 선택 가능합니다ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss.</h5> -->
					<ul class="furnitureKind tagSelectWrap">
						<!-- tagSelect -->
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
						<li>모던</li>
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
	
	<div class="content clf researchContent">
		<div class="sizeLimit researchArea clf">
			<div class="researchWrap clf">
				<div class="sortWrap">
					<select class="selectType">
						<option>낮은 가격 순</option>
						<option>인기 순</option>
					</select>
				</div>
				<div class="resultList clf">
					<ul class="clf">
					<c:forEach var="i" items="${furnitureList}" varStatus="status">
					<li>
						<a href="${pageContext.request.contextPath}/search/detail?furId=${i._id}" class="clf">
							<div class="imgArea"><img class="imgName" src="${i.furImage}" alt=""></div>
							<div class="itemArea">
								<c:choose>
									<c:when test="${fn:length(i.furName) > 15}">
										<h4>${fn:substring(i.furName,0,15)} ...</h4>
									</c:when>
									<c:otherwise>
										<h4>${i.furName}</h4>
									</c:otherwise>
								</c:choose>
								<ul class="itemTag clf">
									<c:forEach var="tag" items="${i.furTag}" varStatus="status" end="4">
									<li>${tag}</li>
									</c:forEach>
								</ul>
								<h5><fmt:formatNumber value="${i.furMinPrice}" pattern="#,###"/> ~ <fmt:formatNumber value="${i.furMaxPrice}" pattern="#,###"/></h5>
							</div>
						</a>
					</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>