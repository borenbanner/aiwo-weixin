<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>爱窝管理系统</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="">
<meta name="author" content="">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">
<!-- Main stylesheet -->
<link href="css/style.css" rel="stylesheet">
<!-- Widgets stylesheet -->
<link href="css/widgets.css" rel="stylesheet">
<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

<!-- Favicon -->
<!-- <link rel="shortcut icon" href="img/favicon/favicon.png"> -->
</head>

<body>

	<!-- Header starts -->
	<header>
		<div class="container">
			<div class="row">

				<!-- Logo section -->
				<div class="col-md-12">
					<!-- Logo. -->
					<div class="logo">
						<h1>
							<a href="#">爱窝产品发布及技术支持管理平台<span class="bold"></span> </a>
						</h1>
						<p class="meta">爱窝管理系统</p>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Header ends -->

	<!-- Main content starts -->

	<div class="content">

		<!-- Sidebar -->
		<div class="sidebar">
			<div class="sidebar-dropdown">
				<a href="#">导航</a>
			</div>

			<ul id="nav">
				<!-- Main menu with font awesome icon -->
				<li><a href="index.jsp" class="open"><i class="icon-home"></i>
						首页</a>
				<li><a href="release.jsp"><i class="icon-bar-chart"></i>产品发布</a>
				</li>
				<li><a href="aboutus.jsp" class="open"><i
						class="icon-table"></i>关于我们</a></li>
<!-- 				<li><a href="technology.jsp"><i class="icon-tasks"></i>技术支持</a> -->
				</li>
				<li><a href="news.jsp"><i class="icon-magic"></i>最新咨询</a></li>
				<li><a href="bill.jsp"><i class="icon-magic"></i>订单查看</a></li>
			</ul>
		</div>

		<!-- Sidebar ends -->

		<!-- Main bar -->
		<div class="mainbar">

			<!-- Page heading -->
			<div class="page-head">
				<h2 class="pull-left">
					<i class="icon-home"></i> 首页
				</h2>

				<!-- Breadcrumb -->
				<div class="bread-crumb pull-right">
					<a href="index.jsp"><i class="icon-home"></i> 首页</a>
					<!-- Divider -->
					<span class="divider">/</span> <a href="#" class="bread-current">关于我们</a>
				</div>

				<div class="clearfix"></div>

			</div>
			<!-- Page heading ends -->

			<!-- Matter-->

			<div class="matter">
				<div class="container">

					<div class="row">

						<div class="col-md-12">

							<div class="widget wred">
								<div class="widget-head">
									<div class="pull-left">关于我们</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i>
										</a> <a href="#" class="wclose"><i class="icon-remove"></i> </a>
									</div>
									<div class="clearfix"></div>
								</div>

								<div class="widget-content">
									<div class="padd">


										<div id="js_article" class="rich_media">

											<div id="js_top_ad_area" class="top_banner"></div>

											<div class="rich_media_inner">
												<h2 class="rich_media_title" id="titlname"></h2>
												<div class="rich_media_meta_list">
													<em id="post-date"
														class="rich_media_meta rich_media_meta_text"></em>

													<div
														class="rich_media_meta rich_media_meta_link rich_media_meta_nickname">爱窝智慧家居</div>
												</div>
												<div id="page-content">
													<div id="img-content">
														<div class="rich_media_thumb_wrp" id="media">
															
														</div>

														<div class="rich_media_content" id="js_content"></div>


														<link rel="stylesheet" type="text/css"
															href="css/page_mp_article_improve_combo231ee1.css">

													</div>


													<div id="js_bottom_ad_area"></div>


													<div id="js_iframetest" style="display:none;"></div>
												</div>
											</div>
										</div>

										<hr />
										<!-- Button to trigger modal -->
										<a href="/control/aboutusup.jsp"
											class="btn btn-primary btn-lg btn-block" data-toggle="modal">编辑关于我们</a>

										<!-- Modal -->
										<hr />






									</div>
								</div>
								<div class="widget-foot">
									<!-- Footer goes here -->
								</div>
							</div>

						</div>

					</div>

				</div>
			</div>

			<!-- Matter ends -->



		</div>

		<!-- Mainbar ends -->
		<div class="clearfix"></div>

	</div>
	<!-- Content ends -->

	<!-- Footer starts -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<!-- Copyright info -->
					<p class="copy text-center">
						Copyright &copy; 2014 | <a href="#">BorenBanner</a> | all rights
						reserved
					</p>
				</div>
			</div>
		</div>
	</footer>

	<!-- Footer ends -->


	<script src="../js/jquery-1.10.2.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="js/filter.js"></script>
	<script src="js/custom.js"></script>
	<script type="text/javascript">
		$(function() {

			$.ajax({
				type : "post",
				url : "../usServlet",
				dataType : "json",
				data:{"flg":"query"},
				success : function(data) {
					var obj = data ;
					$("#titlname").text(obj.usTitle) ;
					$("#post-date").text(obj.usTime.substring(0,10));
					var ht = "<img src=\""+obj.usImage+"\" class=\"rich_media_thumb\" >"
					$("#media").empty().append(ht);
					$("#js_content").empty().append(obj.usContent);

				}
			});

		});
	</script>
</body>
</html>
