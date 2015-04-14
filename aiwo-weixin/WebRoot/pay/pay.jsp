<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- base href="http://pay.haierubic.com/weixinAir/" -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link type="text/css" href="../css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/sha1.js"></script>
<script type="text/javascript" src="../js/weixin.js"></script>

<style>
.dprice,.buy {
	background: #FF3399;
	color: #ffffff;
}

.canceldiv,.buydiv {
	padding-top: 0px;
	padding-bottom: 0px;
}

.buydiv {
	padding-right: 7px;
	padding-left: 15px;
}

.canceldiv {
	padding-right: 15px;
	padding-left: 7px;
}

.cancel {
	background: #CD3333;
	color: #ffffff;
}

.pnum {
	height: 55px;
	margin-top: 15px;
}

.address {
	height: 150px;
	margin-top: 20px;
}

.info,.address,.pnum {
	border-bottom: solid 1.5px #D5D5D5;
}

.center {
	display: table;
	text-align: center;
	margin: 0 auto;
}

.addr-con {
	background-color: #D5D5D5;
	-moz-border-radius: 5px; /* Gecko browsers */
	-webkit-border-radius: 5px; /* Webkit browsers */
	border-radius: 5px; /* W3C syntax */
	height: 110px;
}

.tipinf {
	margin-top: 8px;
	margin-left: 10px;
}

.priceinf,.tipinf {
	color: #A8A8A8;
	font-size: 10px;
}

.priceinf {
	margin-top: 30px;
}

.btns {
	margin-top: 5px;
	margin-bottom: 15px;
}

.edit-center {
	display: table;
	text-align: center;
	margin: 0 auto;
	padding-top: 50px;
}

.edit-left {
	display: table;
	height: 10px;
	padding-left: 15px;
}

.addr {
	height: 80px;
	padding-left: 15px;
	padding-top: 20px;
}

.row {
	margin-left: 0px;
	margin-right: 0px;
}

#num {
	background-color: #D5D5D5;
}

body {
	background-color: #f0f0f0;
}
.addr_a{
 background-color: #FFFFFF;
    background-image: none;
    border: 1px solid #CCCCCC;
    border-radius: 4px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    color: #555555;
    display: block;
    font-size: 14px;
    height: 34px;
    line-height: 1.42857;
    padding: 6px 12px;
    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
    vertical-align: middle;
    width: 100%;
}
</style>
<script type="text/javascript">
	$(function() {

		var timeStamp = getTimeStamp();
		var nonceStr = getNonceStr();
		
		 var signparas = $.extend(signparasobj, {
	            "accesstoken": getaccesstoken(),
	            "noncestr": nonceStr,
	            "timestamp": timeStamp,
	            "url": window.location.href
	        });
		 
		 var signstring = getSign(perapara(signparas));
		 alert(signstring) ;
		 $("#getAddr").click(function(){
			 WeixinJSBridge.invoke('editAddress',
	                    {
	                        "appId": getappid(),
	                        "scope": "jsapi_address",
	                        "signType": "sha1",
	                        "addrSign": signstring,
	                        "timeStamp": nonceStr,
	                        "nonceStr": timeStamp,
	                    },function(res){
	                    	alert(res.err_msg);
	                    	if (res.err_msg == 'edit_address:ok') {
								var addr = res.proviceFirstStageName
										+ "，"
										+ res.addressCitySecondStageName
										+ "，"
										+ res.addressCountiesThirdStageName
										+ "，"
										+ res.addressDetailInfo
										+ "， "
										+ res.addressPostalCode;
								;
								userName = res.userName;
								phoneNumber = res.telNumber;
								address = addr;
								getAddress = true;
								document
										.getElementById("address").innerHTML = addr;
								document
										.getElementById("nameAndPhone").innerHTML = res.userName
										+ "，"
										+ res.telNumber;
							} else {
								address = "";
								getAddress = false;
								document
										.getElementById("address").innerHTML = "获取地址失败，请重新点击";
								document
										.getElementById("nameAndPhone").innerHTML = "";
							}
	                    	
	                    });
			 
		 }) ;
		$("#getBrandWCPayRequest")
				.click(
						function() {
							$.ajax({
										type : "POST",
										url : "/pay/prepayIdServlet",
										data :{"openId":'${openId}',"total_fee":$("#total_price").html(),"body":$("#bodydes").html(),"productid":$("#productid").val()}, //参数自己根据业务定义
										dataType : "json",
										success : function (data1,textStatus) {
												var data = eval('(' + data1 + ')') ;
												alert(data);
												var appId = data.appId;
												var timestamp = data.timestamp;
												var nonceStr = data.nonceStr;
												var packages = data.packages;
												var finalsign = data.paySign;
												alert(appId+","+timestamp+","+nonceStr+","+packages+","+paySign);
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
</script>

</head>
<body>
	<div class="row info">
		<div class="col-xs-3" style="padding:0;">
			<div style="padding-top:15px;text-align:right;">选择商品：&nbsp;</div>
		</div>
		<input type="hidden"  id="accesstoken"value='${accesstoken}'/>
		<input type="hidden"  id="productid" value='${id}'/>
		<div class="col-xs-9 dinfo" style="padding:0px; margin-bottom:10px;">
			<div style="font-size:17px;margin:0;padding-top:10px;" id="bodydes">${describe
				}</div>

			<div style="font-size:12px;padding-top:4px;"></div>
			<div style="padding-top:0px;margin-bottom:10px;">
				<div style="float:left; ">
					微信价&nbsp;￥<span id="price" style="font-size:21px;">${price }</span>
				</div>
				<div style="float:left;display:inline;margin-left:10px;">
					市场价&nbsp;
					<del style="font-size:21px; color:#FF0000; ">
						<span style="color:#000000;">￥</span>${price+100 }
					</del>
				</div>
			</div>
		</div>
	</div>
	<div class="row pnum">
		<div class="col-xs-3 col-sm-3"
			style="padding:0px;margin-top:5px;text-align:right;">选择数量：&nbsp;</div>
		<div class="col-xs-2 col-sm-2" style="padding:1px;">
			<button type="button" class="btn btn-primary btn-block" id="less">&nbsp;&nbsp;-&nbsp;&nbsp;</button>
		</div>
		<div class="col-xs-4 col-sm-4"
			style="padding-left:7px;padding-right:7px;padding-bottom:1px;padding-top:1px;">
			<button type="button" class="btn  btn-block" id="num">1</button>
		</div>
		<div class="col-xs-2 col-sm-2" style="padding:1px;">
			<button type="button" class="btn btn-primary btn-block" id="more">&nbsp;&nbsp;+&nbsp;&nbsp;</button>
		</div>
	</div>

	<div class="row address">
		<div class="col-xs-3 col-sm-3"
			style="padding-right:0px;text-align:right;">选择地址：&nbsp;</div>
		<div class="col-xs-8 col-sm-8" style="padding:0px;">
			<div class="">

<%----%>
<%--				<div id="addr">--%>
<%--					<div id="address" style=""></div>--%>
<%--					<div id="nameAndPhone"></div>--%>
<%--				</div>--%>
<%----%>
<%----%>
<%----%>
<%--				<div class="edit edit-center" style=" color: #00ccff; ">--%>
<%--					<a href="#" id="getAddr"><u>选择送货地址</u> </a>--%>
<%--				</div>--%>
					<input class="addr_a" type="text" /> 

			</div>
			<div class="tipinf">预计3-5个工作日到货。(稍后给你发送物流信息。偏远地区时间会稍长,请耐心等待)</div>
		</div>
	</div>
	<div class="priceinf center">
		共需支付￥<span id="total_price">${price }</span>元
	</div>
	<div class="row btns">
		<div class="col-xs-1 col-md-4"></div>
		<div class="col-xs-5 col-md-2 buydiv">
			<button class="btn btn-info  btn-block buy "
				style="text-align:center;border:0;height:40px;"
				id="getBrandWCPayRequest">
				<div class="center">
					<div style="float:left;">
						<img alt="" src="../image/haw_81.png" style="width:20px;height:20px;">
					</div>
					<div style="float:left;">&nbsp;微信支付</div>
				</div>
			</button>
		</div>
		<div class="col-xs-5 col-md-2 canceldiv">
			<button class="btn btn-info  btn-block cancel "
				style="text-align:center;border:0;height:40px;"
				onclick="WeixinJSBridge.call('closeWindow');">
				<div class="center">
					<div style="float:left;">
						<img alt="" src="../image/cancel.png" style="width:20px;height:20px;">
					</div>
					<div style="float:left;">&nbsp;取消订单</div>
				</div>
			</button>
		</div>
	</div>
	<div style="height:10px;">&nbsp;</div>
	<div class="alertMsg">
		<div class="modal fade" id="alertMsg">
			<div class="modal-dialog" style="margin-top:150px;">
				<div class="modal-content" style="height: auto;">
					<div class="modal-body">
						<p id="msg"
							style="text-align:center; margin-bottom:10px;font-size:15px;">One
							fine body…</p>
						<p style="text-align:center;">
							<button type="button" class="btn btn-default btn-xm"
								data-dismiss="modal" id="toWeixin"
								onclick="WeixinJSBridge.call('closeWindow');">确定</button>
							<button type="button" class="btn btn-default btn-xm"
								data-dismiss="modal" id="toPage">确定</button>
						</p>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
		var marque = "";
		var price = parseInt($("#price").html());
		console.log(price);
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
	</script>

</body>
</html>