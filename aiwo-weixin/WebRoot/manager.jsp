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
<base href="<%=basePath%>">

<title>管理设备</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
.deviceList {
	-moz-user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	display: inline-block;
	font-size: 14px;
	font-weight: normal;
	line-height: 1.42857;
	margin-bottom: 0;
	padding: 6px 12px;
	text-align: center;
	vertical-align: middle;
	white-space: nowrap;
	margin-top: 20px;
	background-color: #999;
	color: #fff;
	width: 80%;
}

.managerBar {
	float: right;
	width: 20px;
	display: inline;
	margin-right: 5px;
}

.main {
	text-align: center;
	margin: auto;
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

.more-img {
	margin-bottom: 60px;
	margin-left: 15px;
	margin-right: 15px;
}

.center {
	display: table;
	text-align: center;
	margin: 0 auto;
}

.dinfo {
	background: #0099FE;
}

.dprice,.buy {
	background: #FF3399;
	color: #ffffff;
}


.span{
	background: black;
	
}
</style>
</head>

<body>
	<input type="hidden" name="userId" value="${userId }">
	<div class="main"></div>
	<div class="footer">
		<div class="row">
			<div class="more-img">
				<table style="width: 100%">
					<tbody>
						<tr>
							<td style="width: 36%"><a
								class="btn btn-default  btn-block buy gopay" href="index.jsp"
								style="text-align:center;height:40px;border:0;	">
									<div class="center">
										<div style="float:left;">
											<img alt="" src="image/haw_22.png"
												style="width:20px;height:20px;">
										</div>
										<div style="float:left;">&nbsp;返回</div>
									</div> </a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var method = "444";

			$.ajax({
						type : "POST",
						url : "managerServlet",
						data : {
							"method" : method,
							"userId" : '1'
							//$('input[name=userId]').val()
						},
						dataType : "json",
						success : function(data) {
							var obj = data;

							var html = "";
							$(obj).each(
											function() {
												html += "<div class=\"deviceList\"><img src=\"image/haw_22.png\" style=\" float:left ;width: 20px; display: inline;\" class=\"img-rounded img-responsive\">"
														+ "<span onbeforeeditfocus=\"this.oldData=this.firstChild.data\" onblur=\"this.oldData==this.firstChild.data?void(0):updateDeviceName('"
														+ this.macId
														+ "',this);\" onclick=\"update(this);\" style=\"float: left;\">"
														+ this.deviceName
														+ "</span>"
														+ "<img src=\"image/del.png\" alt=\"\" class=\"img-rounded img-responsive managerBar\" onclick=\"deletedevice('"
														+ this.macId
														+ "',this);\"></div>";
											});
							$(".main").empty().append(html);
						}
					});
		});

		function update(obj) {

			$(obj).attr("contenteditable", true);
			$(obj).addClass("span");
		}

		function updateDeviceName(mac, obj) {
			var macId = mac;
			var method = "222";
			var macName = $(obj).text();
			$(obj).removeClass("span");
			$.ajax({
				type : "POST",
				url : "managerServlet",
				data : {
					"macId" : macId,
					"method" : method,
					"macName" : macName
				},
				success : function(data) {
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
					"userId" : '1'
// 					$("input[name=userId]").val()
				},
				success : function(data) {
					$(obj).parent().remove();
				}
			});
		}
	</script>

</body>
</html>
