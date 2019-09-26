var kindList = [];
var brandList = [];
var statList = [];
var delKindList = [];
var delBrandList = [];
var delStatList = [];
var searchList = [];
var delFullList = [];
var fullList = [];
$(function(){
   // 내 정보 보기
   /*$(".myInfoBtn").hover(function(){
      $(".myInfoBtn a").stop().delay(1000).show(500);
   },function(){
      $(".myInfoBtn a").stop().hide(0);
   });*/

   // tagSearchBtn
   /*$(".tagSearchBtn").click(function(){
      if($(".tagSelect").length == 0){
         alert("태그를 선택해주세요!");
         return false;
      }
   });*/
   

	
	// filter view
	$(".searchInput").click(function(){
		$(".filterWrap").fadeIn(300);
	});
	$(".filterCloseBtn").click(function(){
		$(".filterWrap").fadeOut(300);
		$('.searchTxt').val("");
		$('.searchInput').val("");
		$(".tagSelectWrap li").removeClass("tagSelect");
		kindList = [];
		brandList = [];
		statList = [];
		fullList = [];
	});

	// 종류
	$(".furnitureKind li").click(function(){
		chkFilter($(this));
		$(".kindTxt").val(kindList);
		$(".searchInput").val(fullList);
	});
	// 브랜드
	$(".brandKind li").click(function(){
		chkFilter($(this));
		$(".brandTxt").val(brandList);
		$(".searchInput").val(fullList);
	});
	// 취향
	$(".statKind li").click(function(){
		chkFilter($(this));
		$(".statTxt").val(statList);
		$(".searchInput").val(fullList);
	});
});

//취향 체크
function chkFilter(userSelect){
	var parentClass = 	userSelect.parent();
	var chkFilter 	= 	userSelect.text();
	if(userSelect.attr("class") == "tagSelect"){
		userSelect.removeClass("tagSelect");
		if(parentClass.hasClass("furnitureKind")){
			delKindList = kindList.indexOf(chkFilter);
			delFullList = fullList.indexOf(chkFilter);
			kindList.splice(delKindList, 1);
			fullList.splice(delFullList, 1);
		}else if(parentClass.hasClass("brandKind")){
			delBrandList = brandList.indexOf(chkFilter);
			delFullList = fullList.indexOf(chkFilter);
			brandList.splice(delBrandList, 1);
			fullList.splice(delFullList, 1);
		}else if(parentClass.hasClass("statKind")){
			delStatList = statList.indexOf(chkFilter);
			delFullList = fullList.indexOf(chkFilter);
			statList.splice(delStatList, 1);
			fullList.splice(delFullList, 1);
		}
	}else{
		if(parentClass.hasClass("furnitureKind")){
			var tagLen = $(".furnitureKind .tagSelect").length + 1;
			if(tagLen < 2){
				kindList.push(chkFilter);
				fullList.push(chkFilter);
			}else{
				return;
			}
		}else if(parentClass.hasClass("brandKind")){
			brandList.push(chkFilter);
			fullList.push(chkFilter);
		}else if(parentClass.hasClass("statKind")){
			statList.push(chkFilter);
			fullList.push(chkFilter);
		}
		userSelect.addClass("tagSelect");
	}
}