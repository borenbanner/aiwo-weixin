// JavaScript Document

var rightI = 0;
var leftJ = 0;
var count = 0;
var animation, animationCircle;
var angleLimit = -180;
function circleAdaptation(){
	var clientWidth = window.innerWidth;
	var circleEle = $(".circleMain");
	var circleMultiple = parseInt((clientWidth - 320)/60)*0.1 + 1; 
	circleEle.css("-moz-transform","scale("+circleMultiple+")");
	circleEle.css("-webkit-transform","scale("+circleMultiple+")");
	circleEle.css("-o-transform","scale("+circleMultiple+")");
	circleEle.css("-ms-transform","scale("+circleMultiple+")");
	circleEle.css("transform","scale("+circleMultiple+")");
}
function leftSemicircle(lSemicircle) {
//	leftJ = leftJ - 2;
	leftJ = leftJ + 1;
	$(lSemicircle).css("-moz-transform", "rotate(" + leftJ + "deg)");
	$(lSemicircle).css("-webkit-transform", "rotate(" + leftJ + "deg)");
	$(lSemicircle).css("-o-transform", "rotate(" + leftJ + "deg)");
	$(lSemicircle).css("-ms-transform", "rotate(" + leftJ + "deg)");
	$(lSemicircle).css("transform", "rotate(" + leftJ + "deg)");
}

function rightSemicircle(rSemicircle) {
//	rightI = rightI - 2;
	rightI = rightI + 1;
	$(rSemicircle).css("-moz-transform", "rotate(" + rightI + "deg)");
	$(rSemicircle).css("-webkit-transform", "rotate(" + rightI + "deg)");
	$(rSemicircle).css("-o-transform", "rotate(" + rightI + "deg)");
	$(rSemicircle).css("-ms-transform", "rotate(" + rightI + "deg)");
	$(rSemicircle).css("transform", "rotate(" + rightI + "deg)");
}
function recolve(circleElem) {
	clearInterval(animationCircle);
	$(circleElem).addClass("revolve");
	animationCircle = setInterval(function() {
		$(circleElem).removeClass("revolve");
	}, 5000);
};
function surround(angle, lSemicircle, rSemicircle, circleElem) {
	angle = -(Math.abs(angle));
	var z = -180 + angle;
	if (rightI < angleLimit || rightI == angleLimit) {
		leftSemicircle(lSemicircle);
		if (count == 1) {
			if (leftJ < angle || leftJ == angle) {
				clearInterval(animation);
				recolve(circleElem);
			}
		}
		if (leftJ < angleLimit || leftJ == -180) {
			count = count + 1;
			rightSemicircle(rSemicircle);
			angleLimit = angleLimit - 180;
		}
	} else {
		if (count == 1) {
			if (rightI < z || rightI == z) {
				clearInterval(animation);
				recolve(circleElem);
			}
		}
		if (rightI != z) {
			rightSemicircle(rSemicircle);
		}
	}
};
function airQualityTwinkle(textIndex,pointIndex) {
	if($("#main").css("display") != "none"){
		$("#main .air-quality-text>span:eq("+textIndex+")").animate({opacity:1},500,function(){
			$("#main .air-quality-text>span:eq("+textIndex+")").animate({opacity:0},500);
		});
		$(".air-quality-triangle>span:eq("+pointIndex+")").css("display","inline-block");
	}
	if($("#inDoor").css("display") != "none"){
		$("#inDoor .air-quality-text>span:eq("+textIndex+")").animate({opacity:1},500,function(){
			$("#inDoor .air-quality-text>span:eq("+textIndex+")").animate({opacity:0},500);
		});
		$("#inDoor .air-quality-triangle>span:eq("+pointIndex+")").css("display","inline-block");
	}
	if($("#near").css("display") != "none"){
		$("#near .air-quality-text>span:eq("+textIndex+")").animate({opacity:1},500,function(){
			$("#near .air-quality-text>span:eq("+textIndex+")").animate({opacity:0},500);
		});
		$("#near .air-quality-triangle>span:eq("+pointIndex+")").css("display","inline-block");
	}
}
function airQualityEnd(angle) {
	if( angle > 120 && angle < 155){
		$(".air-quality-text-end").css("bottom","-28px");
	}else{
		if( angle > 154 && angle < 170){
			$(".air-quality-text-end").css("bottom","-33px");
		}else{
			$(".air-quality-text-end").css("bottom","-38px");
		}
	}
	if(angle > 270){
		$(".air-quality-end").css("-moz-transform", "rotate(315deg)");
		$(".air-quality-end").css("-webkit-transform", "rotate(315deg)");
		$(".air-quality-end").css("-o-transform", "rotate(315deg)");
		$(".air-quality-end").css("-ms-transform", "rotate(315deg)");
		$(".air-quality-end").css("transform", "rotate(315deg)");

		$(".air-quality-text-end").css("-moz-transform", "rotate(-315deg)");
		$(".air-quality-text-end").css("-webkit-transform", "rotate(-315deg)");
		$(".air-quality-text-end").css("-o-transform", "rotate(-315deg)");
		$(".air-quality-text-end").css("-ms-transform", "rotate(-315deg)");
		$(".air-quality-text-end").css("transform", "rotate(-315deg)");
		setTimeout(function(){$(".air-quality-text-end").show();},800);
	}else{
		if(angle<20){
			angle = 20;
		}
		angle =angle + 45;
		$(".air-quality-end").css("-moz-transform", "rotate(" + angle + "deg)");
		$(".air-quality-end").css("-webkit-transform", "rotate(" + angle + "deg)");
		$(".air-quality-end").css("-o-transform", "rotate(" + angle + "deg)");
		$(".air-quality-end").css("-ms-transform", "rotate(" + angle + "deg)");
		$(".air-quality-end").css("transform", "rotate(" + angle + "deg)");
		var angleNegation = -(Math.abs(angle));
		$(".air-quality-text-end").css("-moz-transform", "rotate(" + angleNegation + "deg)");
		$(".air-quality-text-end").css("-webkit-transform", "rotate(" + angleNegation + "deg)");
		$(".air-quality-text-end").css("-o-transform", "rotate(" + angleNegation + "deg)");
		$(".air-quality-text-end").css("-ms-transform", "rotate(" + angleNegation + "deg)");
		$(".air-quality-text-end").css("transform", "rotate(" + angleNegation + "deg)");
		setTimeout(function(){$(".air-quality-text-end").show();},800);
	}
	
	
}
function surroundClockwise(angle, lSemicircle, rSemicircle, circleElem){
	if( leftJ == 45 ){
		airQualityTwinkle(0,0);
	}
	if( leftJ == 90 ){
		airQualityTwinkle(1,3);
	}
	if( leftJ == 135 ){
		airQualityTwinkle(2,1);
	}
	if( leftJ == 179 ){
		airQualityTwinkle(3,4);
	}
	if( rightI == 45 ){
		airQualityTwinkle(4,2);
	}
	if( rightI == 90 ){
		airQualityTwinkle(5,5);
	}
	angle = Math.abs(angle);
	if( angle < 180 || angle == 180 ){
		if( angle == leftJ ){
			clearInterval(animation);
			airQualityEnd(angle);
		}
		leftSemicircle(lSemicircle);
	}else{
		if(leftJ < 180){
			leftSemicircle(lSemicircle);
		}else{
			if( angle == rightI + 180 || rightI > 90){
				clearInterval(animation);
				airQualityEnd(angle);
			}
			rightSemicircle(rSemicircle);
		}
	}
}

function optimizeAnimation(index, minIndex, maxIndex, lSemicircle, rSemicircle,
		circleElem) {
	rightI = 0;
	leftJ = 0;
	count = 0;
	tipsFalg = 0;
	animation;
	angleLimit = -180;
	$(".air-quality-text-end").hide();
	$(".air-quality-triangle>span").not(".pointLast").hide();
	if( typeof(index) == "undefined" || index == "" ){
		return ;
	}
	clearInterval(animation);
	$(lSemicircle).css("-moz-transform", "rotate(0deg)");
	$(lSemicircle).css("-webkit-transform", "rotate(0deg)");
	$(lSemicircle).css("-o-transform", "rotate(0deg)");
	$(lSemicircle).css("-ms-transform", "rotate(0deg)");
	$(lSemicircle).css("transform", "rotate(0deg)");
	$(rSemicircle).css("-moz-transform", "rotate(0deg)");
	$(rSemicircle).css("-webkit-transform", "rotate(0deg)");
	$(rSemicircle).css("-o-transform", "rotate(0deg)");
	$(rSemicircle).css("-ms-transform", "rotate(0deg)");
	$(rSemicircle).css("transform", "rotate(0deg)");
	index = Math.floor((270 / (maxIndex - minIndex)) * index);
//	animation = setInterval(function() {
//		surround(index, lSemicircle, rSemicircle, circleElem);
//	}, 1);
	animation = setInterval(function() {
		surroundClockwise(index, lSemicircle, rSemicircle, circleElem);
	}, 10);
}

var cdrightI = 0;
var cdleftJ = 0;
var cdangle = 0;
var cdanimation, cdTimeAnimation;
var MM = 0;
var SS = 59;
var optimizeTips;
function cdLeftSemicircle(cdlSemicircle) {
	cdleftJ = cdleftJ - cdangle;
	$(cdlSemicircle).css("-moz-transform", "rotate(" + cdleftJ + "deg)");
	$(cdlSemicircle).css("-webkit-transform", "rotate(" + cdleftJ + "deg)");
	$(cdlSemicircle).css("-o-transform", "rotate(" + cdleftJ + "deg)");
	$(cdlSemicircle).css("-ms-transform", "rotate(" + cdleftJ + "deg)");
	$(cdlSemicircle).css("transform", "rotate(" + cdleftJ + "deg)");

}

function cdRightSemicircle(cdrSemicircle) {
	cdrightI = cdrightI - cdangle;
	$(cdrSemicircle).css("-moz-transform", "rotate(" + cdrightI + "deg)");
	$(cdrSemicircle).css("-webkit-transform", "rotate(" + cdrightI + "deg)");
	$(cdrSemicircle).css("-o-transform", "rotate(" + cdrightI + "deg)");
	$(cdrSemicircle).css("-ms-transform", "rotate(" + cdrightI + "deg)");
	$(cdrSemicircle).css("transform", "rotate(" + cdrightI + "deg)");
}
function cdSurround(cdlSemicircle, cdrSemicircle) {
	if (cdrightI < -180 || cdrightI == -180) {
		if (cdleftJ < -180 || cdleftJ == -180) {
			clearInterval(cdanimation);
		} else {
			cdLeftSemicircle(cdlSemicircle);
		}
	} else {
		cdRightSemicircle(cdrSemicircle);
	}
};

function showTime(virtual) {
	if ($(".cdCircleMain").css("display") == "none") {
		clearInterval(cdTimeAnimation);
	}
	if (SS > 0) {
		SS = SS - 1;
		if (SS < 10) {
			SS = "0" + SS;
		}
		
	}
	if (SS == 0 && MM > 0) {
		SS = 60;
		MM = MM - 1;
		if (MM < 10) {
			MM = "0" + MM;
		}
		;
	}
	$(".countdownText>div").html(MM + ":" + SS);
	if (virtual != 1) {
//		$("#optimize_tips").html('');
//		viewModel.indoorViewModel.tips("");
		if (MM == 0 && SS == 0) {
			clearInterval(cdTimeAnimation);
			clearInterval(updateHaiValue);
			viewModel.loadIndoor($("#openId").val(), false);
			$("[src='images/haw_14.png'][mac!='']:visible").attr("src",
					"images/haw_15.png");
			cancel_cdOptimize();
			viewModel.indoorViewModel.tips("设备已经优化完成");
			$(".optimization-before").text(beforeOptimizeValue);
			$(".optimization-after").text($("#myHai").text());
			$("#optimization-complete").modal("show"); // 20分钟后弹出优化前后值对比

		} else {
			if (MM == 0) {
				if (optimizeTips == "即将通过部分设备达到最佳优化") {
					//$("#optimize_tips").html("约" + parseInt(SS) + "秒后通过部分设备达到最佳优化");
//					 viewModel.indoorViewModel.tips("约"+parseInt(SS)+"秒后通过部分设备达到最佳优化");
				} else {
					//$("#optimize_tips").html("已开启设备，约" + parseInt(SS) + "秒后拥有好空气");
//					 viewModel.indoorViewModel.tips("已开启设备，约"+parseInt(SS)+"秒后拥有好空气");
				}
			} else {
				if (optimizeTips == "即将通过部分设备达到最佳优化") {
//					$("#optimize_tips").html("约" + parseInt(MM) + "分钟后通过部分设备达到最佳优化");
//					 viewModel.indoorViewModel.tips("约"+parseInt(MM)+"分钟后通过部分设备达到最佳优化");
				} else {
//					$("#optimize_tips").html("已开启设备，约" + parseInt(MM) + "分钟后拥有好空气");
//					 viewModel.indoorViewModel.tips("已开启设备，约"+parseInt(MM)+"分钟后拥有好空气");
				}

			}
		}
		if( tipsFalg < viewModel.indoorViewModel.optTips().length ){
			viewModel.indoorViewModel.tips('');
			viewModel.indoorViewModel.tips(viewModel.indoorViewModel.optTips()[tipsFalg]);
			tipsFalg++;
		}
	}else{
		if( SS == 0 && MM == 0){
			cancel_cdOptimize();
			viewModel.loadIndoor($("#openId").val(), true);
			$(".optimization-before").text(beforeOptimizeValue);
			$(".optimization-after").text($("#myHai").text());
			$("#optimization-complete").modal("show"); 
		}
	}
}
function cdOptimize(){
	$(".cdCircleMain").css("display", "block");
	/*$(".after-air").css("display", "inline");*/
	$('.goto_icon').css("visibility", "hidden");
	/*$(".need-optimize").css("visibility","hidden");*/
	$(".warningRed").removeClass("warningRed");
	if(viewModel.indoorViewModel.pm25() == "20" || viewModel.indoorViewModel.pm25() == "20.0" ){
		$(".after-air:eq(0)").css("display", "none");
	}else{
		$(".after-air:eq(0)").css("display", "inline");
	}
	if(viewModel.indoorViewModel.hcho() == "0.01" ){
		$(".after-air:eq(1)").css("display", "none");
	}else{
		$(".after-air:eq(1)").css("display", "inline");
	}
	if(viewModel.indoorViewModel.temp() == "25" || viewModel.indoorViewModel.temp() == "25.0"){
		$(".after-air:eq(2)").css("display", "none");
	}else{
		$(".after-air:eq(2)").css("display", "inline");
	}
	if(viewModel.indoorViewModel.wet() == "50" || viewModel.indoorViewModel.wet() == "50.0" ){
		$(".after-air:eq(3)").css("display", "none");
	}else{
		$(".after-air:eq(3)").css("display", "inline");
	}
	if(viewModel.indoorViewModel.voc() == "20" || viewModel.indoorViewModel.voc() == "20.0" ){
		$(".after-air:eq(4)").css("display", "none");
	}else{
		$(".after-air:eq(4)").css("display", "inline");
	}
}
function cancel_cdOptimize(){
	clearInterval(cdTimeAnimation);
	$(".cdCircleMain").css("display", "none");
	$(".after-air").css("display", "none");
	$('.goto_icon').css("visibility", "visible");
}
function cdOptimizeAnimation(cdlSemicircle, cdrSemicircle, countDownTime,
		virtual) {
	$("[src='images/haw_15.png'][mac!='']:visible").attr("src",
			"images/haw_14.png");
	cdrightI = 0;
	cdleftJ = 0;
	MM = 0;
	SS = 59;
	MM = countDownTime - 1;
	clearInterval(cdTimeAnimation);
	clearInterval(cdanimation);
	optimizeTips = viewModel.indoorViewModel.tips();
	$(cdlSemicircle).css("-moz-transform", "rotate(0deg)");
	$(cdlSemicircle).css("-webkit-transform", "rotate(0deg)");
	$(cdlSemicircle).css("-o-transform", "rotate(0deg)");
	$(cdlSemicircle).css("-ms-transform", "rotate(0deg)");
	$(cdlSemicircle).css("transform", "rotate(0deg)");
	$(cdrSemicircle).css("-moz-transform", "rotate(0deg)");
	$(cdrSemicircle).css("-webkit-transform", "rotate(0deg)");
	$(cdrSemicircle).css("-o-transform", "rotate(0deg)");
	$(cdrSemicircle).css("-ms-transform", "rotate(0deg)");
	$(cdrSemicircle).css("transform", "rotate(0deg)");
	cdangle = 360 / (countDownTime * 60);
	if(!virtual){
		updateHaiValue = setInterval(function() {
			viewModel.loadIndoor($("#openId").val(), virtual);
		}, 300000);
	}
	cdanimation = setInterval(function() {
		cdSurround(cdlSemicircle, cdrSemicircle);
	}, 1000);
	cdTimeAnimation = setInterval(function() {
		showTime(virtual);
	}, 1000);
	cdOptimize();
}
