// JavaScript Document
/*
 * 日期格式化
 * 
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/*
 * 曲线图的宽度自适应
 */
function lineAdaptation() {
	alert(window.innerWidth);
	var c = $(".canvas");
	c.attr("width", window.innerWidth + 50);
	c.attr("height", 50);
}
/*
 * VOC和PM2.5曲线数据填充
 */
function airBoxlineDate(openId, TimeCycle, DeviceMac, offset, callback) {
	// $('#ajax_loader').modal('show');
	$('#ajax_loader').show();
	$.get("http://" + window.location.hostname + ":" + window.location.port
			+ "/weixinAir/airquality?openId=" + openId + "&timeCycle="
			+ TimeCycle + "&mac=" + DeviceMac + "&offset=" + offset + "&isVtrual=1", function(
			result) {
		/* $('#ajax_loader').modal('hide'); */
		$('#ajax_loader').hide();
		$(".airBoxTemp").text(result.now.temperature);
		$(".airBoxWet").text(result.now.humidity);
		$(".airBoxVOC").text(result.now.voc);
		$(".airBoxPM25").text(result.now.pm25);
		var pm25 = new Array();
		var voc = new Array();
		var pm25d = result.curve.pm25Data;
		var vocd = result.curve.vocData;
		for (var i = 0; i < pm25d.length; i++) {
			if (pm25d[i] > 0) {
				pm25[i] = pm25d[i];
			}
		}
		for (var i = 0; i < vocd.length; i++) {
			if (vocd[i] > 0) {
				voc[i] = vocd[i];
			}
		}
		var lineChartData = {
			labels : result.curve.xLabels,
			datasets : [ {
				fillColor : "rgba(255,255,255,0.3)",
				strokeColor : "rgba(102,0,0,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#660000",
				warningValue : 100,
				// datasetStrokeDotted:[0], 0为实线
				data : voc
			}, {
				fillColor : "rgba(255,255,255,0.5)",
				strokeColor : "rgba(102,102,102,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#666",
				// lineWidth:5,
				warningValue : 100,
				// datasetStrokeDotted:[3], 0为实线
				data : pm25
			} ]
		};
		$("#canvas-voc-pm").attr("width",60*lineChartData.labels.length);
		$("#canvas-voc-pm").attr("height", 200);
		setTimeout(function(){
			$("#wrapper_airLine").scrollLeft(60*lineChartData.labels.length);
		},1000);
		callback(lineChartData);
	});
}
/* VOC和PM2.5曲线模拟数据填充 */
function airData(xLabels, vocData, pmData) {
	var lineChartData = {
		labels : xLabels,
		datasets : [ {
			fillColor : "rgba(255,255,255,0.3)",
			strokeColor : "rgba(102,0,0,1)",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#660000",
			warningValue : 100,
			data : vocData
		}, {
			fillColor : "rgba(255,255,255,0.5)",
			strokeColor : "rgba(102,102,102,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#666",
			/* lineWidth : 5, */
			warningValue : 100,
			data : pmData
		} ]
	};
	$("#canvas-voc-pm").attr("width",60*(lineChartData.labels.length));
	$("#canvas-voc-pm").attr("height", 200);
	setTimeout(function(){
		$("#wrapper_airLine").scrollLeft(60*lineChartData.labels.length);
	},1000);
	return lineChartData;
}
function allknowData(xLabels, allknowData) {
	var allknowDatad = new Array();
	for (var i = 0; i < allknowData.length; i++) {
		if (allknowData[i] != null ) {
			allknowDatad[i] = allknowData[i];
		}else{
			allknowDatad[i] = 0;
		}
	}
	var lineChartData = {
		labels : xLabels,
		datasets : [ {
			fillColor : "rgba(255,255,255,0.3)",
			strokeColor : "rgba(102,102,102,1)",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#666",
			datasetStrokeDotted : [ 0 ], // 0为实线
			warningValue : 0.08,
			data : allknowDatad
		} ]
	};
	$("#canvas-aldehyde").attr("width",60*lineChartData.labels.length);
	$("#canvas-aldehyde").attr("height", 200);
	setTimeout(function(){
		$("#wrapper_allLine").scrollLeft(60*lineChartData.labels.length);
	},1000);
	return lineChartData;
}
/*
 * 画曲线图
 */
function drawingGraphs(ElementId, lineChartData) {
	// 曲线图参数设置
	var optionss = {
		// Boolean - If we show the scale above the chart data // 网格线是否在数据线的上面
		scaleOverlay : false,
		// Boolean - If we want to override with a hard coded scale //
		// 是否用硬编码重写y轴网格线线
		scaleOverride : false,

		// ** Required if scaleOverride is true **
		// Number - The number of steps in a hard coded scale // y轴刻度的个数
		scaleSteps : null,
		// Number - The value jump in the hard coded scale // y轴每个刻度的宽度
		scaleStepWidth : null,
		// Number - The scale starting value // y轴的起始值
		scaleStartValue : null,

		// String - Colour of the scale line // x轴y轴的颜色
		scaleLineColor : "rgba(255,255,255,1)",

		// Number - Pixel width of the scale line // x轴y轴的线宽
		scaleLineWidth : 1,

		// Boolean - Whether to show labels on the scale // 是否显示y轴的标签
		scaleShowLabels : false,

		// Interpolated JS string - can access value // 标签显示值
		scaleLabel : "<%=value%>",

		// String - Scale label font declaration for the scale label // 标签的字体
		scaleFontFamily : "'Arial'",

		// Number - Scale label font size in pixels // 标签字体的大小
		scaleFontSize : 12,

		// String - Scale label font weight style // 标签字体的样式
		scaleFontStyle : "normal",

		// String - Scale label font colour // 标签字体的颜色
		scaleFontColor : "#666",

		// /Boolean - Whether grid lines are shown across the chart // 是否显示网格线
		scaleShowGridLines : false,

		// String - Colour of the grid lines // 网格线的颜色
		scaleGridLineColor : "rgba(0,0,0,.05)",

		// Number - Width of the grid lines // 网格线的线宽
		scaleGridLineWidth : 1,

		// Boolean - Whether the line is curved between points // 是否是曲线
		bezierCurve : false,

		// Boolean - Whether to show a dot for each point // 是否显示点
		pointDot : true,

		// Number - Radius of each point dot in pixels // 点的半径
		pointDotRadius : 4,

		// Number - Pixel width of point dot stroke // 数据线的线宽
		pointDotStrokeWidth : 2,

		// Boolean - Whether to show a stroke for datasets
		datasetStroke : true,

		// Number - Pixel width of dataset stroke
		datasetStrokeWidth : 3,

		// Boolean - Whether to fill the dataset with a colour // 数据线是否填充颜色
		datasetFill : true,

		// 是否填充数据
		dataset : true,

		// Boolean - Whether to animate the chart // 是否有动画效果
		animation : false,

		// Number - Number of animation steps // 动画的步数
		animationSteps : 60,

		// String - Animation easing effect // 动画的效果
		animationEasing : "easeOutQuart",

		// Function - Fires when the animation is complete // 动画完成后调用
		onAnimationComplete : null

	};
	var c = $(ElementId);
	var cxt = c.get(0).getContext("2d");
	return new Chart(cxt).Line(lineChartData, optionss);
}

//画柱状图
function drawingBar(ElementId, lineChartData) {
	// 曲线图参数设置
	var optionss = {
			
			//Boolean - If we show the scale above the chart data			
			scaleOverlay : false,
			
			//Boolean - If we want to override with a hard coded scale
			scaleOverride : false,
			
			//** Required if scaleOverride is true **
			//Number - The number of steps in a hard coded scale
			scaleSteps : null,
			//Number - The value jump in the hard coded scale
			scaleStepWidth : null,
			//Number - The scale starting value
			scaleStartValue : null,

			//String - Colour of the scale line	
			scaleLineColor : "rgba(0,0,0,.1)",
			
			//Number - Pixel width of the scale line	
			scaleLineWidth : 1,

			//Boolean - Whether to show labels on the scale	
			scaleShowLabels : false,
			
			//Interpolated JS string - can access value
			scaleLabel : "<%=value%>",
			
			//String - Scale label font declaration for the scale label
			scaleFontFamily : "'Arial'",
			
			//Number - Scale label font size in pixels	
			scaleFontSize : 12,
			
			//String - Scale label font weight style	
			scaleFontStyle : "normal",
			
			//String - Scale label font colour	
			scaleFontColor : "#666",	
			
			///Boolean - Whether grid lines are shown across the chart
			scaleShowGridLines : false,
			
			//String - Colour of the grid lines
			scaleGridLineColor : "rgba(0,0,0,.05)",
			
			//Number - Width of the grid lines
			scaleGridLineWidth : 1,	

			//Boolean - If there is a stroke on each bar	
			barShowStroke : true,
			
			//Number - Pixel width of the bar stroke	
			barStrokeWidth : 2,
			
			//Number - Spacing between each of the X value sets
			barValueSpacing : 5,
			
			//Number - Spacing between data sets within X values
			barDatasetSpacing : 1,
			
			//Boolean - Whether to animate the chart
			animation : false,

			//Number - Number of animation steps
			animationSteps : 60,
			
			//String - Animation easing effect
			animationEasing : "easeOutQuart",

			//Function - Fires when the animation is complete
			onAnimationComplete : null
			
		};
	var c = $(ElementId);
	var cxt = c.get(0).getContext("2d");
	return new Chart(cxt).Bar(lineChartData, optionss);
}

function touchSlider(elementId, turnLeftId, turnRightId) {
	var sliderevent = function() {
	};
	var startX = 0;
	var endX = 0;
	document.getElementById(elementId).addEventListener("touchstart",
			touchStart, false);
	document.getElementById(elementId).addEventListener("touchmove", touchMove,
			false);
	document.getElementById(elementId).addEventListener("touchend", touchEnd,
			false);
	// 里面getElementByIdx_x 中 x_x 是新浪自己加的，用的时候请去掉
	function touchStart(event) {
		clearTimeout(sliderevent);
		var touch = event.touches[0];
		startX = touch.pageX;
	}
	function touchMove(event) {
		touch = event.touches[0];
		endX = (startX - touch.pageX);

	}
	function touchEnd(event) {
		sliderevent = setTimeout(function() {
			if (endX < -80) {
				$(turnLeftId).click();
			}
			if (endX > 80) {
				$(turnRightId).click();
			}
		}, 500);
	}
}

function inOutLineData(xLabels, inDoorData, outDoorData, callback) {
	var inDoorDatad = new Array();
	var outDoorDatad = new Array();
	for (var i = 0; i < inDoorData.length; i++) {
		if (inDoorData[i] > 0) {
			inDoorDatad[i] = inDoorData[i];
		}
	}
	for (var i = 0; i < outDoorData.length; i++) {
		if (outDoorData[i] > 0) {
			outDoorDatad[i] = outDoorData[i];
		}
	}
	var lineChartData = {
		labels : xLabels,
		datasets : [ {
			fillColor : "rgba(255,255,255,0.5)",
			strokeColor : "rgba(255,51,153,1)",
			pointColor : "rgba(151,187,205,1)",
			pointStrokeColor : "#ff3399",
			/* lineWidth : 5, */
			/* warningValue : 100, */
			data : inDoorDatad
		}, {
			fillColor : "rgba(255,255,255,0.3)",
			strokeColor : "rgba(255,255,255,1)",
			pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#fff",
			/* warningValue : 100, */
			data : outDoorDatad
		} ]
	};
	$("#canvas-indoor-outdoor").attr("width",60*xLabels.length);
	$("#canvas-indoor-outdoor").attr("height", 200);
	$("#canvas-indoor-outdoor-share").attr("width",60*xLabels.length);
	$("#canvas-indoor-outdoor-share").attr("height", 200);
	setTimeout(function(){
		$(".wrapper_inOutLine").scrollLeft(60*xLabels.length);
	},500);
	callback(lineChartData);
}
function lineSlider(elementId) {
	sliderevent_InOut = function() { };
	var startX_InOut = 0;
	var endX_InOut = 0;
	document.getElementById(elementId).addEventListener("touchstart",
			touchStartLine, false);
	document.getElementById(elementId).addEventListener("touchmove",
			touchMoveLine, false);
	document.getElementById(elementId).addEventListener("touchend",
			touchEndLine, false);
	// 里面getElementByIdx_x 中 x_x 是新浪自己加的，用的时候请去掉
	function touchStartLine(event) {
		var touch = event.touches[0];
		startX_InOut = touch.pageX;
	}
	function touchMoveLine(event) {
		touch = event.touches[0];
		endX_InOut = (startX_InOut - touch.pageX);

	}
	function touchEndLine(event) {
		clearTimeout(sliderevent_InOut);
		sliderevent_InOut = setTimeout(function() {
			if (endX_InOut < -80) {
				if (elementId == "canvas-indoor-outdoor") {
					preweek_inOutDoor();
				}
				if (elementId == "canvas-control") {
					control_offset--;
					control_line_color(controlLineType[lineTypeFlag],
							control_offset);
				}
			}
			if (endX_InOut > 80) {
				if (elementId == "canvas-indoor-outdoor") {
					nextweek_inOutDoor();
				}
				if (elementId == "canvas-control" && control_offset < 0) {
					control_offset++;
					control_line_color(controlLineType[lineTypeFlag],
							control_offset);
				}
			}
		}, 500);
	}
}
function inOutDoorLine() {
	$("#ajax_loader").show();
	offsetInOut = 0;
	lineAdaptation();
	$.get("http://" + window.location.hostname + ":" + window.location.port
			+ "/weixinAir/aqi?city=" + $("#city").val() + "&offset="
			+ offsetInOut + "&openid=" + $("#openId").val(), function(result) {
		$(".chao").text(result.integrity + "%");
		$("#outdoor").val(result.outDoorAqi[result.outDoorAqi.length-1]);
		$("#indoor").val(result.inDoorAqi[result.inDoorAqi.length-1]);
		
		inOutLineData(result.xLabels, result.inDoorAqi, result.outDoorAqi,
				function(data) {
					drawingGraphs("#canvas-indoor-outdoor", data);
					drawingGraphs("#canvas-indoor-outdoor-share", data);
				});
		$("#ajax_loader").hide();
	});
}
function controlLineData(xLabels, tempData, fillColor, strokeColor, callback) {
	var tempDatad = new Array();
	for (var i = 0; i < tempData.length; i++) {
		if (tempData[i] > 0) {
			tempDatad[i] = tempData[i];
		}
	}
	var lineChartData = {
		labels : xLabels,
		datasets : [ {
			fillColor : fillColor,
			strokeColor : strokeColor,
			pointColor : "rgba(240,240,240,1)",
			pointStrokeColor : strokeColor,
			/* warningValue : 100, */
			data : tempDatad
		} ]
	};
	$("#canvas-control").attr("width",60*xLabels.length);
	$("#canvas-control").attr("height", 180);
	setTimeout(function(){
		$("#wrapper_line").scrollLeft(60*xLabels.length);
	},500);
	callback(lineChartData);
	
	
}

function controlLine(offsetFlag, lineType, fillColor, strokeColor) {
	$("#ajax_loader").show();
//	lineAdaptation();
//	$("#canvas-control").attr("height", 180);
	if (lineType == "睡眠曲线") {
		$
				.get(
						"http://"
								+ window.location.hostname
								+ ":"
								+ window.location.port
								+ "/weixinAir/curves?curveType=temp&mac=0007A88A088E&offset=-1",
						function(result) {
							controlLineData(["1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00", "14:00", "15:00","16:00", "17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"], [ 10, 25, 26, 28,
									20, 30, 20, 1, 30, 20,22,12,20, 30, 20, 20, 30, 20,22,12,25,21,12,14 ], fillColor, strokeColor,
									function(data) {
										drawingGraphs("#canvas-control", data);
									});
							$("#ajax_loader").hide();
						});
	}
	if (lineType == "室内温度曲线") {
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/curves?curveType=temp&mac=" + $("#control_mac").val()
				+ "&offset=" + offsetFlag, function(result) {

			if($("#control_mac").val()==""){
				result.xLabels = ["1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00", "14:00", "15:00","16:00", "17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"];
				result.tempData=[17,21,20,23,26,2,17,17,21,20,23,26,25,17,17,21,20,23,26,25,17,22,24,26];
			}
			controlLineData( result.xLabels, result.tempData , fillColor,
					strokeColor, function(data) {
						drawingGraphs("#canvas-control", data);
					});
			$("#ajax_loader").hide();
		});
	}
	if (lineType == "能耗曲线") {
		$
				.get(
						"http://"
								+ window.location.hostname
								+ ":"
								+ window.location.port
								+ "/weixinAir/curves?curveType=temp&mac=0007A88A088E&offset=-1",
						function(result) {

							controlLineData(["1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00", "14:00", "15:00","16:00", "17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"], [17,21,20,23,26,25,17,17,21,20,23,26,25,0,17,21,20,23,26,25,17,22,24,26], fillColor, strokeColor,
									function(data) {
							        	drawingBar("#canvas-control", data);
									});
							$("#ajax_loader").hide();
						});
	}
}
function control_line_color(lineType, offsetFlag) {
	var fillColor = "rgba(0,204,255,0.2)";
	var strokeColor = "rgba(0,204,255,1)";
	if (viewModel.conditionStatus.onOff() == "关") {
		fillColor = "rgba(0,204,255,0.2)";
		strokeColor = "rgba(0,204,255,1)";
	} else {
		switch (viewModel.conditionStatus.mode()) {
		case "制冷":
			fillColor = "rgba(0,204,255,0.2)";
			strokeColor = "rgba(0,204,255,1)";
			break;
		case "制热":
			fillColor = "rgba(255,153,0,0.2)";
			strokeColor = "rgba(255,153,0,1)";
			break;
		case "送风":
			fillColor = "rgba(0,204,0,0.2)";
			strokeColor = "rgba(0,204,0,1)";
			break;
		case "抽湿":
			fillColor = "rgba(255,153,204,0.2)";
			strokeColor = "rgba(255,153,204,1)";
			break;
		case "自动":
			fillColor = "rgba(0,153,204,0.2)";
			strokeColor = "rgba(0,153,204,1)";
			break;
		default:
			/*alert("没有这个模式：" + viewModel.conditionStatus.mode());*/
		}
	}
	controlLine(offsetFlag, lineType, fillColor, strokeColor);
}