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

<title>爱窝智慧家庭-登录用户绑定</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />
<!-- CSS -->
<link rel="stylesheet" href="assets/css/reset.css">
<link rel="stylesheet" href="assets/css/supersized.css">
<link rel="stylesheet" href="assets/css/style.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

</head>

<body>

	<div class="page-container">
		<h1>登录</h1>
		<form action="loginServlet" method="post">
			<input type="hidden" name="openId" value="${openId }"/>
			<input type="hidden" name="key" value="${key }" /> 
			<input type="text"
				name="username" class="username" placeholder="请输入注册的app名称">
			<input type="password" name="password" class="password"
				placeholder="请输入密码">
			<button type="submit">登录</button>
			<!--                 <div class="error"><span>用户名或密码错误+</span></div> -->
		</form>
		<div class="connect">
			<p>绑定前确保通过app注册</p>
			<a href="www.baidu.com">我要下载app</a>
			<p class="error">${error }</p>
		</div>
	</div>

	<!-- Javascript -->
	<script src="assets/js/jquery-1.8.2.min.js"></script>
	<script src="assets/js/supersized.3.2.7.min.js"></script>
	<script src="assets/js/supersized-init.js"></script>
	<script src="assets/js/scripts.js"></script>

</body>
</html>
