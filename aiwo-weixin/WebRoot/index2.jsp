<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0083)http://pay.haierubic.com/weixinAir/haierservice?openId=oFE8Aj1wvDjH5ftHbOWrB8ZDvvQU -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<base href="http://pay.haierubic.com:80/weixinAir/">-->
<base href=".">
<title>室外空气质量</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/icon_control.css">
<link type="text/css" rel="stylesheet" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/allknow_tryout.css">
<link rel="stylesheet" type="text/css" href="css/air_condition_control.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/buttons.css">
<link rel="stylesheet" type="text/css" href="css/circle.css">
<link rel="stylesheet" type="text/css" href="css/list.css">
<link rel="stylesheet" type="text/css" href="css/line.css">
<link rel="stylesheet" type="text/css" href="css/xpg.scrollSelect.css">


<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/knockout-3.0.0.js"></script>
<script type="text/javascript" src="js/circle.js"></script>
<!-- <script type="text/javascript" src="js/air_condition_control.js"></script> -->
<!-- <script type="text/javascript" src="js/iscroll.js"></script> -->
<script type="text/javascript" src="js/jQueryRotate.js"></script>
<script type="text/javascript">
	
	$(function() {
		circleAdaptation();
		var pm= parseInt($('#pm').text());
		console.log(pm);
		if(pm>-1&&pm<61){
			$(".bg").addClass("air_outstanding");
			$(".air-quality-text-end").text("优秀");
		}else if(pm>60&&pm<121){
			$(".bg").addClass("air_good");
			$(".air-quality-text-end").text("良好");
		}else if(pm>120&&pm<181){
			$(".bg").addClass("air_light_pollution");
			$(".air-quality-text-end").text("轻度污染");
		
		}else if(pm>180&&pm<241){
			$(".bg").addClass("air_light_pollution");
				$(".air-quality-text-end").text("中度污染");
			
		}else if(pm>240&&pm<301){
			$(".bg").addClass("air_severe_pollution");
			$(".air-quality-text-end").text("重度污染");
		}else{
			$(".bg").addClass("air_serious_pollution");
			$(".air-quality-text-end").text("严重污染");
		}
		
		
	});
</script>
</head>
<body style="background-color: rgb(240, 240, 240);">
	<input type="hidden" id="openId" value="oFE8Aj1wvDjH5ftHbOWrB8ZDvvQU">
	<input type="hidden" id="page" value="main">
	<input type="hidden" id="hasDevice" value="false">
	<input type="hidden" id="mac" value="">
	<input type="hidden" id="familyid" value="">
	<input type="hidden" id="subscribe" value="2">
	<input type="hidden" id="before" value="">
	<input type="hidden" id="after" value="">
	<input type="hidden" id="aqi" value="">
	<input type="hidden" id="pkId" value="">
	<input type="hidden" id="nearId" value="">
	<input type="hidden" id="city" value="广州">
	<input type="hidden" id="isVirtual" value="">
	<input type="hidden" id="outdoor" value="">
	<input type="hidden" id="indoor" value="">
	<input type="hidden" id="nickName" value="Liuxp">

	<!-- 预加载页面 -->




	<!-- 首页 -->

	<!-- 首页  -->

	<div id="main" style="overflow-x: hidden;">
		<div class="bg " style="height: 100%;">
			<div class="location">
				<img src="image/haw_10.png" style="width: 30px; display: inline;"
					alt=""> <span data-bind="text:location">查看其他设备</span>
			</div>
			<img id="goto" src="image/haw_76.png"
				style="width: 32%; display: inline; margin-top: 61%">
			<div class="absoluteCentering circleSize">
				<div class="circleMain" id="circleMain"
					style="-webkit-transform: scale(2.7);">
					<div class="circle_1">
						<div class="circle_2">
							<div class="circle_3" id="circle_3"
								style="transform: rotate(45deg); -ms-transform: rotate(45deg); -moz-transform: rotate(45deg); -webkit-transform: rotate(45deg); -o-transform: rotate(45deg);">
								<div class="hold hold1">
									<div class="pie pie1 angle_1"
										style="-webkit-transform: rotate(0deg);"></div>
								</div>
								<div class="hold hold2">
									<div class="pie pie2 angle_2"
										style="-webkit-transform: rotate(128deg);"></div>
								</div>
							</div>
							<div class="air-quality-end"
								style="-webkit-transform: rotate(172deg);">
								<span class="air-quality-text-end" data-bind="text:quality"
									style="bottom: -28px; -webkit-transform: rotate(-172deg); display: inline;"></span>
							</div>
						</div>
					</div>
					<div class="circleText">
						<div class="circle-hai"  ><span id="pm">${session.deviceList[0].pm }</span><span style="font-size: 10px;">μg/m³</span></div>
<!-- 						<div class="circle-hai-text" data-bind="text:quality" -->
<!-- 							style="display: none;">轻度污染fae</div> -->
						<div class="circle-hai-text">PM2.5指数</div>
					</div>
					<div class="air-quality-triangle">
						<span
							style="position: absolute; top: 50%; left: 3px; -webkit-transform: rotate(180deg); display: inline-block;"></span>
						<span
							style="position: absolute; top: 3px; left: 47%; -webkit-transform: rotate(-90deg); display: none;"></span>
						<span
							style="position: absolute; top: 50%; right: 1px; display: none;"></span>
					</div>
					<div class="air-quality-triangle"
						style="transform: rotate(45deg); -ms-transform: rotate(45deg); -moz-transform: rotate(45deg); -webkit-transform: rotate(45deg); -o-transform: rotate(45deg);">
						<span
							style="position: absolute; top: 50%; left: 3px; -webkit-transform: rotate(180deg); display: inline-block;"></span>
						<span
							style="position: absolute; top: 3px; left: 48%; -webkit-transform: rotate(-90deg); display: none;"></span>
						<span
							style="position: absolute; top: 50%; right: 1px; display: none;"></span>
						<span class="pointLast"
							style="position: absolute; bottom: 1px; right: 48%; display:inline-block; transform: rotate(90deg); -ms-transform: rotate(90deg); -moz-transform: rotate(90deg); -webkit-transform: rotate(90deg); -o-transform: rotate(90deg);"></span>
					</div>
					<div class="air-quality-text">
						<span
							style="position: absolute; top: 50%; left: -25px; opacity: 0;">优秀</span>
						<span
							style="position: absolute; top: 20px; left: 0px; opacity: 0;">良好</span>
						<span style="position: absolute; top: -15px; left: 38%;">轻度污染</span><span
							style="position: absolute; top: 0; right: 0;">中度<br>污染
						</span> <span style="position: absolute; top: 46%; right: -30px;">重度<br>污染
						</span> <span style="position: absolute; bottom: -5px; right: 3px;">严重<br>污染
						</span>
					</div>
				</div>
			</div>
<!-- 			<div class="middleBar"> -->
<!-- 				<img src="image/share.png" -->
<!-- 					data-bind="click:function(){shareOutdoor();}" -->
<!-- 					style="width: 30px; display: inline;" alt=""> -->
<!-- 			</div> -->
<!-- 			<div class="bar"> -->
<!-- 				<img src="image/loading.png" -->
<!-- 					style="width: 7%; display: none; margin: 0px auto; position: relative; top: 26%; -webkit-transform: rotate(150deg); -webkit-transform-origin: 50% 50%;" -->
<!-- 					alt="" class="img-rounded img-responsive loading"> <span -->
<!-- 					style="color: #fff; display: inline; margin: 0 auto; position: relative; top: 26%; vertical-align: middle;" -->
<!-- 					data-bind="text:tips">好怀念夏天暖暖的太阳！</span> -->
<!-- 			</div> -->
		</div>
		<br>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-5 ">
				<img src="image/temperature.png" alt=""
					class="img-rounded img-responsive icon"> <span
					class="icon-text">温度</span>
			</div>
			<div class="col-xs-4" style="text-align: right;">
				<span data-bind="text:outTemp" class="airValue">${session.deviceList[0].wd }</span>
			</div>
			<span
				style="display: inline-block; vertical-align: top; font-size: 10px;">℃</span>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-5">
				<img src="image/humidity.png" alt=""
					class="img-rounded img-responsive icon"> <span
					class="icon-text">湿度</span>
			</div>
			<div class="col-xs-4" style="text-align: right;">
				<span data-bind="text:outWet" class="airValue">${session.deviceList[0].sd }</span>
			</div>
			<span
				style="display: inline-block; vertical-align: top; font-size: 10px;">%</span>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-5 ">
				<img src="image/qiya.png" alt=""
					class="img-rounded img-responsive icon"> <span
					class="icon-text">光照</span>
			</div>
			<div class="col-xs-4" style="text-align: right;">
				<span data-bind="text:pressure" class="airValue">${session.deviceList[0].lux }</span>
			</div>
			<span
				style="display: inline-block; vertical-align: top; font-size: 10px;">lux</span>
		</div>
		<br>
		<div class="row" style="font-size: 15px;">
			<div class="col-xs-1"></div>
			<div class="col-xs-5 ">
				<img src="image/pm25.png" alt=""
					class="img-rounded img-responsive icon"> <span
					class="icon-text">PM2.5</span>
			</div>
			<div class="col-xs-4" style="text-align: right;">
				<span data-bind="text:outPm25" class="airValue">${session.deviceList[0].pm }</span>
			</div>
			<span
				style="display: inline-block; vertical-align: top; font-size: 10px;">μg/m³</span>
		</div>
		<br>
		<hr>
		<div style="text-align: center; margin: auto;">
			<button type="button" class="btn binding"
				style="background-color: #999; color: #fff; width: 50%;">
				<img src="image/haw_70.png" style="width: 20px; display: inline;"
					alt="" class="img-rounded img-responsive"> 管理我的家电
			</button>
		</div>
		<br>
	</div>

</body>
</html>