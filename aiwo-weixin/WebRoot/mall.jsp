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

<title>爱窝商城</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />

<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="control/js/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="control/js/jquery-geturlparam.js"></script>
<script type="text/javascript">
	$(function() {
		var page = $.getUrlParam('page');
		if (null == page) {
			page = 1;
		}
		$
				.ajax({

					type : "post",
					cache : false,
					url : "productServlet",
					data : {
						"method" : "queryAll",
						"page" : page
					},
					dataType : "json",
					success : function(data) {
						var obj = data;
						var htm = "";
						for (key in obj.productList) {
							htm += "<div>"
									+ "<img src=\""
									+ obj.productList[key].proImage
									+ "\"  class=\"img-responsive\" onclick=\"gopay("
									+ obj.productList[key].proId
									+ ")\">"
									+ "</div><div class=\"row\"><div class=\"col-xs-8 dinfo\">"
									+ "<p>"
									+ obj.productList[key].proName
									+ "</p>"
									+ "</div><div class=\"col-xs-4 dprice gopay\">"
									+ "<p class=\"center \">￥"
									+ obj.productList[key].proPrice + "</p>"
									+ "</div></div>";
						}
						$("#info").empty().append(htm);
						$(".pagination").twbsPagination({
							totalPages : parseInt(obj.size),
							startPage : parseInt(page),
							visiblePages : 3,
							first : '首页',
							prev : '前一页',
							next : '下一页',
							last : '尾页',
							href : 'mall.jsp?page={{number}}'
						});
					}
				});

	});

	function gopay(id) {
		window.location.href = "malldetail.jsp?id=" + id;
	}
</script>
<style>
.dinfo {
	background: #0099FE;
}

.dinfo p {
	font-size: 15px;
	margin: 0;
	padding-top: 13px;
}

.pages {
	background: -moz-linear-gradient(center top, #F8F8F8, #F2F2F2) repeat
		scroll 0 0 rgba(0, 0, 0, 0);
	border-bottom: 1px solid #FFFFFF;
	border-bottom-left-radius: 3px;
	border-bottom-right-radius: 3px;
	border-top: 1px solid #CCCCCC;
	box-shadow: 0 1px 1px #FFFFFF inset;
	color: #555555;
	font-size: 12px;
	padding: 4px 7px;
	text-shadow: 0 1px #FFFFFF
}

.pagination {
	margin: 5px 0 5px;
}

.dprice,.buy {
	background: #FF3399;
	color: #ffffff;
}

.dprice,.dinfo {
	height: 50px;
	color: #ffffff;
}

.row {
	margin-left: 0px;
	margin-right: 0px;
}

.center {
	display: table;
	text-align: center;
	margin: 0 auto;
	font-size: 18px;
	padding-top: 13px;
}

.p-info {
	background-color: #f0f0f0;
	margin-bottom: 7px;
	margin-top: 7px;
	margin-left: 10px;
	margin-right: 10px;
	/* border:1px solid #CCCCCC; */
	-webkit-box-shadow: 0 0 0 #666, 0 0 0 #666, 0 2px 2px #666, 0 0 0 #666;
	-moz-box-shadow: 0 0 0 #666, 0 0 0 #666, 0 2px 2px #666, 0 0 0 #666;
	box-shadow: 0 0 0 #666, 0 0 0 #666, 0 2px 2px #666, 0 0 0 #666;
}

body {
	background-color: #CCCCCC;
}
</style>
</head>

<body style="">
	<div class="p-info">
		<div id="info"></div>
	</div>

	<div class="pages">
		<ul class="pagination center"></ul>
	</div>

</body>
</html>
