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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/myinfo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jqcloud.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pageUp.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/myinfo.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jqcloud.min.js"></script>
<script>
$(function(){
	// *********************************************
	// Word Cloud
	var tagName = '<c:out value="${tagName}"/>';
	var tagValue = '<c:out value="${tagVal}"/>';
	var tagNameList = tagName.split(",");
	var tagValueList = tagValue.split(",");
	var words = []
	for(var i=0; i<tagNameList.length; i++){
		words.push({text:tagNameList[i], weight:tagValueList[i]})
	};
	$('#keywords').jQCloud(words,{
		autoResize:true
	});
});
</script>
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
			<%-- <h4>취향 통계</h4> --%>
			<div class="preStat">
				<div class="interFurWrap topTag">
					<div class="icon"><i class="fas fa-gem"></i></div>
					<div class="iconTxt">
						<dl>
							<dt>${userLikeType[0].tagName}</dt>
							<dd>관심 가구 종류</dd>
						</dl>
					</div>
				</div>
				<div class="interBrandWrap topTag">
					<div class="icon"><i class="fas fa-gem"></i></div>
					<div class="iconTxt">
						<dl>
							<dt>${userLikeBrand[0].tagName}</dt>
							<dd>관심 브랜드 종류</dd>
						</dl>
					</div>
				</div>
				<div class="interFurGraphWrap graph">
					<h3>관심 가구 종류</h3>
					<div class="donutchart">
						<div class="pieID pie"></div>
						<ul class="pieID legend">
							<c:forEach var="score" items="${userLikeType}">
							<li><p></p><em>${score.tagName}</em> <span>${score.tagVal}</span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="interBrandGraphWrap graph">
					<h3>관심 브랜드 종류</h3>
					<!-- <div id="columnchart_values"></div> -->
					<div class="brandGraph">
						<ul class="clf">
						<c:forEach var="score" items="${userLikeBrand}">
						<li>
							<span class="bar" style="height:${score.tagVal}%"><span class="value">${score.tagVal }%</span></span>
							<span class="text">${score.tagName}</span>
						</li>
						</c:forEach>
						</ul>
					</div>
				</div>
				<div class="preScoreWrap">
					<h3>내 취향 점수</h3>
					<table>
						<c:forEach var="i" items="${userLikeTag}">
						<tr>
							<th>${i.tagName}</th>
							<td>${i.tagVal}</td>
						</tr>
						</c:forEach>
					</table>
				</div>
				<div class="preWordCloudWrap">
					<h3>취향 태그</h3>
					<div id="keywords"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
function sliceSize(dataNum, dataTotal) {
	return (dataNum / dataTotal) * 360;
}

function addSlice(sliceSize, pieElement, offset, sliceID, color) {
	$(pieElement).append("<div class='slice "+sliceID+"'><span></span></div>");
	var offset = offset - 1;
	var sizeRotation = -179 + sliceSize;
	$("."+sliceID).css({"transform": "rotate("+offset+"deg) translate3d(0,0,0)"});
	$("."+sliceID+" span").css({
		"transform"       : "rotate("+sizeRotation+"deg) translate3d(0,0,0)",
		"background-color": color
	});
}
	
function iterateSlices(sliceSize, pieElement, offset, dataCount, sliceCount, color) {
	var sliceID = "s"+dataCount+"-"+sliceCount;
	var maxSize = 179;
	if(sliceSize<=maxSize) {
		addSlice(sliceSize, pieElement, offset, sliceID, color);
	}else{
		addSlice(maxSize, pieElement, offset, sliceID, color);
		iterateSlices(sliceSize-maxSize, pieElement, offset+maxSize, dataCount, sliceCount+1, color);
	}
}

function createPie(dataElement, pieElement) {
	var listData = [];
	$(dataElement+" span").each(function() {
		listData.push(Number($(this).html()));
	});
	var listTotal = 0;
	for(var i=0; i<listData.length; i++) {
		listTotal += listData[i];
	}
	var offset = 0;
	var color = ["#EF5350", "#F06292", "#7E57C2", "#26C6DA", "#66BB6A"];
	for(var i=0; i<listData.length; i++) {
		var size = sliceSize(listData[i], listTotal);
		iterateSlices(size, pieElement, offset, i, 0, color[i]);
	    $(dataElement+" li:nth-child("+(i+1)+") p").css("background-color", color[i]);
	    offset += size;
	}
}

createPie(".pieID.legend", ".pieID.pie");

</script>
</html>