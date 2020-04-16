<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<!-- Loading Flat UI -->
<link href="css/flat-ui.css" rel="stylesheet" />
<link href="css/webuploader.css" rel="stylesheet" />
<!-- 开始自定义样式 -->
<link href="css/style.css" rel="stylesheet" />
<link rel="shortcut icon" href="images/favicon.ico" />
</head>

<body class="mainbody">
	<nav class="navbar navbar-inverse navbar-lg navbar-fixed-top"
		role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse-01">
				<span class="sr-only">更多导航</span>
			</button>
			<a class="navbar-brand logo" href="./">杭州职业技术学院 短消息管理系统</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse-01"></div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>
	<!-- /navbar -->

	<section>
	<div class="container">
		<div><h1><p class="text-danger text-center">网站要爆炸了，待会再试！</p></h1></div>
		<div><h3><p class="text-info text-center">${error }</p></h3></div>
		<div class="text-center"><a href="login.jsp">返回</a></div>
	</div>
	</section>

	<c:import url="footer.jsp"></c:import>
</body>
</html>