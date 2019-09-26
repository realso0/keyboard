<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>Merbau - join</title>
<!-- 아이콘 : <link rel="shortcut icon" href="movLogo.ico"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pageUp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/swiper.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageUp.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/join.js"></script>
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
		<div class="sizeLimit joinArea">
		<div class="joinVisual"><img src="${pageContext.request.contextPath}/resources/images/bg_join.png" alt="background image"></div>
			<div class="joinContent">
				<h3>JOIN</h3>
				<form class="form_private clf" action="${pageContext.request.contextPath}/user/join" name="testForm">
					<label for="id" class="inputTit">아이디(이메일 주소)</label>
					<p class="idChkTxt chkTxt"></p>
					<!-- 아이디 -->
					<div class="inputCover clf">
						<input type="text" id="id" class="inputBox idInputBox" name="userId">
						<input type="button" class="inputBtn idChk" value="중복 확인">
					</div>
					<input type="hidden" class="idChkBtn" disabled="disabled">
					<!-- 비밀번호 -->
					<label for="pw" class="inputTit">비밀번호</label>
					<input type="password" id="pw" class="inputBox pwChk1" name="password">
					<label for="pw_re" class="inputTit">비밀번호 확인</label>
					<input type="password" id="pw_re" class="inputBox pw_re pwChk2">
					<p class="pwChkTxt chkTxt"></p>
					<!-- 비밀번호 찾기 질문 -->
					<label for="pw_q" class="inputTit">비밀번호 찾기 질문 선택</label>
					<p class="pw_qChkTxt chkTxt"></p>
					<select class="selectType pwQuestion" id="pw_q" name="userQuestion">
						<option>질문을 선택해주세요.</option>
						<option>처음 입학한 초등학교 이름은?</option>
						<option>질문지 2</option>
						<option>질문지 3</option>
						<option>질문지 4</option>
					</select>
					<label for="pw_a" class="inputTit">비밀번호 찾기 답 입력</label>
					<input type="text" id="pw_a" class="inputBox pwFindA" name="userAnswer">
					<p class="pw_aChkTxt chkTxt"></p>
					<!-- 생년월일 -->
					<label for="birth" class="inputTit">생년월일</label>
					<p class="birthChkTxt chkTxt"></p>
					<div class="inputCover">
						<input type="text" id="birth" class="inputBox birthBox birth_y" placeholder="년(4자리)" maxlength="4" name="year">
						<select class="birthBox birth_m selectType" name="month">
							<option>월</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
						</select>
						<input type="text" id="birth" class="inputBox birthBox birth_d" placeholder="일" maxlength="2" name="day">
					</div>
					<!-- 성별 -->
					<label class="inputTit">성별</label>
					<ul class="genderBox clf">
						<li>남자</li>
						<li class="select">여자</li>
					</ul>
					<!-- 취향 선택 -->
					<label class="inputTit">취향</label>
					<p class="statChkTxt chkTxt"></p>
					<ul class="statBox">
						<li><i>#</i>모던</li>
						<li><i>#</i>북유럽</li>
						<li><i>#</i>내추럴</li>
						<li><i>#</i>빈티지</li>
						<li><i>#</i>프로방스/로맨틱</li>
						<li><i>#</i>클래식/엔틱</li>
						<li><i>#</i>유니크</li>
						<li><i>#</i>보헤미안</li>
					</ul>
					<input type="hidden" class="statSelect" name="userLikeTag">
					<input type="hidden" class="genderSelect"  value="여자" name="gender">
					<input type="submit" class="coreBtn joinBtn" value="회원가입" id="joinBtn">
				</form>
			</div>
		</div>
		<div class="serviceInfo">
			<!-- swiper 플러그인 -->
			<div class="swiper-container backgroundeEf">
			    <div class="swiper-wrapper">
			      <div class="swiper-slide"><img src="./resources/images/sample01.png" alt=""></div>
			      <div class="swiper-slide"><img src="./resources/images/sample02.png" alt=""></div>
			      <div class="swiper-slide"><img src="./resources/images/sample03.png" alt=""></div>
			    </div>
			    <!-- Add Pagination -->
			    <div class="swiper-pagination"></div>
			    <script src="${pageContext.request.contextPath }/resources/js/swiper.min.js"></script>
			    <script>
				    var swiper = new Swiper('.swiper-container', {
				      spaceBetween: 30,
				      centeredSlides: true,
				      autoplay: {
				        delay: 2500,
				        disableOnInteraction: false,
				      },
				      pagination: {
				        el: '.swiper-pagination',
				        clickable: true,
				      },
				    });
			  </script>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
//리퀘스트바디로 받을때
$(".idChk").on("click",function(){
	var userId = $("#id").val();
	
	var userVo={
			userId:userId
	}
	console.log(userVo);
	//var password = "1111";
	
	//console.log(email);
	
	/* var userVo={
			email:email,
			password:password
	} */
	
	$.ajax({
		//줄때
		url : "${pageContext.request.contextPath }/apiUser/idCheck",
		type : "post",
		//dataType: "json",
		//contentType : "application/json; charset=UTF=8",  
		//contentType : "text",
		data :  {userId:userId}, /* JSON.stringify(userVo), */     //이렇게 보낼경우 리퀘스트? 리스폰스? 헤더영역에 들어감  @requestbody로 받아줘야됨
		
		
		//받을때 데이터타입 
		//dataType : "json",
		success : function(result){
			
			if(result==true){
				$(".idChkTxt").html("사용 가능한 아이디입니다.");
			}
			else
				$(".idChkTxt").html("중복된 아이디입니다. 다시 입력해주세요.");
		/*성공시 처리해야될 코드 작성*/
		},
		/*연결실패시 */
		error : function(XHR, status, error) {
		console.error(status + " : " + error);
		}
		});
	
});
</script>
</html>