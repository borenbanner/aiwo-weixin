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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">


<title>智能传感器</title>

<link rel="stylesheet" type="text/css"
	href="control/css/page_mp_article_improve2318b8.css">
<style>
.list-paddingleft-2 {
	padding-left: 30px;
}

blockquote {
	margin: 0;
	padding-left: 10px;
	border-left: 3px solid #DBDBDB;
}
</style>

</head>
<body id="activity-detail" class="zh_CN ">

	<div id="js_article" class="rich_media">

		<div id="js_top_ad_area" class="top_banner"></div>

		<div class="rich_media_inner">
			<h2 class="rich_media_title" id="titlname"></h2>
			<div class="rich_media_meta_list">
				<em id="post-date" class="rich_media_meta rich_media_meta_text"></em>
				<div
					class="rich_media_meta rich_media_meta_link rich_media_meta_nickname">爱窝智慧家居</div>
			</div>
			<div id="page-content">
				<div id="img-content">
					<div class="rich_media_thumb_wrp" id="media">
					</div>
					<div class="rich_media_content" id="js_content"></div>
					<link rel="stylesheet" type="text/css"
						href="control/css/page_mp_article_improve_combo231ee1.css">
				</div>
				<div id="js_bottom_ad_area"></div>
				<div id="js_iframetest" style="display:none;"></div>
			</div>
		</div>
	</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
		$(function() {

			$.ajax({
				type : "post",
				url : "usServlet",
				dataType : "json",
				data:{"flg":"query"},
				success : function(data) {
					var obj = data ;
					$("#titlname").text(obj.usTitle) ;
					$("#post-date").text(obj.usTime.substring(0,10));
					var ht = "<img src=\"control/"+obj.usImage+"\" class=\"rich_media_thumb\" >"
					$("#media").empty().append(ht);
					$("#js_content").empty().append(obj.usContent);

				}
			});

		});
	</script>
</body>
</html>