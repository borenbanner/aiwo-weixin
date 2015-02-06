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

<title>爱窝智能家居-无设备</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="viewport"
content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<style type="text/css">
	.center{
/*        	background: url("image/warning.png") no-repeat center center/cover rgba(255,255,255,0); */
		margin-left: auto; 
		margin-right: auto;
		width: 300px ;
		height: 300px;
		text-align: center;
		
	}
	.center img {
		width: 100%;
		height: 100%;
	}
	h3{
		font-size: 18px;
		font-weight: 800;
		color:red;
	}

</style>

</head>
<body>
	<div class="container">
		<div class="center">
		 	<img src="image/warning.png">	
		 	<h3>未绑定设备，<br>请通过app添加设备！！</h3>
		</div>
	</div>
</body>
</html>
