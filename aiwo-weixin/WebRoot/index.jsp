<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href=".">
<title>爱窝智慧家庭-用户</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/buttons.css">
<link rel="stylesheet" type="text/css" href="css/circle.css">
<link rel="stylesheet" type="text/css" href="css/line.css">

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/circle.js"></script>
<script type="text/javascript" src="js/iscroll.js"></script>
<script type="text/javascript" src="js/jQueryRotate.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body style="background-color: rgb(240, 240, 240);">

	<input type="hidden" name="userId" value="${userId}" />
	<div id="main" style="overflow-x: hidden;">
		<div class="bg " style="height: 100%;">
			<div class="location">
				<img src="image/haw_10.png" style="width: 30px; display: inline;"
					alt=""> <span data-bind="text:location" id="showOther">查看设备</span>
			</div>
			<img id="goto" src="image/haw_76.png"
				style="width: 32%; display: inline; margin-top: 61%">
			<div class="absoluteCentering circleSize">
				<div class="circleMain" id="circleMain">
					<div class="circle_1">
						<div class="circle_2">
							<div class="circle_3" id="circle_3"></div>
							<div class="air-quality-end">
								<span class="air-quality-text-end"
									style="bottom: -28px; display: inline;"> <!-- 									style="bottom: -28px; -webkit-transform: rotate(-172deg); display: inline;" -->
								</span>
							</div>
						</div>
					</div>
					<div class="circleText">
						<div class="circle-hai">
							<span id="pm"></span><span style="font-size: 10px;">μg/m³</span>
						</div>
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
				<span class="airValue" id="wd"></span>
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
				<span class="airValue" id="sd"></span>
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
				<span class="airValue" id="lux"></span>
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
				<span class="airValue" id="pm2"></span>
			</div>
			<span
				style="display: inline-block; vertical-align: top; font-size: 10px;">μg/m³</span>
		</div>
		<br>
		<hr>
		<div style="text-align: center; margin: auto;">
			<button type="submit" class="btn binding" id="managerModel"
				style="background-color: #999; color: #fff; width: 50%;">
				<img src="image/haw_70.png" style="width: 20px; display: inline;"
					alt="" class="img-rounded img-responsive"> 管理我的爱窝
			</button>
			<!-- 			</form> -->
		</div>
		<br>
	</div>

	<div class="modal fade" id="noEquipment" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="modal-header-color" class="modal-header"
					data-dismiss="modal">
					<img class="glyphicon close-modal" alt="close"
						src="image/whiteDel.png" data-dismiss="modal">
					<h5 class="modal-title">用户设备</h5>
				</div>
				<div class="modal-body" id="deviceListShow"></div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="manager" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="modal-header-color-2" class="modal-header"
					data-dismiss="modal">
					<img class="glyphicon close-modal" alt="close"
						src="image/whiteDel.png" data-dismiss="modal">
					<h5 class="modal-title">用户设备管理</h5>
				</div>
				<div class="modal-body" id="managerList"></div>
			</div>
		</div>
	</div>
</body>
</html>