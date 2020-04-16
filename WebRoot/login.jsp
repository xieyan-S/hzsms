<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">    
  <!-- Loading Flat UI -->
  <link href="css/flat-ui.css" rel="stylesheet">
  <!-- 开始自定义样式 -->
  <link href="css/style.css" rel="stylesheet">
  <link rel="shortcut icon" href="images/favicon.ico">
  <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
  <![endif]-->

  <title>杭州职业技术学院 短消息管理系统</title>
</head>

<body class="loginbody">
    <nav class="navbar navbar-inverse navbar-lg navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
        <span class="sr-only">更多导航</span>
      </button>
      <a class="navbar-brand logo" href="./">杭州职业技术学院 短消息管理系统</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
      <ul class="nav navbar-nav">     
      </ul>   
    </div><!-- /.navbar-collapse -->
  </div>
  </nav><!-- /navbar -->


  <!-- 轮播组件开始 -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
        <li data-target="#myCarousel" data-slide-to="4"></li>
        <li data-target="#myCarousel" data-slide-to="5"></li>
        <li data-target="#myCarousel" data-slide-to="6"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active">
          <img src="images/skin/hzvtc00.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
        <div class="item">
          <img src="images/skin/hzvtc01.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
        <div class="item">
          <img src="images/skin/hzvtc02.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
        <div class="item">
          <img src="images/skin/hzvtc03.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
        <div class="item">
          <img src="images/skin/hzvtc04.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
        <div class="item">
          <img src="images/skin/hzvtc05.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
        <div class="item">
          <img src="images/skin/hzvtc06.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption"></div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
      </a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
      </a>
    </div>
    <!-- 轮播结束 -->
    <section>
    <div class="container" id="content">
      <div class="row">
        <!-- 登录窗体 -->    
        <form class="form-horizontal" role="form" method="post" action="user?action=login">
        <div class="loginbox col-sm-4 col-sm-push-4">
          <div class="login-form">
            <h2 class="title text-center"><span class="fui-user"></span>系统登录</h2>
			
				<!-- <input type="hidden" name="action" value="login"> -->
				<div class="form-group">
					<input name="username" type="text" class="form-control login-field" value=""
						placeholder="请输入用户名" id="username" /> <label
						class="login-field-icon fui-user" for="username"></label>
				</div>
				<div class="form-group">
					<input name="userpassword" type="password" class="form-control login-field" value=""
						placeholder="请输入密码" id="userpassword" /> <label
						class="login-field-icon fui-lock" for="userpassword"></label>
				</div>
				<div class="form-group row">
					<div class="col-lg-7">
						<input name="verifycode" type="text" class="form-control login-field" value=""
							placeholder="请输入验证码" id="verifycode" maxlength="4" />
					</div>
					<div class="col-lg-3">
						<img src="verifycode" class="img-rounded" alt="点击获取验证码"
							title="点击刷新"
							onclick="this.src='verifycode?code='+Date.parse(new Date())" />
					</div>
				</div>
				<div class="form-group">
			  		<c:if test="${error ne null}">
						<div class="col-sm-12">
							<p class="text-danger">${error }</p>
						</div>
					</c:if>
            	</div>
				<div class="form-group">
					<!-- <input id="login" type="button" class="btn btn-primary btn-lg btn-block"
						value="登    录" /> -->
					<button id="login" type="submit" class="btn btn-primary btn-lg btn-block"/>
					登    录</button>
				</div>
					
			
			<a class="login-link" href="register.jsp">我要注册</a>
          </div>
          </div>
        </div>
        </form>
        <!-- 登录尾部 --> 
        </div>
    </div>
    </section>    
  
  <c:import url="footer.jsp"/>

  <c:if test="${msg eq 1}">
	<script type="text/javascript">
		alert("用户注册成功!请登录系统");
	</script>
  </c:if>
  <c:if test="${msg eq -1}">
	<script type="text/javascript">
		alert("未知操作!请重新登录系统");
	</script>
  </c:if>
  <script type="text/javascript">
  	$("#login").click(function(){
		if($("#username").val()==""){
			alert("用户名不能为空!");
			$("#username").focus();
			return false;
		}else if($("#userpassword").val()==""){
			alert("密码不能为空!");
			$("#userpassword").focus();
			return false;
		}else if($("#verifycode").val()==""){
			alert("验证码不能为空!");
			$("#verifycode").focus();
			return false;
		}
		return true;
	});
  </script>

</body>
</html>