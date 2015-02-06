<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
				<li><a href="aboutus.jsp"><i class="icon-table"></i>关于我们</a></li>
<!-- 				<li><a href="technology.jsp"><i class="icon-tasks"></i>技术支持</a></li> -->
				<li><a href="news.jsp" class="open"><i class="icon-magic"></i>最新咨询</a></li>
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
					<span class="divider">/</span> <a href="news.jsp" class="bread-current">最新资讯</a>
				</div>

				<div class="clearfix"></div>

			</div>
			<!-- Page heading ends -->
			<!-- Matter-->
			<div class="matter">
				<div class="container">

					<!-- Table -->

					<div class="row">
						<div class="col-md-12">
							<div class="widget">
								<div class="widget-head">
									<div class="pull-left">已发布的产品</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i>
										</a> <a href="#" class="wclose"><i class="icon-remove"></i> </a>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="widget-content">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>标题</th>
												<th>咨询图片</th>
												<th>发布时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="news">
											<!-- 											<tr> -->
											<!-- 												<td>1</td> -->
											<!-- 												<td>Ashok</td> -->
											<!-- 												<td>India</td>  -->
											<!-- 												<td>23</td> -->
											<!-- 												<td> -->
											<!-- 													<button class="btn btn-xs btn-default"> -->
											<!-- 														<i class="icon-ok"></i> -->
											<!-- 													</button> -->
											<!-- 													<button class="btn btn-xs btn-default"> -->
											<!-- 														<i class="icon-pencil"></i> -->
											<!-- 													</button> -->
											<!-- 													<button class="btn btn-xs btn-default"> -->
											<!-- 														<i class="icon-remove"></i> -->
											<!-- 													</button></td> -->
											<!-- 											</tr> -->
										</tbody>
									</table>
									<div class="widget-foot">
										<!-- 查询 -->
										<ul class="pagination pull-right">
										</ul>

										<div class="clearfix"></div>
									</div>
								</div>
							</div>

							<div class="widget wgreen">

								<div class="widget-head">
									<div class="pull-left">发布产品</div>
									<div class="widget-icons pull-right">
										<a href="#" class="wminimize"><i class="icon-chevron-up"></i>
										</a> <a href="#" class="wclose"><i class="icon-remove"></i> </a>
									</div>
									<div class="clearfix"></div>
								</div>

								<div class="widget-content">
									<div class="padd">
										<!-- Form starts.  -->
										<form class="form-horizontal"
											action="../newServlet?news=insert" method="post"
											enctype="multipart/form-data">

											<div class="form-group">
												<label class="col-lg-4 control-label">标题</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" name="newTitle"
														placeholder="请输入标题">
												</div>
												<label class="col-lg-3 control-label" style="width: 250px;">请务必填写！！</label>
											</div>

											<div class="form-group">
												<label class="col-lg-4 control-label">资讯图片</label>
												<div class="col-lg-6">
													<div class="input-group">
														<input type="file" name="image" />
													</div>
												</div>
												<label class="col-lg-3 control-label" style="width: 250px;">像素360*240</label>
											</div>

											<div class="form-group">
												<label class="col-lg-8 control-label">资讯内容</label>
												<div class="col-lg-10">
													<textarea id="editor" name="newContent"></textarea>
												</div>
											</div>
											<hr />
											<div class="form-group">
												<div class="col-lg-offset-1 col-lg-9">
													<button type="submit"
														class="btn btn-primary btn-lg btn-block">发布资讯</button>
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
						Copyright &copy; 2014 | <a href="#">BorenBanner</a> | all rights reserved
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
<script src="js/jquery-geturlparam.js"></script>
	<script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script>
	<script type="text/javascript" src="release/ueditor.config.js"></script>
	<script type="text/javascript" src="release/ueditor.all.min.js"></script>
	<script type="text/javascript" src="release/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		UE.getEditor('editor', {
			toolbars : [ [ 'source', 'bold', 'italic', 'underline',
					'strikethrough', 'removeformat', '|', 'forecolor',
					'backcolor', 'insertorderedlist', 'insertunorderedlist',
					'|', 'fontfamily', 'fontsize', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyjustify', '|',
					'simpleupload', 'insertimage', '|', ] ],
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
			var page = $.getUrlParam('page');
			if (null == page) {
				page = 1;
			}
			$
					.ajax({
						type : "post",
						cache : false,
						url : "../newServlet",
						data : {
							"news" : "query",
							"page" : page
						},
						dataType : "text",
						success : function(data) {

							var obj = eval("(" + data + ")");

							var htm = "";
							for (key in obj.newList) {
								htm += "<tr><td>"
										+ obj.newList[key].newTitle
										+ "</td>"
										+ "<td>"
										+ "<a target='_blank' href='"+obj.newList[key].newImage+""
										+ "'>产品图片预览</a>"
										+ "</td>"
										+ "<td>"
										+ obj.newList[key].newTime
										+ "</td>"
										+ "<td>"
										+ "<button class=\"btn btn-xs btn-default\" onclick=\"update(this,'"
										+ obj.newList[key].newId
										+ "');\"><i class=\"icon-pencil\"></i></button>"
										+ "<button class=\"btn btn-xs btn-default \" onclick=\"removeProduct(this,'"
										+ obj.newList[key].newId
										+ "');\"><i class=\"icon-remove\"></i></button>"
										+ "</td></tr>";
							}
							$("#news").empty().append(htm);
							$(".pagination").twbsPagination({
								totalPages : parseInt(obj.size),
								startPage : page,
								visiblePages : 10,
								first : '首页',
								prev : '前一页',
								next : '下一页',
								last : '尾页',
								href : 'news.jsp?page={{number}}',
							});
						}

					});
		});

		function update(obj, id) {

			window.location.href = "../newServlet?news=querysigle&newId="+id;

		}
		function removeProduct(obj, id) {

			$.ajax({
				type : "post",
				cache : false,
				url : "../newServlet",
				data : {
					"news" : "delete",
					"newId" : id
				},
				dataType : "text",
				success : function(data) {

					$(obj).parent().parent().remove();
				}
			});
		}
	</script>
</body>
</html>
