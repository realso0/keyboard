<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	if('' != '<c:out value="${authUser}"/>'){
		$(".myinfoBtn").click(function(){
			if($(this).hasClass("objOn") == true){
				$(".myinfoPopUpMenu").stop().fadeOut(300);
				$(this).removeClass("objOn");
			}else{
				$(this).addClass("objOn");
				$(".myinfoPopUpMenu").stop().fadeIn(300);
			}
		});
	}else{
		$(".myinfoBtn").click(function(){
			$(showPopup).fadeIn(300);
		});
		$(".myinfoModalClose").click(function(){
			$(".myinfoModalWrap, .bgBlock").fadeOut(300);
		});
		showPopup = function(){
			$(".myinfoModalWrap, .bgBlock").fadeIn(300);
			$(".myinfoModalWrap").center();
		}
		jQuery.fn.center = function (){
			this.css("position","absolute");
			this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + "px");
			this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + $(window).scrollLeft()) + "px");
			return this;
		}
	}
});
</script>
<!-- HEADER -->
<header class="header clf">
	<h1 class="logo"><a href="${pageContext.request.contextPath}/main"><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo"></a></h1>
	<c:choose>
		<c:when test="${!empty authUser.userId}">
			<div class="myinfoBtn"><span>${authUser.userId}</span><i class="fas fa-user"></i></div>
		</c:when>
		<c:otherwise>
			<div class="myinfoBtn"><span>GUEST</span><i class="fas fa-user"></i></div>
		</c:otherwise>
	</c:choose>
	
</header>

<!--  로그인 회원 -->
<div class="myinfoPopUpMenu">
	<ul>
		<li><a href="${pageContext.request.contextPath}/user/myinfo"><i class="fas fa-info-circle"></i>내 정보</a></li>
		<li><a href="${pageContext.request.contextPath}/user/myinfolike"><i class="fas fa-heart"></i>LIKE</a></li>
		<li><a href="${pageContext.request.contextPath}/user/logout"><i class="fas fa-sign-out-alt"></i>로그아웃</a></li>
	</ul>
</div>
<!-- 비로그인 회원 -->
<div class="bgBlock"></div>
<div class="myinfoModalWrap">
	<h3>아직 Merbau의<br>회원이 아니신가요?</h3>
	<ul>
		<li><a href="${pageContext.request.contextPath }/user/joinform">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath }/user/loginform">로그인</a></li>
	</ul>
	<p class="myinfoModalClose">닫기</p>
</div>