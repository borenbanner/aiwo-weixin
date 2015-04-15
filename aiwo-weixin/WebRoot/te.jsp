<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html style="overflow: visible; height: auto; position: static;"
	class="no-js " lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<link rel="dns-prefetch" href="http://tj.koudaitong.com/">
<link rel="dns-prefetch" href="http://imgqn.koudaitong.com/">
<link rel="dns-prefetch" href="http://kdt-static.qiniudn.com/">

<link rel="shortcut icon"
	href="http://kdt-static.qiniucdn.com/v2/image/yz_fc.ico">
<title>待付款的订单</title>
<script>
	var _global = {
		"kdt_id" : "674010",
		"user_id" : 0,
		"run_mode" : "online",
		"debug" : false,
		"project" : "default",
		"online_debug" : false,
		"js" : {
			"js_compress" : true,
			"css_compress" : true,
			"use_js_cdn" : true,
			"use_css_cdn" : true,
			"message_report" : true,
			"checkbrowser" : true,
			"hide_wx_nav" : true,
			"qn_public" : "kdt_img",
			"qn_private" : "kdt-private"
		},
		"query_path" : "\/trade\/order\/confirm",
		"query_key" : "order_no=E20150415094759121013790&kdt_id=674010&showwxpaytitle=1&forbid_wxpay=0",
		"real_query_path" : "get:\/trade\/order\/confirm.html",
		"module" : "trade",
		"controller" : "Trade_Order_Controller",
		"action" : "confirm",
		"full_action" : "getConfirmHtml",
		"method" : "get",
		"format" : "html",
		"platform" : "unknown",
		"is_mobile" : false,
		"authorize" : "unknown",
		"platform_version" : "unknown",
		"mobile_system" : "unknown",
		"page_size" : 320,
		"isShopDomain" : false,
		"share" : {
			"notShare" : true
		},
		"jsBradgeSupport" : true,
		"wuxi1_0_0" : false,
		"source" : "",
		"track" : "",
		"nobody" : "niplppk7pgrirse1i33afhk2q5",
		"mp_changed" : false,
		"team_certificate" : true,
		"hide_shopping_cart" : 0,
		"mp_data" : {
			"logo" : "http:\/\/imgqn.koudaitong.com\/upload_files\/2015\/03\/16\/FqUBa9_4pSRaX-_iOv5rgvFnG9Tv.jpg",
			"team_name" : "\u5929\u5a01\u5b98\u65b9\u5fae\u5546\u57ce",
			"intro" : "\u5168\u7403\u6700\u5927\u517c\u5bb9\u4e0e\u518d\u751f\u6253\u5370\u8017\u6750\u54c1\u724c\u201c\u5929\u5a01\u201d\u552f\u4e00\u5fae\u4fe1\u5b98\u65b9\u5546\u57ce\u3002\u4e13\u6ce8\u6253\u537030\u5e74\uff0c\u6253\u51fa\u7cbe\u5f69\uff0c\u5929\u5a01\u8017\u6750\uff01",
			"is_display_footbar" : "1",
			"is_display_suffix_name" : "0",
			"suffix_name" : "\u5929\u5a01\u5b98\u65b9\u5fae\u5546\u57ce",
			"shopping_cart_style" : "1",
			"team_type" : "youzan",
			"team_physical" : 0
		},
		"wxpay_big" : false,
		"alipay_env" : true,
		"wxpay_env" : false,
		"wxaddress_env" : false,
		"is_owner_team" : false,
		"fans_id" : 0,
		"is_fans" : 2,
		"fans_nickname" : "",
		"fans_type" : 0,
		"fans_token" : "",
		"mp_id" : 0,
		"fans_picture" : "",
		"proxy_fans_id" : 0,
		"youzan_fans_id" : 0,
		"youzan_fans_nickname" : "",
		"youzan_fans_picture" : "",
		"no_user_login" : true,
		"buyer_id" : 0,
		"change_password_url" : "",
		"buyer" : {
			"id" : 0,
			"phone" : ""
		},
		"directSeller" : {
			"seller" : "",
			"is_display_directseller_button" : false
		},
		"address_list" : [],
		"order_no" : "E20150415094759121013790",
		"is_virtual" : "0",
		"goods_alias" : "a37k5gc1",
		"address_required" : true,
		"address_token" : {
			"appId" : 0,
			"timeStamp" : "1429062799",
			"nonceStr" : "5ytmpohKxua6ZGVmwMabwnqZ05IXFd2l",
			"signType" : "SHA1",
			"addrSign" : "a19cf5f701a9d3eb8b14bd0adb2a7a6d8254d742",
			"scope" : "jsapi_address"
		},
		"order_state" : 1,
		"clear_local_address" : 0,
		"order_type" : 0,
		"coupons" : {
			"none_coupon" : {
				"order" : {
					"pay" : 990,
					"postage" : 0,
					"real_pay" : 990,
					"decrease" : 0
				},
				"coupon" : {
					"id" : 0,
					"type" : ""
				}
			},
			"default_coupon" : null,
			"cards" : [],
			"codes" : [],
			"total" : 0
		},
		"ump" : {
			"item" : {
				"activities" : []
			},
			"order" : {
				"coupons" : {
					"money" : {
						"order" : {
							"pay" : 990,
							"postage" : 0,
							"real_pay" : 990,
							"decrease" : 0,
							"goods_price" : 990
						},
						"coupon" : {
							"id" : 0,
							"type" : ""
						}
					},
					"postage" : null
				},
				"reduce" : [],
				"operation" : null
			},
			"final" : {
				"postage" : "0"
			}
		},
		"payWays" : [ {
			"key" : 7,
			"code" : "peerpay",
			"name" : "\u627e\u4eba\u4ee3\u4ed8"
		}, {
			"key" : 8,
			"code" : "umpay",
			"name" : "\u4fe1\u7528\u5361\u4ed8\u6b3e"
		}, {
			"key" : 12,
			"code" : "baiduwap",
			"name" : "\u50a8\u84c4\u5361\u4ed8\u6b3e"
		} ],
		"pagePayWaySize" : 3,
		"innerPayWays" : {
			"present" : {
				"name" : "\u9886\u53d6\u8d60\u54c1",
				"key" : 15,
				"code" : "presentpay"
			},
			"coupon" : {
				"name" : "\u7acb\u5373\u5151\u6362",
				"key" : 16,
				"code" : "couponpay"
			}
		},
		"allow_self_fetch" : false,
		"expressType" : 0,
		"selffetchAddress" : null,
		"expressAddress" : [],
		"url" : {
			"base" : "http:\/\/koudaitong.com",
			"domain" : "koudaitong.com",
			"cp" : "http:\/\/cp.koudaitong.com",
			"pay" : "http:\/\/pay.koudaitong.com",
			"trade" : "http:\/\/trade.koudaitong.com",
			"wdp_wap" : "http:\/\/m.wdianpu.com",
			"wdp" : "http:\/\/www.wdianpu.com",
			"api" : "http:\/\/api.koudaitong.com",
			"img" : "http:\/\/img.koudaitong.com",
			"static" : "http:\/\/static.koudaitong.com\/v2",
			"cdn_static" : "http:\/\/kdt-static.qiniucdn.com\/v2",
			"cdn" : "http:\/\/kdt-static.qiniucdn.com\/",
			"www" : "http:\/\/koudaitong.com\/v2",
			"v1" : "http:\/\/koudaitong.com\/v1",
			"short_url" : "http:\/\/koudaitong.com",
			"v1_static" : "http:\/\/static.koudaitong.com\/v1",
			"wap" : "http:\/\/wap.koudaitong.com\/v2",
			"imgqn" : "http:\/\/imgqn.koudaitong.com",
			"log_url" : "http:\/\/tj.koudaitong.com\/1.gif",
			"taobao_item" : "http:\/\/item.taobao.com",
			"open" : "http:\/\/open.koudaitong.com",
			"v2" : "http:\/\/koudaitong.com\/v2",
			"kdtim" : "http:\/\/kdt.im",
			"static_host" : "http:\/\/static.koudaitong.com",
			"wxcode" : "http:\/\/open.weixin.qq.com\/qr\/code\/?username=",
			"shop" : "http:\/\/detail.koudaitong.com",
			"vip" : "http:\/\/vip.koudaitong.com",
			"bbs" : "http:\/\/bbs.youzan.com",
			"fuwu" : "http:\/\/fuwu.youzan.com",
			"daxue" : "http:\/\/xt.youzan.com",
			"youzan" : "http:\/\/youzan.com",
			"cloud" : "http:\/\/kdt-cloud.qiniudn.com",
			"fenxiao" : "http:\/\/fx.youzan.com",
			"union" : "http:\/\/u.youzan.com",
			"union_api_list" : [ "http:\/\/10.10.6.235:9081",
					"http:\/\/10.10.24.136:9081" ],
			"union_ds_api_list" : [ "http:\/\/10.10.6.235:9082",
					"http:\/\/10.10.24.136:9082" ],
			"datacenter_api_list" : [ "http:\/\/10.10.34.166:5061" ],
			"ws" : "ws:\/\/im.koudaitong.com:83",
			"matrix" : "http:\/\/cp.qima-inc.com",
			"login" : "http:\/\/login.youzan.com",
			"settlement" : [ "http:\/\/10.10.21.250:8090",
					"http:\/\/10.10.19.57:8090" ]
		}
	};
	function onReady(target, callback) {
		if (!callback)
			return;
		var _onReady = function() {
			if (window[target]) {
				callback();
			} else {
				setTimeout(function() {
					_onReady(target, callback);
				}, 1000);
			}
		};
		_onReady(target, callback);
	}

	//base_head_script.js
	function _cdnFallback(e) {
		var t = e.nodeName.toLowerCase(), i = document.createElement(t), a = {
			script : "src",
			link : "href"
		}, n = a[t], o = e[n];
		o = o.replace("kdt-static.qiniudn.com", "kdt-static.b0.upaiyun.com"),
				"link" == t && (i.rel = "stylesheet"), i[n] = o, document.body
						.appendChild(i)
	}
	!function(e) {
		var a = document.documentElement
		"weixin" === e.platform ? a.className = a.className
				+ " mobile wx_mobile" : e.is_mobile
				&& (a.className = a.className + " mobile")
	}(window._global || {})
</script>


<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="te/base_d955a8a222.css"
	onerror="_cdnFallback(this)">
<link rel="stylesheet" href="te/trade_3bffe65a06.css"
	onerror="_cdnFallback(this)">
</head>

<body style="overflow: visible; height: auto;" class=" ">
	<div class="container js-page-content wap-page-order">
		<div style="min-height: 553px;" class="content confirm-container">
			<div class="app app-order">
				<div class="app-inner inner-order" id="js-page-content">
					<!-- 通知 -->

					<!-- 物流 -->
					<div class="block express" id="js-logistics-container"
						style="margin-top: -1px;">
						<div class="js-logistics-content logistics-content js-express">
							<div class="">
								<div class="js-order-address express-panel express-panel-no">
									<div class="js-edit-address empty-address-tip">
										<span>添加收货地址</span>
									</div>
								</div>
							</div>
						</div>
						<div
							class="js-logistics-content logistics-content js-self-fetch hide"></div>
					</div>


					<!-- 支付 -->

				</div>
			</div>
		</div>

		<div id="js-self-fetch-modal" class="modal order-modal"></div>
	</div>

	<div >

		<form class="js-address-fm address-ui address-fm">
			<h4 class="address-fm-title">收货地址</h4>
			<div class="js-address-cancel publish-cancel">
				<div class="cancel-img"></div>
			</div>
			<div class="block" style="margin:0;">
				<div class="block-item">
					<label class="form-row form-text-row"> <em
						class="form-text-label">收货人</em> <span class="input-wrapper"><input
							type="text" name="user_name" class="form-text-input" value=""
							placeholder="名字" /> </span> </label>
				</div>
				<div class="block-item">
					<label class="form-row form-text-row"> <em
						class="form-text-label">联系电话</em> <span class="input-wrapper"><input
							type="tel" name="tel" class="form-text-input" value=""
							placeholder="手机或固话" /> </span> </label>
				</div>
				<div class="block-item">
					<label class="form-row form-text-row"> <em
						class="form-text-label">详细地址</em> <span class="input-wrapper"><input
							type="text" name="address_detail" class="form-text-input"
							value="" placeholder="街道门牌信息" /> </span> </label>
				</div>
				<div class="block-item">
					<label class="form-row form-text-row"> <em
						class="form-text-label">邮政编码</em> <span class="input-wrapper"><input
							type="tel" maxlength="6" name="postal_code"
							class="form-text-input" value="" placeholder="邮政编码(选填)" /> </span> </label>
				</div>
			</div>
			<div>
				<div class="action-container">
					<a class="js-address-save btn btn-block btn-green">保存</a>
				</div>
			</div>
		</form>
	</div>


	<!-- 	<script src="te/zepto_74673adbe0.js" onerror="_cdnFallback(this)"></script> -->
	<!-- 	<script src="te/almond_0c51656101.js" onerror="_cdnFallback(this)"></script> -->
	<!-- 	<script src="te/base_86ac197941.js" onerror="_cdnFallback(this)"></script> -->

	<!-- 	<script src="te/u_b.js" onerror="_cdnFallback(this)"></script> -->
	<!-- 	<script src="te/jquery.js"></script> -->

	<!-- 	<script src="te/confirm_6107be318e.js" onerror="_cdnFallback(this)"></script> -->
</body>
</html>
