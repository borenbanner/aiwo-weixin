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
						class="icon-table"></i>关于我们</a>
				</li>
<!-- 				<li><a href="technology.jsp"><i class="icon-tasks"></i>技术支持</a> -->
				</li>
				<li><a href="news.jsp"><i class="icon-magic"></i>最新咨询</a>
				</li>
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


										<form class="form-horizontal" action="../usServlet?flg=update"
											method="post" enctype="multipart/form-data">

											<div class="form-group">
												<label class="col-lg-4 control-label">标题</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" name="usTitle"
														placeholder="请输入标题" value="">
												</div>
												<label class="col-lg-3 control-label" style="width: 250px;">请务必填写标题！！</label>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label">产品图片</label>
												<div class="col-lg-6">
													<input type="file" name="image">
												</div>
												<label class="col-lg-3 control-label" style="width: 250px;">像素要求640*360</label>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label">内容</label>
												<div class="col-lg-8">
													<textarea id="editor" name="usContent"></textarea>
												</div>
											</div>

											<hr />
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-9">
													<button type="submit"
														class="btn btn-primary btn-lg btn-block">确定编辑</button>
												</div>
											</div>

										</form>


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
	<script type="text/javascript" src="release/ueditor.config.js"></script>
	<script type="text/javascript" src="release/ueditor.all.min.js"></script>
	<script type="text/javascript" src="release/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		UE.getEditor('editor',
				{
					toolbars : [ [ 'source', 'bold', 'italic', 'underline',
							'strikethrough', 'removeformat', '|', 'forecolor',
							'backcolor', 'insertorderedlist',
							'insertunorderedlist', '|', 'fontfamily',
							'fontsize', '|', 'justifyleft', 'justifycenter',
							'justifyright', 'justifyjustify', '|', ] ],
					//focus时自动清空初始化时的内容  
					autoClearinitialContent : false,
					//关闭字数统计  
					wordCount : false,
					//关闭elementPath  
					elementPathEnabled : false,
					//默认的编辑区域高度  
					initialFrameHeight : 300
				});
	</script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				type : "post",
				url : "../usServlet",
				dataType : "json",
				data : {
					"flg" : "query"
				},
				success : function(data) {
					var obj = data;
					$("input[name=usTitle]").val(obj.usTitle);
					$("input[name=image]").after("<span>"+obj.usImage+"</span>");
					UE.getEditor('editor').setContent(obj.usContent);
				}
			});

		});
	</script>
</body>
</html>
