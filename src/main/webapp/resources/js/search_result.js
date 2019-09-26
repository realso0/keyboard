$(function(){
	var viewWidth = window.innerWidth;
	var imgHeight = $(".imgName").height();
	if(viewWidth < 1024){
		$(".itemArea").height(imgHeight-16);
	}else{
		$(".itemTag").attr('style', 'margin-bottom:30px');
	}
	
	$(window).resize(function(){
		viewWidth = window.innerWidth;
		imgHeight = $(".imgName").height();
		if(viewWidth < 1024){
			$(".itemArea").height(imgHeight-16);
			$(".itemTag").removeAttr("style");
		}else{
			$(".itemArea").removeAttr("style");
			$(".itemTag").attr('style', 'margin-bottom:30px');
		}
	});
});