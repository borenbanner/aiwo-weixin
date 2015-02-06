<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>海尔空气MINI</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="control/js/jquery-geturlparam.js"></script>
<script type="text/javascript">
	$(function() {
		var id = $.getUrlParam('id');
		if (id == null) {
			id = 1;
		}

		$.ajax({
			type : "post",
			cache : false,
			url : "productServlet",
			data : {
				"method" : "querysigle",
				"proId" : id,
				"update" : "no"
			},
			dataType : "json",
			success : function(data) {
			var obj = data ;
				$(".item").html("<img src=\""+obj.proImage+"\"  class=\"img\">");
				$(".dinfo > p").text(obj.proName);
				$("#price").text(obj.proPrice);
				$("#oldprice").text(parseInt(obj.proPrice)+300) ;
				$("#content").html(obj.proDetail);
			}
		});
	});
</script>
<style>
.row {
	margin-left: 0px;
	margin-right: 0px;
}

.imgsss {
	width: 450px !important;
	height: 200px !important;
}

.imgs {
	width: 350px !important;
	height: 200px !important;
}

.dinfo {
	background: #0099FE;
}

.dprice,.buy {
	background: #FF3399;
	color: #ffffff;
}

.dprice,.dinfo {
	height: 50px;
	color: #ffffff;
}

.center {
	display: table;
	text-align: center;
	margin: 0 auto;
}

.footer {
	position: fixed;
	bottom: 0;
	padding: 0;
	width: 100%;
	background: #F0F8FF;
	height: 60px;
	padding-top: 10px;
}

.describe {
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 15px;
	padding-right: 15px;
}

.more-img {
	margin-bottom: 60px;
	margin-left: 15px;
	margin-right: 15px;
}
</style>
</head>
<body style="">
	<div class="rollimgs">
		<div id="roll-images" class="carousel slide">
			<div class="carousel-inner">

				<div class="item active">
					
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-8 dinfo">
			<p style="font-size:15px;margin:0;padding-top:4px;">海尔空气MINI</p>
		</div>
		<div class="col-xs-4 dprice gopay">
			<p class="center " style="font-size:18px;padding-top:4px;">
				￥<span id="price"></span>
			</p>
			<p class="center " style="font-size:9px;">
				<strike>市场价￥<span id="oldprice"></span>
				</strike>
			</p>
		</div>
	</div>


	<!-- <div class="more-img">	 -->

	<!-- <div></div> -->


	<!-- </div> -->
	<div class="row">
		<div class="more-img" id="content">
			<!-- 内容 -->
		</div>
	</div>
	<div class="footer">
		<div class="row">
			<div class="more-img">
				<table style="width: 100%">
					<tbody>
						<tr>
							<td style="width: 36%">
								<button class="btn btn-default  btn-block buy gopay"
									style="text-align:center;height:40px;border:0;	">
									<div class="center">
										<div style="float:left;">
											<img alt="" src="image/haw_81.png"
												style="width:20px;height:20px;">
										</div>
										<div style="float:left;">&nbsp;立即秒杀</div>
									</div>
								</button></td>

						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>



</body>
</html>
