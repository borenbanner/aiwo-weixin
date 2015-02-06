var Model = function() {
	this.mainViewModel = {
		hai : ko.observable(0),
		outWet : ko.observable(),
		outTemp : ko.observable(),
		outPm25 : ko.observable(),
		location : ko.observable(),
		quality : ko.observable(),
		pm10 : ko.observable(), // 颗粒物 2014-5-26 add
		pressure : ko.observable(), // 气压 2014-5-26 add
		tips : ko.observable("正在计算您所在位置室外空气质量..."),
		outDoorHai : ko.observable(0)
	};

	this.indoorViewModel = {
		isVirtual : ko.observable(),
		rank : ko.observable(),
		hai : ko.observable(0),
		temp : ko.observable(),
		wet : ko.observable(),
		pm25 : ko.observable(),
		quality : ko.observable(),
		devices : ko.observableArray([ {
			"name" : "空调",
			"status" : "STATUS_OFFLINE",
			"mac" : "",
			"deviceType" : "AirCondition",
			"isVirtual" : "true",
			"familyId" : "",
			"switchStatus" : "关",
			"img" : "images/haw_17.png"
		}, {
			"name" : "空气盒子",
			"status" : "STATUS_OFFLINE",
			"mac" : "",
			"deviceType" : "AirCircle",
			"isVirtual" : "true",
			"familyId" : "",
			"switchStatus" : "关",
			"img" : "images/haw_40.png"
		}, {
			"name" : "醛知道",
			"status" : "STATUS_OFFLINE",
			"mac" : "",
			"deviceType" : "AllKnow",
			"isVirtual" : "true",
			"familyId" : "",
			"switchStatus" : "关",
			"img" : "images/haw_48.png"
		} ]),
		zan : ko.observable(),
		// add2014-05 -26
		hcho : ko.observable(),
		co2 : ko.observable(),
		voc : ko.observable(),
		tips : ko.observable("正在启动检测设备…"),
		hasDevice : ko.observable(),
		indoorHai : ko.observable(),
		optTips : ko.observableArray()
	};

	this.nearViewModel = {
		locate : ko.observable(),
		nickName : ko.observable(),
		headUrl : ko.observable(),
		zan : ko.observable(),
		temp : ko.observable(),
		wet : ko.observable(),
		pm25 : ko.observable(), // 2014-5-22 add
		hai : ko.observable(0),
		quality : ko.observable(),
		devices : ko.observableArray(),
		// add2014-05 -26
		hcho : ko.observable(),
		co2 : ko.observable(),
		voc : ko.observable(),
		tips : ko.observable("正在启动检测设备…")
	};

	this.pkViewModel = {
		locate : ko.observable(),
		nickName : ko.observable(),
		headUrl : ko.observable(),
		// devices : ko.observableArray(),
		quality : ko.observable(),
		hai : ko.observable(),
		myhai : ko.observable(),
		myheadUrl : ko.observable(),
		myquality : ko.observable(),
		// myDevices : ko.observableArray(),
		pkresult : ko.observable(),
		tips : ko.observable(),
		pkId : ko.observable()
	};

	this.bindingViewModel = {
		devices : ko.observableArray(),
	};

	this.bindUserAccountViewModel = {
		accounts : ko.observableArray()
	};

	this.usersModel = {
		users : ko.observableArray()
	};

	this.conditionStatus = {
		temp : ko.observable(),
		onOff : ko.observable(),
		mode : ko.observable(),
		windSpeed : ko.observable(),
		windDirection : ko.observable(),
		airFunction : ko.observable(),
		airFuncOff : ko.observable(),
		healthy : ko.observable(),
		touching : ko.observable(),
		sleep : ko.observable(),
		addOxygen : ko.observable()
	};

	this.conditionModel = {
		usercount : ko.observable(),
		pollDete : ko.observable(),
		pollClean : ko.observable(),
		tempRise : ko.observable(),
		tempReduce : ko.observable(),
		airHum : ko.observable(),
		airDry : ko.observable(),
		elecCtrl : ko.observable(),
		images : ko.observableArray(),
		hasDevice : ko.observable(),
		deviceType : ko.observable()
	};

	this.deviceIntegrity = {
		level : ko.observable(),
		title : ko.observable(),
		monitor : ko.observable(),
		clear : ko.observable(),
		rise : ko.observable(),
		reduce : ko.observable(),
		wet : ko.observable(),
		dry : ko.observable(),
		control : ko.observable(),
		monitor_width : ko.observable(),
		clear_width : ko.observable(),
		rise_width : ko.observable(),
		reduce_width : ko.observable(),
		wet_width : ko.observable(),
		dry_width : ko.observable(),
		control_width : ko.observable()
	};
};

Model.prototype = {
	loadMain : function(openId, preload) {
		var self = this;
		$(".loading").css("display", "inline");
		self.mainViewModel.tips("正在加载...");
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/outdoorvalues?openId=" + openId,
				function(result) {
					$(".loading").css("display", "none");
					if (preload) {
						navigateToPage($('#main'));
					}
					self.mainViewModel.hai(result.AQI);
					self.mainViewModel.outWet(result.wetOutDoor);
					self.mainViewModel.outTemp(result.tempOutDoor);
					self.mainViewModel.outPm25(result.pm25);
					self.mainViewModel.location(result.location);
					self.mainViewModel.quality(result.haiRank);
					self.mainViewModel.pm10(result.pm10); // 2014-05-26 add
					self.mainViewModel.pressure(result.pressure); // 2014-05-26
					// add
					self.mainViewModel.tips(result.wording);
					self.mainViewModel.outDoorHai(result.outDoorHai);
					/*
					 * switch (result.quality) { case "优":
					 * self.mainViewModel.tips("打开窗户，让新鲜空气涌进来"); break; case
					 * "良": self.mainViewModel.tips("外面好舒服，让我们出去动起来"); break;
					 * case "轻度污染": self.mainViewModel.tips("空气不太好，出门带上口罩吧");
					 * break; case "中度污染":
					 * self.mainViewModel.tips("空气不太好，出门带上口罩吧"); break; case
					 * "重度污染": self.mainViewModel.tips("看不到对面的楼，家电总动员的时候到了");
					 * break; case "严重污染":
					 * self.mainViewModel.tips("看不到对面的楼，家电总动员的时候到了"); break; }
					 */
					aqiAnim(Number(result.outDoorHai));
				});
	},
	loadNear : function(openId, nearId) {
		var id = fRandomBy(6, 105);

		if (nearId) {
			id = nearId;
		}

		$("#nearId").val(id);
		var self = this;
		$(".loading").css("display", "inline");
		self.nearViewModel.tips("正在加载...");
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/indoorvalues?openId=" + openId + "&nearId=" + id,
				function(result) {
					$(".loading").css("display", "none");
					self.nearViewModel.locate(result.address_full);
					self.nearViewModel.nickName(result.username);
					self.nearViewModel.headUrl(result.headportrait);
					self.nearViewModel.zan(result.praise);
					self.nearViewModel.temp(result.temperature_in);
					self.nearViewModel.wet(result.humidity_in);
					self.nearViewModel.pm25(result.pm25); // 2014-5-22 add
					self.nearViewModel.hai(result.indoorHai);
					self.nearViewModel.quality(result.haiRank);
					self.nearViewModel.devices(result.device);

					var tips = "未测到您家中设备，看下朋友家中情况";
					// var tips = "点击Ta的家电进行体验";

					self.nearViewModel.tips(tips);
					// add2014-05 -26
					self.nearViewModel.hcho(result.hcho);
					self.nearViewModel.co2(result.co2);
					self.nearViewModel.voc(result.vocIndoor);

					$("#nearby_img").rotate({
						duration : 2200,
						angle : 0,
						animateTo : 360
					});
					$("#nearby_hai").text("0");
					updateHai("nearby_hai", result.indoorHai);
					optimizeAnimation(result.indoorHai, 0, 360,
							".nearAangle_2", ".nearAngle_1", "#nearCircle_3");
					$(".ding>img").attr("src", "images/like.png");
				});
	},
	loadIndoor : function(openId, isVirtual, preload) {
		if (!preload) {
			$("#ajax_loader_transparent").show();
		}
		isVirtualOnly = isVirtual ? 1 : 0;
		var self = this;
		$(".loading").css("display", "inline");
		self.indoorViewModel.tips("正在加载...");
		if (!isVirtual) {
			$("#inDoor .circle-hai-text").text("室内空气指数");
			$
					.get(
							"http://" + window.location.hostname + ":"
									+ window.location.port
									+ "/weixinAir/beforeoptimize?openId="
									+ openId,
							function(result) {
								$(".loading").css("display", "none");
								if (preload) {
									navigateToPage($('#inDoor'));
								}
								var rank = "舒适度超过全国<span class='rank'>"
										+ result.rank + "%</span>的使用者 ";
								self.indoorViewModel.isVirtual(false);
								self.indoorViewModel.rank(rank);
								self.indoorViewModel.hai(result.value.HAI);
								self.indoorViewModel
										.temp(result.value.tempIndoor);
								self.indoorViewModel
										.wet(result.value.wetIndoor);
								self.indoorViewModel.pm25(result.value.pm25);
								self.indoorViewModel
										.quality(result.value.haiRank);
								self.indoorViewModel.zan(result.praise);
								// self.indoorViewModel.tips(result.value.tips);

								self.indoorViewModel.hcho(result.value.hcho);
								self.indoorViewModel.co2(result.value.co2);
								self.indoorViewModel
										.voc(result.value.vocIndoor);
								self.indoorViewModel.hasDevice(hasDevice);
								self.indoorViewModel
										.indoorHai(result.value.indoorHai);
								self.indoorViewModel
										.optTips(result.value.wordings.optTips);

								updateHai("myHai", result.value.indoorHai);
								optimizeAnimation(result.value.indoorHai, 0,
										360, ".indoorAangle_2",
										".indoorAngle_1", "#indoorCircle_3");

								$("#btn-mydevice").css('display',
										'inline-block');

								devices = result.device;

								for ( var i = 0; i < devices.length; i++) {
									switch (devices[i].deviceType) {
									case "AirCondition":
										if (devices[i].isVirtual === 'true') {
											if (devices[i].status === "STATUS_ONLINE") {
												devices[i].img = "images/haw_14.png";
											} else {
												devices[i].img = "images/haw_17.png";
											}
										} else {
											if (devices[i].status === "STATUS_ONLINE") {
												if (devices[i].switchStatus === "开") {
													devices[i].img = "images/haw_14.png";
												} else {
													devices[i].img = "images/haw_15.png";
												}
											} else {
												devices[i].img = "images/haw_16.png";
											}

										}
										break;
									case "AirCircle":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_37.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_39.png";
										} else {
											devices[i].img = "images/haw_40.png";
										}
										break;
									case "AllKnow":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_45.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_47.png";
										} else {
											devices[i].img = "images/haw_48.png";
										}
										break;
									case "Humidifier":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_55.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_57.png";
										} else {
											devices[i].img = "images/haw_58.png";
										}
										break;
									case "Purifier":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_26.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_28.png";
										} else {
											devices[i].img = "images/haw_29.png";
										}
										break;
									}
								}

								self.indoorViewModel.devices(devices);

								viewModel.indoorViewModel
										.tips(result.value.wordings.inTips);

								$("#ajax_loader_transparent").hide();

							});
		} else {
			$("#inDoor .circle-hai-text").text("虚拟室内空气");
			$
					.get(
							"http://" + window.location.hostname + ":"
									+ window.location.port
									+ "/weixinAir/TryOutServlet?openId="
									+ openId,
							function(result) {
								$(".loading").css("display", "none");
								if (preload) {
									navigateToPage($('#inDoor'));
								}
								var rank = "您是第<span class='rank'>"
										+ result.count + "</span>位试用设备用户";
								self.indoorViewModel.isVirtual(true);
								self.indoorViewModel.rank(rank);
								self.indoorViewModel.hai(result.value.HAI);
								self.indoorViewModel
										.temp(result.value.tempIndoor);
								self.indoorViewModel
										.wet(result.value.wetIndoor);
								self.indoorViewModel.pm25(result.value.pm25);
								self.indoorViewModel
										.quality(result.value.haiRank);
								self.indoorViewModel.zan(result.praise);
								// self.indoorViewModel.tips(result.value.tips);

								self.indoorViewModel.hcho(result.value.hcho);
								self.indoorViewModel.co2(result.value.co2);
								self.indoorViewModel
										.voc(result.value.vocIndoor);
								self.indoorViewModel.hasDevice(hasDevice);
								self.indoorViewModel
										.indoorHai(result.value.indoorHai);

								updateHai("myHai", result.value.indoorHai);
								optimizeAnimation(result.value.indoorHai, 0,
										360, ".indoorAangle_2",
										".indoorAngle_1", "#indoorCircle_3");

								$("#btn-xunidevice").css('display',
										'inline-block');

								devices = result.device;

								for ( var i = 0; i < devices.length; i++) {
									switch (devices[i].deviceType) {
									case "AirCondition":
										if (devices[i].isVirtual === 'true') {
											if (devices[i].status === "STATUS_ONLINE") {
												devices[i].img = "images/haw_14.png";
											} else {
												devices[i].img = "images/haw_17.png";
											}
										} else {
											if (devices[i].status === "STATUS_ONLINE") {
												if (devices[i].switchStatus === "开") {
													devices[i].img = "images/haw_14.png";
												} else {
													devices[i].img = "images/haw_15.png";
												}
											} else {
												devices[i].img = "images/haw_16.png";
											}

										}
										break;
									case "AirCircle":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_37.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_39.png";
										} else {
											devices[i].img = "images/haw_40.png";
										}
										break;
									case "AllKnow":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_45.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_47.png";
										} else {
											devices[i].img = "images/haw_48.png";
										}
										break;
									case "Humidifier":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_55.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_57.png";
										} else {
											devices[i].img = "images/haw_58.png";
										}
										break;
									case "Purifier":
										if (devices[i].status === "STATUS_ONLINE") {
											devices[i].img = "images/haw_26.png";
										} else if (devices[i].status === "STATUS_OFFLINE"
												&& devices[i].isVirtual === 'false') {
											devices[i].img = "images/haw_28.png";
										} else {
											devices[i].img = "images/haw_29.png";
										}
										break;
									}
								}

								self.indoorViewModel.devices(devices);

								viewModel.indoorViewModel
										.tips(result.value.tips);

								$("#ajax_loader_transparent").hide();
							});
		}
	},
	loadPk : function(openId, id, hai,callback) {
		$('#ajax_loader').show();
		var self = this;
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/VirtualUserWithIDServlet?openId=" + openId
				+ "&id=" + id + "&hai=" + hai, function(result) {
			self.pkViewModel.locate(result.locate);
			self.pkViewModel.nickName(result.nickName);
			self.pkViewModel.headUrl(result.headUrl);
			self.pkViewModel.quality(result.vhaiRank);
			self.pkViewModel.hai(result.hai);
			self.pkViewModel.myheadUrl(result.myheadUrl);
			self.pkViewModel.myhai(result.indoorHai);
			self.pkViewModel.myquality(result.haiRank);

			self.pkViewModel.tips(result.tips);
			self.pkViewModel.pkId(id);

			self.pkViewModel.pkresult(result.pkresult);

			if (result.pkresult) {
				$(".pk").css("background-image", "url(images/haw_04.jpg)");
				$(".pk_img_1").attr("src", "images/haw_30.png");
				$(".pk_img_2").attr("src", "images/haw_62.png");
				$(".pk_img_3").css("visibility", "");
				$(".pk_img_4").css("visibility", "hidden");
			} else {
				$(".pk").css("background-image", "url(images/haw_09.jpg)");
				$(".pk_img_1").attr("src", "images/haw_62.png");
				$(".pk_img_2").attr("src", "images/haw_49.png");
				$(".pk_img_3").css("visibility", "hidden");
				$(".pk_img_4").css("visibility", "");
			}
			$('#ajax_loader').hide();
			
			callback();
			
			var title = "室内空气质量";
			if ($('#subscribe').val() != 2) {
				if (result.pkresult) {
					title = $("#nickName").val() + "战胜了朋友";
				} else {
					title = "朋友战胜了" + $("#nickName").val();
				}
			}
			$(document).attr("title", title);
		});
	},
	loadTryOut : function(openId, type) {
		// document.title = "商品展示";
		var deviceType;

		switch (type.toLocaleLowerCase()) {
		case "aircondition":
			deviceType = 1;
			$("#condition_Modal h5.modal-title").text("海尔空调");
			break;
		case "aircircle":
			deviceType = 2;
			$("#condition_Modal h5.modal-title").text("空气盒子");
			break;
		case "allknow":
			deviceType = 3;
			$("#condition_Modal h5.modal-title").text("醛知道");
			break;
		case "purifier":
			deviceType = 4;
			$("#condition_Modal h5.modal-title").text("净化机");
			break;
		case "humidifier":
			deviceType = 5;
			$("#condition_Modal h5.modal-title").text("加湿机");
			break;
		case "dehumidifier":
			deviceType = 6;
			$("#condition_Modal h5.modal-title").text("除湿机");
			break;
		case "heater":
			deviceType = 7;
			$("#condition_Modal h5.modal-title").text("暖风机");
			break;
		}
		$('#ajax_loader_transparent').show();
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/DeviceInfoServlet?openId=" + openId
				+ "&deviceType=" + deviceType,
				function(result) {
					$('#ajax_loader_transparent').hide();
					viewModel.conditionModel
							.usercount(result.deviceinfo.usingSum);
					viewModel.conditionModel.pollDete('+'
							+ result.deviceinfo.pollDete);
					viewModel.conditionModel.pollClean('+'
							+ result.deviceinfo.pollClean);
					viewModel.conditionModel.tempRise('+'
							+ result.deviceinfo.tempRise);
					viewModel.conditionModel.tempReduce('+'
							+ result.deviceinfo.tempReduce);
					viewModel.conditionModel.airHum('+'
							+ result.deviceinfo.airHum);
					viewModel.conditionModel.airDry('+'
							+ result.deviceinfo.airDry);
					viewModel.conditionModel.elecCtrl('+'
							+ result.deviceinfo.elecCtrl);
					viewModel.conditionModel.deviceType(result.deviceType);
					viewModel.conditionModel.hasDevice(result.hasDevice);
					viewModel.conditionModel.images(result.imageUrl);

					imageSliderChange();
				});

	},
	load_conditionStatus : function(openId, mac, callback) {
		$('#ajax_loader_transparent').show();
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/GetConditionRealTimeStatus?openId=" + openId
				+ "&mac=" + mac, function(result) {

			viewModel.conditionStatus.temp(result.temperature);
			viewModel.conditionStatus.onOff(result.onOff);
			viewModel.conditionStatus.mode(result.model);
			viewModel.conditionStatus.windSpeed(result.windSpeed);
			viewModel.conditionStatus.windDirection(result.windDirection);
			viewModel.conditionStatus.airFunction(result.airFunction);
			viewModel.conditionStatus.airFuncOff(result.airFuncOff);

			viewModel.conditionStatus.healthy(result.health);
			viewModel.conditionStatus.touching(result.touch);
			viewModel.conditionStatus.sleep(result.sleep);
			viewModel.conditionStatus.addOxygen(result.ox);
			if(window.location.href.indexOf("page=mydevice") > -1) {
				$.get('/weixinAir/devdata?openId='+ $("#openId").val(),function(result) {
					viewModel.indoorViewModel.temp(result.value.tempIndoor);
				});
			}
			setMode(result.onOff, result.model, result.status);
			if (callback) {
				callback();
			}
			$('#ajax_loader_transparent').hide();
			if ($("#controlTips").text() == "操作完毕！"
					|| $("#controlTips").text() == "") {
				$("#controlTips").text("");
			} else {
				$("#controlTips").text("操作完毕！");
			}
			if ($("#controlTips").text() == "") {
				$("#indoorTemp").show();
			} else {
				$("#indoorTemp").hide();
			}

		});
	},
	loadDeviceIntegrity : function(openId) {
		$('#ajax_loader').show();
		$.get("http://" + window.location.hostname + ":" + window.location.port
				+ "/weixinAir/DeviceIntegrityServlet?openId=" + openId,
				function(result) {
					$('#ajax_loader').hide();
					viewModel.deviceIntegrity.level(result.level);
					viewModel.deviceIntegrity.title(result.title);
					viewModel.deviceIntegrity.monitor(result.monitor + "/10");
					viewModel.deviceIntegrity.clear(result.clear + "/10");
					viewModel.deviceIntegrity.rise(result.rise + "/10");
					viewModel.deviceIntegrity.reduce(result.reduce + "/10");
					viewModel.deviceIntegrity.wet(result.wet + "/10");
					viewModel.deviceIntegrity.dry(result.dry + "/10");
					viewModel.deviceIntegrity.control(result.control + "/10");
					viewModel.deviceIntegrity.monitor_width(result.monitor
							+ "0%");
					viewModel.deviceIntegrity.clear_width(result.clear + "0%");
					viewModel.deviceIntegrity.rise_width(result.rise + "0%");
					viewModel.deviceIntegrity
							.reduce_width(result.reduce + "0%");
					viewModel.deviceIntegrity.wet_width(result.wet + "0%");
					viewModel.deviceIntegrity.dry_width(result.dry + "0%");
					viewModel.deviceIntegrity.control_width(result.control
							+ "0%");
				});

	}
};
