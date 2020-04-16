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
  <link href="css/webuploader.css" rel="stylesheet">
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

<body class="mainbody">
  <c:import url="nav.jsp"/>

  <section>
    <div class="container">
      <ol class="breadcrumb">
        <li>
          <a href="login.jsp">首页</a>
        </li>
        <li class="active">用户注册</li>
      </ol>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
          <form class="form-horizontal" role="form" method="post" action="user">
			<input type="hidden" name="action" value="registe">
            <div class="form-group">
              <label for="username" class="col-sm-2 control-label">用户名</label>
              <div class="col-sm-10">
                <input name="username" type="text" class="form-control" id="username" placeholder="用户名">
              </div>
            </div>  
			<div class="form-group">
              <label for="password" class="col-sm-2 control-label">密码</label>
              <div class="col-sm-10">
                <input name="userpassword" type="text" class="form-control" id="userpassword" placeholder="密码">
              </div>
            </div>
			<div class="form-group">
              <label for="confirmPassword" class="col-sm-2 control-label">确认密码</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="confirmPassword" placeholder="确认密码">
              </div>
            </div>
			<div class="form-group">
              <label for="email" class="col-sm-2 control-label">邮箱</label>
              <div class="col-sm-10">
                <input name="useremail" type="text" class="form-control" id="useremail" placeholder="邮箱">
              </div>
            </div>
            
			<div class="form-group">
			  <c:if test="${error ne null}">
	          <label for="error" class="col-sm-2 control-label"></label>
	           	<div class="col-sm-10">
	           		<p class="text-danger">${error }</p>
	           	</div>
	          </c:if>
              <!-- <label for="error" class="col-sm-2 control-label"></label>
              <div class="col-sm-10">
                <p class="text-danger">错误消息</p>
              </div> -->
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button id="register" type="submit" class="btn btn-primary btn-hg btn-embossed btn-block" href="#"><span class="fui-new"></span>我要注册</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

  <c:import url="footer.jsp"/>

  <script type="text/javascript">
  	$("#register")
  			.click(
  					function(){
  						if($("#username").val()==""){
  							alert("用户名不能为空!");
  							return false;
  						}else if($("#userpassword").val()==""){
  							alert("密码不能为空!");
  							return false;
  						}else if($("#userpassword").val()!=$("#confirmPassword").val()){
  							alert("两次密码不同!");
  							return false;
  						}else if($("#useremail").val()==""){
  							alert("邮箱不能为空!");
  							return false;
  						}
  			
  					});
  </script>
</body>
</html>