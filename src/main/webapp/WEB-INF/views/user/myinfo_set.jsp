<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>Merbau - myinfo</title>
<!-- 아이콘 :  <link rel="shortcut icon" href="movLogo.ico"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pageUp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/myinfo_set.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageUp.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<c:import url="/WEB-INF/views/includes/pageUp.jsp"></c:import>
	
	<div class="contnet clf imgContent">
		<img src="${pageContext.request.contextPath}/resources/images/myinfo_bg.png" alt="">
	</div>
	<div class="content clf myinfoContent">
		<div class="myinfoMenu">
			<h3>${authUser.userId}</h3>
			<ul class="clf">
				<li><a href="${pageContext.request.contextPath}/user/myinfo"><i class="fas fa-chart-pie"></i><br><span>취향 통계</span></a></li>
				<li><a href="${pageContext.request.contextPath}/user/myinfolike"><i class="fab fa-gratipay"></i><br><span>MY LIKE</span></a></li>
				<li><a href="${pageContext.request.contextPath}/user/myinfoset"><i class="fas fa-cog"></i><br><span>설정</span></a></li>
			</ul>
		</div>
		<div class="sizeLimit myinfoArea clf">
			<div class="listWrap">
				<form action="" class="">
					<table>
						<tr>
							<th>이메일 주소(ID)</th>
							<td><input type="text" class="inputBox" value="${authUser.userId}" readonly></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" class="inputBox"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" class="inputBox"></td>
						</tr>
					</table>
					<input type="submit" class="submitBtn" value="수정">
				</form>
			</div>
		</div>
	</div>

</html>