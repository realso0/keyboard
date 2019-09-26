<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>Merbau - login</title>
<!-- 아이콘 :  <link rel="shortcut icon" href="movLogo.ico"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pageUp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageUp.js"></script>
<script>
$(document).ready(function(){
	$('.idInputBox').focus();
});
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	<c:import url="/WEB-INF/views/includes/pageUp.jsp"></c:import>
	<div class="content clf">
		<div class="sizeLimit loginArea">
		<div class="loginVisual"><img src="${pageContext.request.contextPath}/resources/images/bg_login.jpg" alt="background image"></div>
			<div class="loginContent">
				<h3>LOGIN</h3>
				<form class="form_private clf" action="${pageContext.request.contextPath}/user/login" method="post" name="testForm">
					<label for="id" class="inputTit">아이디(이메일 주소)</label>
					<p class="idChkTxt chkTxt"></p>
					<input type="text" id="id" class="inputBox idInputBox" name="userId">
					<input type="hidden" class="idChkBtn" disabled="disabled">
					<label for="pw" class="inputTit">비밀번호</label>
					<input type="password" id="pw" class="inputBox pwChk1" name="password">
					<c:if test="${param.flag==1}">
						<p class="inputTit">로그인에 실패했습니다. 다시 입력해주세요.</p>
					</c:if>
					<input type="submit" class="coreBtn loginBtn" value="로그인" id="loginBtn">
				</form>
			</div>
		</div>
	</div>
</body>
</html>