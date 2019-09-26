$(function(){
	var userChk = '<c:out value="${authUser}"/>';
	console.log("userChk 값 = " + userChk);
	if(userChk != ""){
		console.log("유저값 존재");
	}else{
		console.log("유저값이 존재하지 않습니다.");
	}

	// 내 정보 클릭시
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
});