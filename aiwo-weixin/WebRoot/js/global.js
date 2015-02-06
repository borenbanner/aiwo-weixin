$(document).ready(function() {
	var angle = 0;
	setInterval(function() {
		angle += 30;
		$(".loading,#preloading,#loading").rotate(angle);
	}, 100);

	window.onpopstate = function(e) {
		switch (e.state.action) {
		case "main":
			navigateToPage($('#main'));
			optimizeAnimation(viewModel.mainViewModel.outDoorHai(), 0, 360, ".angle_2", ".angle_1", "#circle_3");
			break;
		case "near":
			navigateToPage($('#near'));
			optimizeAnimation(viewModel.nearViewModel.hai(), 0, 360,
					".nearAangle_2", ".nearAngle_1", "#nearCircle_3");
			break;
		case "indoor":
			navigateToPage($('#inDoor'));
			break;
		default:
			navigateToPage('#main');
			optimizeAnimation(viewModel.mainViewModel.outDoorHai(), 0, 360, ".angle_2", ".angle_1", "#circle_3");
			break;
		}
	};

});

$(document).ajaxSend(function(event, request, settings) {

	// if (!$("#preload, #binload,#binding-ListPage").is(":visible")) {
	// $('#ajax_loader').show();
	// }

	// if (!$("#main, #near,#inDoor,#preload").is(":visible")) {
	// $('#ajax_loader').modal({
	// backdrop : 'static'
	// });
	// } else {
	// $(".loading").css("display", "inline");
	// viewModel.mainViewModel.tips("正在加载...");
	// viewModel.nearViewModel.tips("正在加载...");
	// viewModel.indoorViewModel.tips("正在加载...");
	// }

});

$(document).ajaxStop(function(event, request, settings) {
	// $('#ajax_loader').hide();
	// $(".loading").css("display", "none");
	// $('#ajax_loader').modal('hide');
});

var currentPage;
var prePage;
var hasDevice;

function navigateToPage(page) {
	if (typeof (page) == "undefined") {
		page = $("#main");
		viewModel.loadMain($("#openId").val(), false);
	}
	
	if (currentPage[0].id === page[0].id) {
		if (typeof (isVirtualOnly) != "undefined" && isVirtualOnly == 1) {
			cancel_cdOptimize();
			/* 强制变换数值 */
			viewModel.indoorViewModel.pm25(60);
			viewModel.indoorViewModel.temp(23);
			viewModel.indoorViewModel.hcho(0.03);
			viewModel.indoorViewModel.wet(30);
			viewModel.indoorViewModel.voc(50);
			/* end */
		}
		return;
	}
	switch (page[0].id) {
	case "main":
//		if (currentPage[0].id == "preload") {
//			currentPage.fadeToggle("slow");
//			page.fadeToggle("slow");
//		} else {
//			currentPage.slideToggle();
//			page.slideToggle();
//		}
		
		currentPage.fadeToggle("slow");
		page.fadeToggle("slow");

		var title = "室外空气质量";
		if ($('#subscribe').val() != 2) {
			title = $("#nickName").val() + "家室外空气质量";
		}
		$(document).attr("title", title);
		prePage = currentPage;
		currentPage = page;
		break;
	case "near":
//		if (currentPage[0].id == "main") {
//			currentPage.fadeToggle("slow");
//			page.fadeToggle("slow");
//		} else {
//			currentPage.slideToggle();
//			page.slideToggle();
//		}
		currentPage.fadeToggle("slow");
		page.fadeToggle("slow");
		var title = "室内空气质量";
		if ($('#subscribe').val() != 2) {
			title = "朋友家室内空气质量";
		}
		$(document).attr("title", title);
		prePage = currentPage;
		currentPage = page;
		if (history.state !== null && history.state.action != "near") {
			var state = {
				action : "near",
				title : null,
				url : window.location.href
			};
			console.log('innnnn');
			history.pushState(state, null, window.location.href);
		}
		break;
	case "inDoor":
		currentPage.fadeToggle("slow");
		page.fadeToggle("slow");
		var title = "室内空气质量";
		if ($('#subscribe').val() != 2) {
			title = $("#nickName").val() + "家室内空气质量";
		}
		$(document).attr("title", title);
		$(".goto_icon,#in4rotate").unbind('click').bind('click', function(e) {
			if (!checkPermission()) {
				return;
			}
			beforeOptimizeValue = $("#myHai").text();
			optimize();
			return false;
		});
		prePage = currentPage;
		currentPage = page;
		if (history.state !== null && history.state.action != "indoor") {
			var state = {
				action : "indoor",
				title : null,
				url : window.location.href
			};
			history.pushState(state, null, window.location.href);
		}
		break;
	case "condition_Modal":
		$("#condition_Modal").modal("show");
		// currentPage.slideToggle();
		// page.slideToggle();
		// $(document).attr("title", "商品展示");
		break;
	case "pk":
		currentPage.slideToggle();
		page.slideToggle();
		var title = "室内空气质量";
		if ($('#subscribe').val() != 2) {
			title = $("#nickName").val() + "战胜了朋友";
		}
		$(document).attr("title", title);
		prePage = currentPage;
		currentPage = page;
		break;
	case "binding-AccoutPage":
		currentPage.slideToggle();
		page.slideToggle();
		$(document).attr("title", "绑定账号");
		prePage = currentPage;
		currentPage = page;
		break;
	case "binding-ListPage":
		currentPage.slideToggle();
		page.slideToggle();
		$(document).attr("title", "管理我的家电");
		prePage = currentPage;
		currentPage = page;
		break;
	case "air_condition_control":
		currentPage.slideToggle();
		page.slideToggle();
		$(document).attr("title", "室内空气质量");
		prePage = currentPage;
		currentPage = page;
		break;
	case "airbox":
		currentPage.slideToggle();
		page.slideToggle();
		$("#unbindbox").hide();
		$(document).attr("title", "室内空气质量");
		prePage = currentPage;
		currentPage = page;
		break;
	case "allknow":
		currentPage.slideToggle();
		page.slideToggle();
		$("#unbindknow").hide();
		$(document).attr("title", "室内空气质量");
		prePage = currentPage;
		currentPage = page;
		break;
	case "downlist":
		currentPage.slideToggle();
		page.slideToggle();
		$(document).attr("title", "下载列表");
		prePage = currentPage;
		currentPage = page;
		break;
	default:
		currentPage.slideToggle();
		page.slideToggle();
		prePage = currentPage;
		currentPage = page;
	}

	setHeight();
	scroll(0, 0);

}

function aqiAnim(aqi) {
	updateHai("out_door_hai", aqi);
	optimizeAnimation(aqi, 0, 360, ".angle_2", ".angle_1", "#circle_3");
}

function setHeight() {
	$(".bg ").height($(".bg:visible").width() * 1.0734);
	$(".circle").height($(".circle:visible").width());

	// $(".bar").css("margin-top", $(".bg:visible").width() * 1.0734 - 29);
}

function updateHai(id, endValue) {
	var goUpdate = new countUp(id, Number($("#" + id).text()),
			Number(endValue), 0, 2.2);
	goUpdate.start();
}

function controlDevice(o) {
	var mac = o.mac;
	var openId = $("#openId").val();
	var optNo = o.status == "STATUS_OFFLINE" ? 3 : 2;
	var url = "http://"
			+ window.location.hostname
			+ ":"
			+ window.location.port
			+ "/weixinAir/DeviceControl?method=do_device_cmd&isVirtualDevice=0&optNo="
			+ optNo + "&mac=" + mac + "&openid=" + openId;

	$.post(url, function(result) {

		if (optNo == 2) {
			o.status = "STATUS_OFFLINE";
			$("[mac='" + mac + "']:visible").removeClass("trueon").addClass(
					"trueoff").text("未启用");
		} else {
			o.status = "STATUS_ONLINE";
			$("[mac='" + mac + "']:visible").removeClass("trueoff").addClass(
					"trueon").text("√已启用");
		}

	});
}

function showOfflineMessage() {
	if (!checkPermission()) {
		return;
	}
	$("#overlay-msg").text("哎呀！没找到设备，是不是没有插电源?请确认后再试");
	$("#overlay").modal('show');
}

function closePK() {
	window.location.href = 'http://' + window.location.hostname + ':'
			+ window.location.port
			+ '/weixinAir/haierservice?page=indoor&openId='
			+ $("#openId").val();
}

function fRandomBy(under, over) {

	switch (arguments.length) {

	case 1:
		return parseInt(Math.random() * under + 1);

	case 2:
		return parseInt(Math.random() * (over - under + 1) + under);

	default:
		return 0;

	}
}
