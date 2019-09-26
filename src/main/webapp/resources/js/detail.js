var userSelectList = [];
var delUserList = [];
$(function(){
	// select
	$(".tagPickBtn").click(function(){
		if($(this).hasClass("on")){
			$(".tagPickArea").stop().fadeOut(300);
			$(".tagPickBtn i").removeClass("rotateAction");
			$(this).removeClass("on");
			$(".tagPickCancel").trigger("click");
			$(".tagPickList li").removeClass("tagSelect");
		}else{
			$(this).addClass("on");
			$(".tagPickBtn i").addClass("rotateAction");
			$(".tagPickArea").stop().fadeIn(300);
		}
	});
	
	$(".tagPickCancel").click(function(){
		$(".tagPickList li").removeClass("tagSelect");
	});
	
	// effect 
	$(".likeBtn").animate({opacity:1},700);
	$(".actionContent").delay(700).animate({opacity:1},700);
	
	// age text insert
	var maxVal = 0;
	var ageText = ["10대" , "20대", "30대", "40대", "50대", "60대"];
	for(var i=0; i<6; i++){
		$(".graphTitle").eq(i).text(ageText[i]);
		$(".graphName").eq(i).text($(".graphBar").eq(i).attr('style').replace("width:",""));
		maxVal = i
	}
	
	// user style select
	$(".tagPickArea ul li").click(function(){
		chkTag($(this));
		$(".tagPickTxt").val(userSelectList);
	});
});

// 리사이징 될 때 생기는 오류 대응 고려 필수!
function chkTag(userSelect){
	var parentClass = userSelect.parent();
	var chkFilter = userSelect.text();
	console.log("chkFilter = " + chkFilter);
	if(userSelect.hasClass("tagSelect")){
		userSelect.removeClass("tagSelect");
		if(parentClass.hasClass("tagPickList")){
			delUserList = userSelectList.indexOf(chkFilter);
			userSelectList.splice(delUserList, 1);
		}
	}else{
		userSelect.addClass("tagSelect");
		console.log("parentClass = " + parentClass.attr("class"));
		if(parentClass.hasClass("tagPickList")){
			userSelectList.push(chkFilter);
		}
	}
};

// google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(genderChart);

function genderChart(){
	// value load
	var value = $(".genderValue").val();
	var valueList = value.split(",");
	// insert
	var data = google.visualization.arrayToDataTable([
		['Task', 'Bought Gender Graph'],
		['여성', parseInt(valueList[0])],
		['남성', parseInt(valueList[1])]
	]);
	// option
	var options = {
		legend:"none",
		pieSliceText:"label",
		tooltip: {trigger:'none'},
		enableInteractivity:false,
		colors:['#ff6d6d','#78c1ff']
	};

	var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	chart.draw(data, options);
};