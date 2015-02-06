$(function() {
	var method = "444";

	$
			.ajax({
				type : "post",
				url : "managerServlet",
				data : {
					"method" : method,
					"userId" : $('input[name=userId]').val()
				},
				dataType : "json",
				cache : false,
				success : function(data) {
					var obj = data;
					var pm = obj[0].pm;
					pm = pm.indexOf(".") != -1 ? pm.substring(0, pm
							.indexOf(".")) : pm;
					$("#pm").text(pm);
					$("#pm2").text(pm);
					var wd = obj[0].wd;
					wd = wd.indexOf(".") != -1 ? wd.substring(0, wd
							.indexOf(".") + 2) : wd;
					$("#wd").text(wd);
					var sd = obj[0].sd;
					sd = sd.indexOf(".") != -1 ? sd.substring(0, sd
							.indexOf(".") + 2) : sd;
					$("#sd").text(sd);
					var lux = obj[0].lux;
					lux = lux.indexOf(".") != -1 ? lux.substring(0, lux
							.indexOf(".") + 2) : lux;
					$("#lux").text(lux);
					circleAdaptation();

					var devieList = "";
					var managerList = "";
					$
							.each(
									obj,
									function(i, n) {

										devieList += "<button  class=\"btn btn-primary\" style=\"width: 80%; margin-top: 20px;\" onclick=\"convert('"
												+ n.mac
												+ "',this)\">"
												+ "<img src=\"image/haw_22.png\" style=\"width: 20px; display: inline;\" alt=\"\ class=\"img-rounded img-responsive\">"
												+ n.deviceName + "</button>";
										managerList += "<div class=\"deviceList\"><img src=\"image/haw_22.png\" style=\" float:left ;width: 20px; display: inline;\" class=\"img-rounded img-responsive\">"
												+ "<span onbeforeeditfocus=\"this.oldData=this.firstChild.data\" onblur=\"this.oldData==this.firstChild.data?void(0):updateDeviceName('"
												+ n.macId
												+ "',this);\" onclick=\"update(this);\" style=\"float: left;\">"
												+ n.deviceName
												+ "</span>"
												+ "<img src=\"image/del.png\" alt=\"\" class=\"img-rounded img-responsive managerBar\" onclick=\"deletedevice('"
												+ n.macId + "',this);\"></div>";
										;
									});
					$("#deviceListShow").empty().append(devieList);
					$("#managerList").empty().append(managerList);

					autoBackground(pm);
					$("#showOther").bind("click", function() {
						$("#noEquipment").modal("show");
					});
					$("#managerModel").bind("click", function() {
						$("#manager").modal("show");
					});
				}
			});
});

function autoBackground(param) {
	var pm = parseInt(param);
	if (pm > -1 && pm < 61) {
		$(".bg").addClass("air_outstanding");
		$("#modal-header-color").addClass("air_outstanding");
		$("#modal-header-color-2").addClass("air_outstanding");
		$(".modal-body  button").addClass("air_outstanding");
		$(".air-quality-text-end").text("优秀");
	} else if (pm > 60 && pm < 121) {
		$(".bg").addClass("air_good");
		$("#modal-header-color").addClass("air_good");
		$("#modal-header-color-2").addClass("air_good");
		$(".modal-body  button").addClass("air_good");
		$(".air-quality-text-end").text("良好");
	} else if (pm > 120 && pm < 181) {
		$(".bg").addClass("air_light_pollution");
		$("#modal-header-color").addClass("air_light_pollution");
		$("#modal-header-color-2").addClass("air_light_pollution");
		$(".modal-body  button").addClass("air_light_pollution");
		$(".air-quality-text-end").text("轻度污染");

	} else if (pm > 180 && pm < 241) {
		$(".bg").addClass("air_moderate_pollution");
		$("#modal-header-color").addClass("air_moderate_pollution");
		$("#modal-header-color-2").addClass("air_moderate_pollution");
		$(".modal-body  button").addClass("air_moderate_pollution");
		$(".air-quality-text-end").text("中度污染");

	} else if (pm > 240 && pm < 301) {
		$(".bg").addClass("air_severe_pollution");
		$("#modal-header-color").addClass("air_severe_pollution");
		$("#modal-header-color-2").addClass("air_severe_pollution");
		$(".modal-body  button").addClass("air_severe_pollution");
		$(".air-quality-text-end").text("重度污染");
	} else {
		$(".bg").addClass("air_serious_pollution");
		$("#modal-header-color").addClass("air_serious_pollution");
		$("#modal-header-color-2").addClass("air_serious_pollution");
		$(".modal-body  button").addClass("air_serious_pollution");
		$(".air-quality-text-end").text("严重污染");
	}
}

function convert(mac, obj) {
	$("#noEquipment").modal("hide");
	$(".bg").removeClass().addClass("bg");
	$(".modal-header").removeClass().addClass("modal-header");
	$
			.ajax({
				type : "post",
				url : "showDeviceSingle",
				data : {
					"userId" : $("input[name=userId]").val(),
					"mac" : mac
				},
				dataType : "json",
				success : function(data) {
					var obj = data;
					var pm = obj[0].pm;
					pm = pm.indexOf(".") != -1 ? pm.substring(0, pm
							.indexOf(".")) : pm;
					$("#pm").text(pm);
					$("#pm2").text(pm);
					var wd = obj[0].wd;
					wd = wd.indexOf(".") != -1 ? wd.substring(0, wd
							.indexOf(".") + 2) : wd;
					$("#wd").text(wd);
					var sd = obj[0].sd;
					sd = sd.indexOf(".") != -1 ? sd.substring(0, sd
							.indexOf(".") + 2) : sd;
					$("#sd").text(sd);
					var lux = obj[0].lux;
					lux = lux.indexOf(".") != -1 ? lux.substring(0, lux
							.indexOf(".") + 2) : lux;
					$("#lux").text(lux);
					circleAdaptation();

					var devieList = "";
					var managerList = "";
					$
							.each(
									obj,
									function(i, n) {

										devieList += "<button  class=\"btn btn-primary\" style=\"width: 80%; margin-top: 20px;\" onclick=\"convert('"
												+ n.mac
												+ "')\">"
												+ "<img src=\"image/haw_22.png\" style=\"width: 20px; display: inline;\" alt=\"\ class=\"img-rounded img-responsive\">"
												+ n.deviceName + "</button>";
										managerList += "<div class=\"deviceList\"><img src=\"image/haw_22.png\" style=\" float:left ;width: 20px; display: inline;\" class=\"img-rounded img-responsive\">"
												+ "<span onbeforeeditfocus=\"this.oldData=this.firstChild.data\" onblur=\"this.oldData==this.firstChild.data?void(0):updateDeviceName('"
												+ n.macId
												+ "',this);\" onclick=\"update(this);\" style=\"float: left;\">"
												+ n.deviceName
												+ "</span>"
												+ "<img src=\"image/del.png\" alt=\"\" class=\"img-rounded img-responsive managerBar\" onclick=\"deletedevice('"
												+ n.macId + "',this);\"></div>";
										;
									});
					$("#deviceListShow").empty().append(devieList);
					$("#managerList").empty().append(managerList);

					autoBackground(pm);
					$("#showOther").bind("click", function() {
						$("#noEquipment").modal("show");
					});
					$("#managerModel").bind("click", function() {
						$("#manager").modal("show");
					});

				}
			});
}

function update(obj) {

	$(obj).attr("contenteditable", true);

}

function updateDeviceName(mac, obj) {
	var macId = mac;
	var method = "222";
	var macName = $(obj).text();

	$.ajax({
		type : "POST",
		url : "managerServlet",
		data : {
			"macId" : macId,
			"method" : method,
			"macName" : macName
		},
		success : function(data) {
			var index = $(".modal-body > div").index($(obj).parent());
			$(".modal-body > button").eq(index).text(macName);
		}
	});

}

function deletedevice(mac, obj) {
	var macId = mac;
	var method = "333";
	$.ajax({
		type : "POST",
		url : "managerServlet",
		data : {
			"macId" : macId,
			"method" : method,
			"userId" : $("input[name=userId]").val()
		},
		success : function(data) {
			$(obj).parent().remove();
			var index = $(".modal-body > div").index($(obj).parent());
			$(".modal-body > button").eq(index).remove();
		}
	});
}