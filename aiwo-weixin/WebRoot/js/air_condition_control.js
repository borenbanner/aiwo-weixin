var usersModel = {
	users : ko.observableArray()
};

var hasChange = false;
var updown;
var disableNum = "";
var tempDisable = false;
var modelDisable = false;
var modeStatusNum = "";
var pages = 1;
var userLen = 0;
var backUsr = true;
var changeControl = false;
var air_condition_point = function(DeviceMac, familyId, type) {
	changeControl = false;
	if (!checkPermission()) {
		return;
	}
	switch (type) {
	case "AirCondition":
		$("#control_mac").val(DeviceMac);
		$("#control_familyid").val(familyId);
		
		$(".function-selection>span").removeClass("active");
		$(".temp-line").show();
		$(".control-area>div").hide();
		viewModel.load_conditionStatus($("#openId").val(), DeviceMac,function(){
			control_offset = 0;
			controlLineType = new Array("睡眠曲线","室内温度曲线","能耗曲线");
			lineTypeFlag = 1;
			control_line_color(controlLineType[lineTypeFlag],control_offset);
			$(".lineTitle>.lineTypeText").text(controlLineType[lineTypeFlag]);
			$(".lineTitle>.turnLeftLine").unbind().bind("click",function(){
				lineTypeFlag--;
				$(".lineTitle>.turnRightLine").css("visibility","visible");
				if(lineTypeFlag==0){
					$(".lineTitle>.turnLeftLine").css("visibility","hidden");
				}
				if(lineTypeFlag >0 || lineTypeFlag==0){
					$(".lineTitle>.lineTypeText").text(controlLineType[lineTypeFlag]);
					control_line_color(controlLineType[lineTypeFlag]);
				}
			});
			$(".lineTitle>.turnRightLine").unbind().bind("click",function(){
				lineTypeFlag++;
				$(".lineTitle>.turnLeftLine").css("visibility","visible");
				if(lineTypeFlag == controlLineType.length-1){
					$(".lineTitle>.turnRightLine").css("visibility","hidden");
				}
				if(lineTypeFlag < controlLineType.length){
					$(".lineTitle>.lineTypeText").text(controlLineType[lineTypeFlag]);
					control_line_color(controlLineType[lineTypeFlag]);
				}
			});
			//暂时取消控制页曲线切换
			$(".lineTitle>.turnLeftLine,.lineTitle>.turnRightLine").unbind().css("color","#ccc");
//			lineSlider("canvas-control");
		});
		
//		navigateToPage($("#air_condition_control"));
		$("#unbindcondition").hide();
		$("#air_condition_control").modal('show');
		break;
	case "AirCircle":
		$("#airbox").modal("show");
		$("#unbindbox").hide();
//		navigateToPage($('#airbox'));
		loadAirBox(DeviceMac);
		break;
	case "AllKnow":
//		navigateToPage($('#allknow'));
		$("#allknow").modal("show");
		$("#unbindknow").hide();
		
		loadAllknow(DeviceMac);
		break;
	default:
		break;
	}

};

function loadAirBox(DeviceMac)
{
	lineAdaptation();
	var openId = $("#openId").val();
	var offset = 0;
	var TimeCycle = "week";
//	airBoxlineDate(openId, TimeCycle, DeviceMac, offset, function(data) {
//		drawingGraphs("#canvas-voc-pm", data);
//	});

	$("#weekBtn-voc-pm").unbind('click').bind('click',function(e) {
		$(".line-main>.btn-group>button").removeClass("active");
		$(this).addClass("active");
//		lineAdaptation();
//		$("#icon-right-voc-pm").hide();
		offset = 0;
		airBoxlineDate(openId, "week", DeviceMac, offset, function(data) {
			drawingGraphs("#canvas-voc-pm", data);
		});
		TimeCycle = "week";
	});
	$("#monthBtn-voc-pm").unbind('click').bind('click',function(e) {
		$(".line-main>.btn-group>button").removeClass("active");
		$(this).addClass("active");
//		lineAdaptation();
//		$("#icon-right-voc-pm").hide();
		offset = 0;
		airBoxlineDate(openId, "month", DeviceMac, offset, function(data) {
			drawingGraphs("#canvas-voc-pm", data);
		});
		TimeCycle = "month";
	});
	$("#icon-left-voc-pm").unbind('click').bind('click',
			function(e) {
//				$("#icon-right-voc-pm").show();
				offset--;
//				lineAdaptation();
				lineChartData = airBoxlineDate(openId, TimeCycle,
						DeviceMac, offset, function(data) {
							drawingGraphs("#canvas-voc-pm", data);
						});

			});
	$("#icon-right-voc-pm").unbind('click').bind('click',
			function(e) {
				if (offset != 0) {
					offset++;
//					lineAdaptation();
					lineChartData = airBoxlineDate(openId, TimeCycle,
							DeviceMac, offset, function(data) {
								drawingGraphs("#canvas-voc-pm", data);
							});
					if(offset == 0){
						$(this).hide();
					}
				}
			});
	$("#weekBtn-voc-pm").click();
}

function loadAllknow(DeviceMac){
	var offset = 0;
	var TimeCycle = "week";
	/* var allknowLineData = allknowData(0,allknowWeekArray[offset]);
	drawingGraphs("#canvas-aldehyde", allknowLineData); */
	$("#icon-right-aldehyde").hide();
	$("#weekBtn-aldehyde").unbind('click').bind('click',function(e) {
		$(".line-main>.btn-group>button").removeClass("active");
		$(this).addClass("active");
//		$("#icon-left-aldehyde").show();
//		$("#icon-right-aldehyde").hide();
//		lineAdaptation();
		offset = 0;
		TimeCycle = "week";
		$.get(
				"http://" + window.location.hostname + ":"
						+ window.location.port
						+ "/weixinAir/allknow?timeCycle="+TimeCycle+"&offset="+offset+"&openId="+$("#openId").val()+"&mac="+DeviceMac+"&isVtrual=1",
				function(result) {
					var allknowLineData = allknowData(result.xLabels,result.hchoData);
					drawingGraphs("#canvas-aldehyde", allknowLineData);
				});
		
	});
	$("#monthBtn-aldehyde").unbind('click').bind('click',function(e) {
		$(".line-main>.btn-group>button").removeClass("active");
		$(this).addClass("active");
		$("#icon-left-aldehyde").hide();
		$("#icon-right-aldehyde").hide();
//		lineAdaptation();
		TimeCycle = "month";
		offset = 0;
		$.get(
				"http://" + window.location.hostname + ":"
						+ window.location.port
						+ "/weixinAir/allknow?timeCycle="+TimeCycle+"&offset="+offset+"&openId="+$("#openId").val()+"&mac="+DeviceMac+"&isVtrual=1",
				function(result) {
					var allknowLineData = allknowData(result.xLabels,result.hchoData);
					drawingGraphs("#canvas-aldehyde", allknowLineData);
				});
		
	});
	
	$("#icon-left-aldehyde").unbind('click').bind('click',
			function(e) {
				if(TimeCycle == "week"){
					$("#icon-right-aldehyde").show();
					lineAdaptation();
					offset--;
					$.get("http://" + window.location.hostname + ":"
									+ window.location.port
									+ "/weixinAir/allknow?timeCycle="+TimeCycle+"&offset="+offset+"&openId="+$("#openId").val()+"&mac="+DeviceMac+"&isVtrual=1",
							function(result) {
								var allknowLineData = allknowData(result.xLabels,result.hchoData);
								drawingGraphs("#canvas-aldehyde", allknowLineData);
							});
				}
			});
	$("#icon-right-aldehyde").unbind('click').bind('click',
			function(e) {
			if(TimeCycle == "week"){
				if (offset != 0) {
//					$("#icon-left-voc-pm").show();
					lineAdaptation();
					offset++;
					$.get("http://" + window.location.hostname + ":"
							+ window.location.port
							+ "/weixinAir/allknow?timeCycle="+TimeCycle+"&offset="+offset+"&openId="+$("#openId").val()+"&mac="+DeviceMac+"&isVtrual=1",
					function(result) {
						var allknowLineData = allknowData(result.xLabels,result.hchoData);
						drawingGraphs("#canvas-aldehyde", allknowLineData);
					});
					if(offset==0){
						$(this).hide();
					}
				}
			}
			});
	$("#weekBtn-aldehyde").click();
}

// vicnent 添加 begin
function setMode(onOff, mode, status) {
	if ("STATUS_OFFLINE" == status) {
		showOfflineMessage();
		return false;
	}
	if ("关" == onOff) {
		setStatus('2');
	} else {
		switch (mode) {
		case '制冷':
			setStatus('4');
		case '制热':
			setStatus('5');
			break;
		case '送风':
			setStatus('7');
			break;
		case '抽湿':
			setStatus('6');
			break;
		default:
			setStatus('2');
			break;
		}
	}
}
function setStatus(mode) {
	switch (mode) {
	case '2':
		turnOffDevice();
		break;
	case '3':
		turnOnDevice();
		break;
	case '4':
		changestatus();
		break;
	case '5':
		changestatus();
		break;
	case '7':
		changestatus();
		disableTemp();
		break;
	case '6':
		defaultcolor();
		choushi();
		break;
	}
}
function turnOnDevice() {
	// setStatus(modeStatusNum);
	defaultcolor();
}
function turnOffDevice() {
	modelDisable = true;
	choushi();
	disableTemp();
	for ( var i = 0; i < 4; i++) {
		$("#pattern-select-items").find("div:eq(" + i + ")").css("color",
				"#ADADAD");
	}
}
function disableTemp() {
	$("#temp_turn_left,#temp_turn_right").addClass("defaultColor");
	tempDisable = true;
}
function changestatus() {
	defaultcolor();
	$("#direction-select-items").find("div:eq(1)").css("color", "#ADADAD");
	disableNum = 24;
}
function defaultcolor() {
	$("#temp_turn_left,#temp_turn_right").removeClass("defaultColor");
	disableNum = "";
	tempDisable = false;
	modelDisable = false;
	for ( var i = 0; i < 4; i++) {
		$("#speed-select-items").find("div:eq(" + i + ")").css("color",
				"#FFFFFF");
		$("#direction-select-items").find("div:eq(" + i + ")").css("color",
				"#FFFFFF");
		$("#pattern-select-items").find("div:eq(" + i + ")").css("color",
				"#FFFFFF");
	}
//	$("#temp_turn_left,#temp_turn_right").css("color", "#FFFFFF");
}
function choushi() {
	disableNum = "all";
	for ( var i = 0; i < 4; i++) {
		$("#speed-select-items").find("div:eq(" + i + ")").css("color",
				"#ADADAD");
		$("#direction-select-items").find("div:eq(" + i + ")").css("color",
				"#ADADAD");
	}
}
function showMsg() {
	$("#msgs").css("display", "");
	setTimeout(function() {
		$("#msgs").css("display", "none");
	}, 3000);
}
function viewUserList(openId, familyId) {
	pages = 1;
	$('#ajax_loader').show();
	$("#userlist").css("height",window.innerHeight + "px");
	// usersModel.users.removeAll();
	$
			.get(
					"http://" + window.location.hostname + ":"
							+ window.location.port
							+ "/weixinAir/GetUsingSameDeviceServlrt?openId="
							+ openId + "&familyId=" + familyId + "&page=1",
					function(result) {
						$('#ajax_loader').hide();
						usersModel.users(result.users);
						$("#numOfUsers").text(result.numOfUsers);
						userLen = result.users.length;
						/*$("#moreUser").html("上拉加载更多。。。");
						
						$("#moreUser").remove();
						var html = "<div id=\"moreUser\" class=\"center\" onClick=\"getMoreUser('"
								+ openId
								+ "','"
								+ familyId
								+ "')\">加载更多用户</div>";
						if (userLen <= 0) {
							html = "<div id=\"moreUser\" class=\"center\" style=\"color:red;\">无已绑定用户</div>";
						}

						$("#scroller").append(html);*/
						if ( userLen < 0 || userLen == 0 ) {
							$("#moreUser").unbind();
							/*$("#moreUser").html("<span style=\"color:red;\">已无绑定用户</span>");*/
							$("#moreUser").html("");
						}else{
							$("#moreUser").html("上拉加载更多...");
							$("#moreUser").unbind().bind("click",function(){
								getMoreUser(openId,familyId);
							});
						}
						
						
					});
	$("#binding-ListPage").modal("hide");
	navigateToPage($("#userlist"));
}
function getMoreUser(openId, familyId) {
	pages = pages + 1;
	if (!backUsr) {
		return false;
	}
	$("#moreUser").html("正在加载用户列表，请稍候...");
	backUsr = false;
	$
			.get(
					"http://" + window.location.hostname + ":"
							+ window.location.port
							+ "/weixinAir/GetUsingSameDeviceServlrt?openId="
							+ openId + "&familyId=" + familyId + "&page="
							+ pages,
					function(result) {
						// usersModel.users(result.users);
						backUsr = true;
						if (result.users.length > 0) {
							userLen = userLen + result.users.length;
							$.each(result.users, function(i, item) {
								usersModel.users.push(item);
							});
							/*var html = "<div id=\"moreUser\" class=\"center\" onClick=\"getMoreUser('"
									+ openId
									+ "','"
									+ familyId
									+ "')\">加载更多</div>";
							$("#user-binding").append(html);*/
							$("#moreUser").html("上拉加载更多...");
						} else {
							pages = 1;
							$("#moreUser").unbind();
							$("#moreUser").html("<span style=\"color:red;\">已无绑定用户</span>");
						}
					});
}
function controlDevice(openId, familyId, type, status) {
	if ("STATUS_OFFLINE" == status) {
		showOfflineMessage();
		return false;
	}
	air_condition_point(openId, familyId, type);
}
function turndevice(openId, mac, type, status) {
	if (type === 'AirCondition') {
		if ("STATUS_OFFLINE" == status) {
			showOfflineMessage();
			return false;
		}
		var classs = $(this).attr("class");
		var restatus = $("#" + mac).attr("name");
		var url = "http://"
				+ window.location.hostname
				+ ":"
				+ window.location.port
				+ "/weixinAir/DeviceControl?method=do_device_cmd&isVirtualDevice=0&optNo="
				+ restatus + "&mac=" + mac + "&openid=" + openId;
		$.post(url, function(result) {
			var json = eval(result);
			var success = json.success;
			var msg = json.msg;
			if (msg = "成功") {
				if (restatus == 3) {
					$("#" + mac).attr("name", 2);
					$("#" + mac).html("√已启用");
					$("#" + mac).removeClass("turnoff").addClass("turnon");
				} else {
					$("#" + mac).attr("name", 3);
					$("#" + mac).html("未启用");
					$("#" + mac).removeClass("turnon").addClass("turnoff");
				}
			}
		});
	} else {
		return false;
	}

}

function changeConditionStatus(opt_no) {
	switch (opt_no) {
	case '2':
		viewModel.conditionStatus.onOff("关");
		break;
	case '3':
		viewModel.conditionStatus.onOff("开");
		break;
	case '4':
		viewModel.conditionStatus.mode("制冷");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置制冷中...");
		break;
	case '5':
		viewModel.conditionStatus.mode("制热");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置制热中...");
		break;
	case '6':
		viewModel.conditionStatus.mode("抽湿");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置抽湿中...");
		break;
	case '7':
		viewModel.conditionStatus.mode("送风");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置送风中...");
		break;
	case '12':
		viewModel.conditionStatus.windSpeed("自动");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置自动中...");
		break;
	case '11':
		viewModel.conditionStatus.windSpeed("低风");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置低风中...");
		break;
	case '10':
		viewModel.conditionStatus.windSpeed("中风");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置中风中...");
		break;
	case '9':
		viewModel.conditionStatus.windSpeed("高风");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置高风中...");
		break;
	case '20':
		viewModel.conditionStatus.windDirection("上下");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置上下中...");
		break;
	case '24':
		viewModel.conditionStatus.windDirection("左右");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置左右中...");
		break;
	case '22':
		viewModel.conditionStatus.windDirection("全向");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置全向中...");
		break;
	case '23':
		viewModel.conditionStatus.windDirection("定向");
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置定向中...");
		break;
	case '14':
		/*if(viewModel.conditionStatus.healthy()){
			viewModel.conditionStatus.healthy(false);
		}else{
			viewModel.conditionStatus.healthy(true);
		}*/
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置健康中...");
		break;
	case '30':
		/*if(viewModel.conditionStatus.touching()){
			viewModel.conditionStatus.touching(false);
		}else{
			viewModel.conditionStatus.touching(true);
		}*/
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置感人中...");
		break;
	case '33':
		/*if(viewModel.conditionStatus.sleep()){
			viewModel.conditionStatus.sleep(false);
		}else{
			viewModel.conditionStatus.sleep(true);
		}*/
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置睡眠中...");
		break;
	case '18':
		/*if(viewModel.conditionStatus.addOxygen()){
			viewModel.conditionStatus.addOxygen(false);
		}else{
			viewModel.conditionStatus.addOxygen(true);
		}*/
		$("#indoorTemp").hide();
		$("#controlTips").text("正在设置加氧中...");
		break;
	/*case viewModel.conditionStatus.airFuncOff().toString():
		viewModel.conditionStatus.airFunction("无");
		break;*/
	}
}

$(function() {

	ko.applyBindings(usersModel, document.getElementById('user-binding'));

	$("#managerUser").click(
			function() {
				$('#ajax_loader').show();
				$.get("http://" + window.location.hostname + ":"
						+ window.location.port
						+ "/weixinAir/GetUsingSameDeviceServlrt?openId="
						+ $("#openId").val() + "&familyId="
						+ $("#control_familyid").val() + "page=1", function(
						result) {
					$('#ajax_loader').hide();
					usersModel.users(result.users);
				});

				navigateToPage($("#userlist"));
			});

	// 空调控制类
	var Device_Control = new air_base_cmd("DeviceControl", $("#base_parameter")
			.serialize()); // $("#base_parameter").serialize()
	
	// 载入微信用户
	// view_entity.view_base_load();
	// 减少温度控制
	$("#temp_turn_left").bind("click", function() {
		var container = $(this).siblings(".content").find("#temp");
		var current_temp = parseInt(container.text());
		if (tempDisable)
			return;
		if (current_temp > 16) {
			Device_Control.base_set_temp(container, current_temp, "left");
			container.text(current_temp-1);
			$("#indoorTemp").hide();
			$("#controlTips").text("正在设置"+(current_temp-1)+"℃中...");
		} else {
			return false;
		}
	});

	// 增加温度控制
	$("#temp_turn_right").bind("click", function() {
		var container = $(this).siblings(".content").find("#temp");
		var current_temp = parseInt(container.text());
		if (tempDisable) {
			showMsg();
			return false;
		}
		if (current_temp < 30) {
			Device_Control.base_set_temp(container, current_temp, "right");
			container.text(current_temp+1);
			$("#indoorTemp").hide();
			$("#controlTips").text("正在设置"+(current_temp+1)+"℃中...");
		} else {
			return false;
		}
	});

	// 模式选择
	/*$("#pattern-select-items").find("div").each(function(i, item) {
		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			// $("#pattern-select-items").find("div").removeClass("selected");
			// $("#pattern-select-items").find("div").addClass("unselected");
			// $this.addClass("selected");
			if (modelDisable == true) {
				showMsg();
				return false;
			}
			modeStatusNum = opt_no;
			changeConditionStatus(opt_no);
			setStatus(opt_no);
			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});
	});*/
	$(".model-select").find("div").each(function(i,item){
		$(item).unbind().bind("click",function(){
			var $this = $(this);
			var opt_no = $this.find('input').val();
			if (modelDisable == true) {
				showMsg();
				return false;
			}
			modeStatusNum = opt_no;
			changeConditionStatus(opt_no);
			setStatus(opt_no);
			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});
	});
	// 风速选择
	/*$("#speed-select-items").find("div").each(function(i, item) {
		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			if ("all" == disableNum) {
				showMsg();
				return false;
			}
			// $("#speed-select-items").find("div").removeClass("selected");
			//
			// $("#speed-select-items").find("div").addClass("unselected");
			// $this.addClass("selected");
			changeConditionStatus(opt_no);
			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});

	});*/
	$(".speed-set").find("div").each(function(i, item) {
		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			if ("all" == disableNum) {
				showMsg();
				return false;
			}
			// $("#speed-select-items").find("div").removeClass("selected");
			//
			// $("#speed-select-items").find("div").addClass("unselected");
			// $this.addClass("selected");
			changeConditionStatus(opt_no);
			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});

	});
	// 风向选择
	/*$("#direction-select-items").find("div").each(function(i, item) {

		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			if ("all" == disableNum || opt_no == disableNum) {
				showMsg();
				return false;
			}
			// $("#direction-select-items").find("div").removeClass("selected");
			// $("#direction-select-items").find("div").addClass("unselected");
			// $this.addClass("selected");

			changeConditionStatus(opt_no);

			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});

	});*/
	$(".wind-direction-select").find("div").each(function(i, item) {
		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			if ("all" == disableNum || opt_no == disableNum) {
				showMsg();
				return false;
			}
			changeConditionStatus(opt_no);

			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});

	});
	
	$(".function-select").find("div").each(function(i, item) {
		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			if ("all" == disableNum || opt_no == disableNum) {
				showMsg();
				return false;
			}
			changeConditionStatus(opt_no);
			var opt;
			switch (opt_no)
			{
			case "14":
				if(viewModel.conditionStatus.healthy()){
					viewModel.conditionStatus.healthy(false);
					opt = "15";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在关闭健康模式中...");
				}else{
					viewModel.conditionStatus.healthy(true);
					opt = "14";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在开启健康模式中...");
				}
				break;
			case "30":
				if(viewModel.conditionStatus.touching()){
					viewModel.conditionStatus.touching(false);
					opt = "31";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在关闭感人模式中...");
				}else{
					viewModel.conditionStatus.touching(true);
					opt = "30";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在开启感人模式中...");
				}
				break;
			case "33":
				if(viewModel.conditionStatus.sleep()){
					viewModel.conditionStatus.sleep(false);
					opt = "34";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在关闭睡眠模式中...");
				}else{
					viewModel.conditionStatus.sleep(true);
					opt = "33";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在开启睡眠模式中...");
				}
				break;
			case "18":
				if(viewModel.conditionStatus.addOxygen()){
					viewModel.conditionStatus.addOxygen(false);
					opt = "19";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在关闭加氧模式中...");
				}else{
					viewModel.conditionStatus.addOxygen(true);
					opt = "18";
					$("#indoorTemp").hide();
					$("#controlTips").text("正在开启加氧模式中...");
				}
				break;
			default:
				opt = opt_no;
				break;
			}

			// 执行命令请求
			Device_Control.base_set_orders(opt);
		});

	});
	
	// 开 关
	/*$("#air-on-off-select-items").find("div").each(function(i, item) {
		$(item).bind("click", function() {
			// optNo
			var $this = $(this);
			var opt_no = $this.find('input').val();
			// $("#air-on-off-select-items").find("div").removeClass("selected");
			// $("#air-on-off-select-items").find("div").addClass("unselected");
			// $this.addClass("selected");
			setStatus(opt_no);
			changeConditionStatus(opt_no);
			// 执行命令请求
			Device_Control.base_set_orders(opt_no);
		});
	});*/
	$("#turnOnDeviceBtn,#turnOffDeviceBtn").click(function(event){
		$(".temp-line").show();
		$(".control-area>div").hide();
		$(".function-selection>span").removeClass("active");
//		control_line_color();
		var opt_no = $(this).next("input").val();
		setStatus(opt_no);
		changeConditionStatus(opt_no);
		// 执行命令请求
		Device_Control.base_set_orders(opt_no);
		control_line_color(controlLineType[1]);
	});

});

/*
 * view_entity view_base_event：用于取消微信的导航栏以及工具栏 view_base_load：将绑定设备的微信用户载入
 */
var parameter;
var view_entity = {
	// 将绑定设备的微信用户载入
	view_base_load : function() {

		var base_action = "DeviceControl?method=do_user_lst";
		var parameter = $("#base_parameter").serialize();

		ajax_base_cmd(
				base_action,
				parameter,
				"false",
				"get",
				function(e) {
					// 错误捕捉
				},
				function() {
					// 加载中
				},
				function(data) {
					// 执行成功
					if (data.users.length > 0) {
						var html = "";

						$
								.each(
										data.users,
										function(i, item) {
											html += "<div class=\"user-element\">";
											html += "<div class=\"avatar\"><img  style=\"width:35px;height:35px;margin-left:10px;padding:0px;\" src="
													+ item.headimgurl
													+ " alt=\"我是头像君\" /></div>";
											html += "<div class=\"baseinfo\">";
											html += "<span class=\"username\">韩梅梅</span><br /><span class=\"user-reg-date\">添加于&nbsp;<span>2014/04/14</span></span>";
											html += "</div>";
											html += "<div class=\"user-enable\">";
											html += "<img src=\"images/stop.png\" alt=\"删除\" /> <span class=\"enable\">禁用</span>";
											html += "</div>";
											html += "</div>";
										});

						$("#wechat-user-lst").empty().append(html);
					}
				});

	}

};

/**
 * 用于抽象出空调的控制
 * 
 * @param base_action
 * @param base_parameter
 * @returns
 */
// 基础空调控制类
function air_base_cmd(base_action, base_parameter) {
	this.action = base_action;
	this.parameter = base_parameter;
};
// 基础控制
air_base_cmd.prototype.base_control = function(action, parameter, callback) {
	// base cmd
	ajax_base_cmd(action, parameter, "false", "post", function(e) {
		// error handler
		$("#controlTips").text("操作完毕!");
		$("#indoorTemp").hide();
	}, function() {
		// beforeSend...
		// 注：若使用全局性质的ajaxStart等操作，此处可以不写。
	}, function(data) {
		callback(data);
	});
};
// 控制温度
air_base_cmd.prototype.base_set_temp = function(container, temp, status) {
	// set temp
	/*
	 * container 承接层 temp 温度
	 */
	switch (status) {
	case "left":
		temp--;
		break;
	case "right":
		temp++;
		break;
	}

	var request_action = this.action
			+ "?method=do_device_cmd&optNo=13&devOptArgs=" + temp + "&mac="
			+ $("#control_mac").val() + "&openid=" + $("#openId").val()
			+ "&isVirtualDevice=0";
	this.base_control(request_action, this.parameter, function(data) {
		// 如果执行成功
		if (data.success) {
			// 更改温度值
			viewModel.conditionStatus.temp(temp);
			$("#indoorTemp").hide();
			$("#controlTips").text("操作完毕！");
		} else {
			return false;
		}
	});
};
// 发送其他指令
air_base_cmd.prototype.base_set_orders = function(mode) {
	hasChange = true;
	updown = mode;
	// set pattern
	var request_action = this.action + "?method=do_device_cmd&optNo=" + mode
			+ "&mac=" + $("#control_mac").val() + "&openid="
			+ $("#openId").val() + "&isVirtualDevice=0";
	this
			.base_control(
					request_action,
					this.parameter,
					function(data) {
						setTimeout(
								"viewModel.load_conditionStatus($('#openId').val(),$('#control_mac').val())",
								1500);
					});
};
/*
 * air_base_cmd.prototype.base_set_speed=function(){ //set speed };
 * air_base_cmd.prototype.base_set_direction=function(){ //set direction };
 * 
 */

function unbinding(openid, familyId) {
	for(var i=0; i<viewModel.bindUserAccountViewModel.accounts().length; i++){
		if(viewModel.bindUserAccountViewModel.accounts()[i].familyId == familyId){
			if(viewModel.bindUserAccountViewModel.accounts()[i].device.length>0){
				eachHasDevice = true;
			}else{
				eachHasDevice = false;
			}
		}
	}
	$("#confirm").modal('show');
	$("#confirmunbinding").unbind('click').bind('click',
			function() {
				$('#ajax_loader').show();
				$.get("http://" + window.location.hostname + ":"
						+ window.location.port
						+ "/weixinAir/UnbindFamilyServlet?familyId=" + familyId
						+ "&openId=" + openid, function(dataResult) {
					$('#ajax_loader').hide();
					if (dataResult.result == "1") {
						hasDevice = dataResult.hasDevice;

						isVirtualOnly = !hasDevice?1:isVirtualOnly;
						
						if( eachHasDevice ){
							cancel_cdOptimize();
							/*强制变换数值*/
							viewModel.indoorViewModel.pm25(60);
							viewModel.indoorViewModel.temp(23);
							viewModel.indoorViewModel.hcho(0.03);
							viewModel.indoorViewModel.wet(30);
							viewModel.indoorViewModel.voc(50);
							/*end*/
						}
						$("#controlMsg").text("解绑成功！");
						$("#sure").modal('show');
						$("#toPage").unbind('click').bind('click',function() {
							$('#sure').modal('hide');
							 getDeviceList(function(){if (currentPage[0].id !== 'fullBindingList') {
									$(
									"#binding-ListPage")
									.modal(
											"show");
						}});
						});
						
//						$('#sure').on('hide.bs.modal', function(e) {
//							getDeviceList();
//						});
					} else {
						$("#binload").css("display", "none");
						$("#controlMsg").text("解绑失败！");
						$("#toPage").unbind('click').bind('click',function() {
							$('#sure').modal('hide');
						});
					}
				});
			});
};

function unbindingOther(openid, familyId) {
	$("#confirmOther").modal('show');
	$("#confirmunbindingOther")
			.click(
					function() {
						$('#ajax_loader').show();
						$
								.get(
										"http://"
												+ window.location.hostname
												+ ":"
												+ window.location.port
												+ "/weixinAir/UnbindFamilyServlet?familyId="
												+ familyId + "&openId="
												+ openid,
										function(dataResult) {
											$('#ajax_loader').hide();
											if (dataResult.result == "1") {
												$("#controlMsg").text("解绑成功！");
												$("#sure").modal('show');
												$("#toPage").unbind('click').bind('click',function() {
																	$
																			.get(
																					"http://"
																							+ window.location.hostname
																							+ ":"
																							+ window.location.port
																							+ "/weixinAir/GetUsingSameDeviceServlrt?openId="
																							+ $(
																									"#openId")
																									.val()
																							+ "&familyId="
																							+ familyId
																							+ "&page=1",
																					function(
																							result) {
																						usersModel
																								.users(result.users);
																						$("#numOfUsers").text(result.numOfUsers);
																						$("#moreUser").unbind().bind("click",function(){
																							getMoreUser(openId,familyId);
																						});
																					});
																});
											} else {
												$("#controlMsg").text("解绑失败！");
												$("#toPage").unbind('click').bind('click',function() {
													$('#sure').modal('hide');
												});
											}
										});
					});
}

/**
 * 对jQuery的ajax操作进行二次封装
 */
// 封装一下Jquery的AJAX操作。
var ajax_base_cmd = function(url, parameter, async, type, error_callback,
		beforeSend_callback, success_callback) {
	
	changeControl = true;

	var url = (url == null || url == "" || typeof (url) == "undefined") ? ""
			: url;
	var parameter = (parameter == null || parameter == "" || typeof (parameter) == "undefined") ? ""
			: parameter;
	var async = (async == null || async == "" || typeof (async) == "undefined") ? "false"
			: async;
	var type = (type == null || type == "" || typeof (type) == "undefined") ? "post"
			: type;

	$.ajax({
		url : url,
		data : parameter,
		async : async,
		type : type,
		error : function(e) {
			if (error_callback) {
				error_callback(e);
			}
		},
		beforeSend : function() {
			if (beforeSend_callback) {
				beforeSend_callback();
			}
		},
		success : function(data) {
			if (success_callback) {
				success_callback(data);
			}
		}

	});
};

function closeDevice() {
	if(window.location.href.indexOf("page=mydevice") > -1) {
		return false;
	}
	if(changeControl){
		cancel_cdOptimize();
		/*强制变换数值*/
		viewModel.indoorViewModel.pm25(60);
		viewModel.indoorViewModel.temp(23);
		viewModel.indoorViewModel.hcho(0.03);
		viewModel.indoorViewModel.wet(30);
		viewModel.indoorViewModel.voc(50);
		/*end*/
	}
	$("#controlTips").text("");
	$("#indoorTemp").show();
	viewModel.indoorViewModel.tips("正在调整设备数据…");
	navigateToPage($("#inDoor"));
	viewModel.loadIndoor($("#openId").val(), isVirtualOnly);

	hasChange = false;
	updown = 0;
}