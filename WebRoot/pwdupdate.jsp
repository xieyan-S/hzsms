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
        <li class="active">修改密码</li>
      </ol>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
          <form class="form-horizontal" role="form" method="post" action="user">
			<input type="hidden" name="action" value="pwdupdate">  
			<div class="form-group">
              <label for="oldpassword" class="col-sm-2 control-label">旧密码</label>
              <div class="col-sm-10">
                <input name="oldpassword" type="text" class="form-control" id="oldpassword" placeholder="密码">
              </div>
            </div>
            <div class="form-group">
              <label for="newpassword" class="col-sm-2 control-label">新密码</label>
              <div class="col-sm-10">
                <input name="newpassword" type="text" class="form-control" id="newpassword" placeholder="密码">
              </div>
            </div>
			<div class="form-group">
              <label for="confirmPassword" class="col-sm-2 control-label">确认新密码</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="confirmPassword" placeholder="确认密码">
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
                <button id="pwdupdate" type="submit" class="btn btn-primary btn-hg btn-embossed btn-block" href="#"><span class="fui-new"></span>我要修改</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

  <c:import url="footer.jsp"/>
  <script type="text/javascript">
  	$("#pwdupdate")
  			.click(
  					function(){
  						if($("#oldpassword").val()==""){
  							alert("旧密码不能为空!");
  							return false;
  						}else if($("#newpassword").val()==""){
  							alert("新密码不能为空!");
  							return false;
  						}else if($("#newpassword").val()!=$("#confirmPassword").val()){
  							alert("两次新密码不同!");
  							return false;
  						}else if($("#oldpassword").val()==$("#newpassword").val()){
  							alert("新旧密码相同!");
  							return false;
  						}
  					});
  </script>
</body>
</html>