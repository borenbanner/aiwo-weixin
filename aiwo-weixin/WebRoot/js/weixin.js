$(function() {
		$("#getBrandWCPayRequest").click(function() {
							$.ajax({
										type : "POST",
										url : "/pay/prepayIdServlet",
										data : {
											"openId" : '${openId}',
											"total_fee" : $("#total_price")
													.html(),
											"body" : $("#bodydes").html(),
											"productid" : $("#productid").val()
										}, //参数自己根据业务定义
										dataType : "json",
										success : function(data1, textStatus) {
											var data = eval('(' + data1 + ')');
											//alert(data);
											var appId = data.appId;
											var timestamp = data.timestamp;
											var nonceStr = data.nonceStr;
											var packages = data.packages;
											var finalsign = data.paySign;
											alert(appId + "," + timestamp + ","
													+ nonceStr + "," + packages
													+ "," + paySign);
											WeixinJSBridge
													.invoke(
															'getBrandWCPayRequest',
															{
																"appId" : appId,
																"timeStamp" : timestamp,
																"nonceStr" : nonceStr,
																"package" : packages,
																"signType" : "MD5",
																"paySign" : finalsign
															},
															function(res) {
																//alert(res.err_msg);
																WeixinJSBridge
																		.log(res.err_msg);
																if (res.err_msg == "get_brand_wcpay_request:ok") {
																	alert("支付成功!");
																	WeixinJSBridge
																			.call('closeWindow');
																} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
																	alert("用户取消支付!");
																} else {
																	alert("支付失败!");

																	WeixinJSBridge
																			.call('closeWindow');
																}
															});
											//自动关闭微信浏览器
											WeixinJSBridge.call('closeWindow');
										}
									});
						});
	});


$(function(){

	var addrobj = $("#addrId").val();
	console.log(addrobj);
	if (addrobj == '') {
		//console.log("addr is null");
		$("#userAddr").css("display","block") ;
	};
	var marque = "";
	var price = parseFloat($("#price").html());
	var totle_price = price * 1;
	console.log("total:" + totle_price);
	$('#less').click(function(e) {
		var num = $("#num").html() * 1;
		if (num > 1) {
			$("#num").html(num - 1);
			totle_price = price * (num - 1);
			$("#total_price").html(totle_price);
		}
	});
	$('#more').click(function(e) {

		var num = $("#num").html() * 1;
		totle_price = price * (num + 1);
		$("#num").html(num + 1);
		$("#total_price").html(totle_price);
	});

	function show(msg, type) {
		var type = type;
		if (type == "toWeixin") {
			$("#toPage").css("display", "none");
			$("#toWeixin").css("display", "");
		} else {
			$("#toWeixin").css("display", "none");
			$("#toPage").css("display", "");
		}
		$("#msg").html(msg);
		$('#alertMsg').modal('show');
	};
	$(".edit").click(function(e) {
		var readdr = "";
		if (readdr.length <= 0) {
			$(this).removeClass("edit-center").addClass("edit-left");
			$("#addr").addClass("addr");
		}
	});

	$("#saveAddr")
			.click(
					function() {
						$
								.ajax({
									type : "POST",
									url : "../addrServlet",
									data : {
										"addrId":addrobj,
										"openId" : $("#openId").val(),
										"userName" : $(
												"input[name=user_name]")
												.val(),
										"tel" : $("input[name=tel]").val(),
										"addr" : $(
												"input[name=address_detail]")
												.val(),
										"postal_code" : $(
												"input[name=postal_code]")
												.val()
									},
									dataType : "text",
									success : function(data) {
										$("#address")
												.html(
														"发货地址："
																+ $(
																		"input[name=address_detail]")
																		.val());
										$("#nameAndPhone")
												.html(
														"收货人/电话："
																+ $(
																		"input[name=user_name]")
																		.val()
																+ "/"
																+ $(
																		"input[name=tel]")
																		.val());

										$("#userAddr").css("display","none") ;
									}
								});
					});

	$("#editAddr,#getAddr").click(function() {
		$("#userAddr").css("display","block") ;
	});
	$("#addrcancel").click(function(){
		
		$("#userAddr").css("display","none") ;
	}) ;


	
});
