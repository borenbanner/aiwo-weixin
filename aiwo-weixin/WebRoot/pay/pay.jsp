<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.aiwo.server.pojo.Addr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
//		request.getSession().setAttribute("addr", new Addr(1,"aaaaaaa","Name","186203823","addressssss","1028774"));
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link type="text/css" href="../css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="../css/trade_3bffe65a06.css"
	rel="stylesheet">
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

.addr_a {
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
	transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s
		ease-in-out 0s;
	vertical-align: middle;
	width: 100%;
}

.address-fm-title {
	background: #FF3399;
	color: #ffffff;
}
</style>
</head>
<body>
	<div class="row info">
		<div class="col-xs-3" style="padding:0;">
			<div style="padding-top:15px;text-align:right;">选择商品：&nbsp;</div>
		</div>
		<input type="hidden" id="productid" value='${id}' /> 
		<input type="hidden" id="addrId" value="${addr.addrId }"/>
		<input type="hidden" id="openId" value="${openId}"/>
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
			<div class="addr-con">


				<div id="addr">
					<div id="address" style="">送货地址：${addr.addr }</div>
					<div id="nameAndPhone">接收人/电话：${addr.userName }/${ addr.tel }</div>
				</div>
				<c:if test='${addr ==null }'>
					<div class="edit edit-center" style=" color: #00ccff; ">
						<div id="getAddr">选择送货地址</div>
					</div>
				</c:if>

				<c:if test="${addr !=null }">
					<div class="edit edit-center" style=" color: #00ccff; ">
						<div id="editAddr">编辑送货地址</div>
					</div>
				</c:if>
				<%--					<input class="addr_a" type="text" /> --%>

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
						<img alt="" src="../image/haw_81.png"
							style="width:20px;height:20px;">
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
						<img alt="" src="../image/cancel.png"
							style="width:20px;height:20px;">
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

	<div id="userAddr" style="display:none;">
		<div id="overPay"
			style=" height: 100%; position: fixed; top: 0px; left: 0px; right: 0px; background-color: rgba(0, 0, 0, 0.9); z-index: 1000; transition: none 0.2s ease 0s ; opacity: 1;"></div>
		<div id="overAddr" class=""
			style=" overflow: hidden; bottom: 0px; left: 0px; right: 0px; background: none repeat scroll 0% 0% white; visibility: visible; position: absolute; z-index: 1000; transform: translate3d(0px, 0px, 0px); transition: all 300ms ease 0s; opacity: 1;">

			<form class="js-address-fm address-ui address-fm">
				<h4 class="address-fm-title">收货地址</h4>
				<div class="js-address-cancel publish-cancel">
					<img id="addrcancel" src="../image/cancel.png">
				</div>
				<div class="block" style="margin:0;">
					<div class="block-item">
						<label class="form-row form-text-row"> <em
							class="form-text-label">收货人</em> <span class="input-wrapper"><input
								type="text" name="user_name" class="form-text-input"
								value="${addr.userName}" placeholder="名字" /> </span> </label>
					</div>
					<div class="block-item">
						<label class="form-row form-text-row"> <em
							class="form-text-label">联系电话</em> <span class="input-wrapper"><input
								type="tel" name="tel" class="form-text-input"
								value="${addr.tel }" placeholder="手机或固话" /> </span> </label>
					</div>
					<div class="block-item">
						<label class="form-row form-text-row"> <em
							class="form-text-label">详细地址</em> <span class="input-wrapper"><input
								type="text" name="address_detail" class="form-text-input"
								value="${addr.addr }" placeholder="街道门牌信息" /> </span> </label>
					</div>
					<div class="block-item">
						<label class="form-row form-text-row"> <em
							class="form-text-label">邮政编码</em> <span class="input-wrapper"><input
								type="tel" maxlength="6" name="postal_code"
								class="form-text-input" value="${addr.postal_code }"
								placeholder="邮政编码(选填)" /> </span> </label>
					</div>
				</div>
				<div>
					<div class="action-container">
						<a class="btn btn-info  btn-block buy" id="saveAddr">保存</a>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>