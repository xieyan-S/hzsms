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
          <a href="main.html">首页</a>
        </li>
        <li class="active">发送消息</li>
      </ol>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
          <form class="form-horizontal" role="form" method="post" action="msg">
          	<input type="hidden" name="action" value="send">
            <div class="form-group">
              <label for="toUser" class="col-sm-2 control-label">发送给</label>
              <div class="col-sm-10">
                <select id="receiveuser" name="receiveuser">
                  <optgroup label="请选择...">
                  	<c:forEach items="${userinfos }" var="userinfo">
                  		<option value="${userinfo.userId}"
                  			<c:if test="${userinfo.userId eq messageinfo.msgReceiver.userId}">
                  				selected="selected"
                  			</c:if>>${userinfo.userName }
                  		</option>
                  	</c:forEach>
                  </optgroup>
                </select>
              </div>
            </div>
			<div class="form-group">
              <label for="title" class="col-sm-2 control-label">标题</label>
              <div class="col-sm-10">
                <input name="msgtitle" type="text" class="form-control" id="msgtitle" placeholder="标题">
              </div>
            </div>
			<div class="form-group">
              <label for="message" class="col-sm-2 control-label">消息</label>
              <div class="col-sm-10">
                <textarea name="msgcontent" placeholder="消息" class="form-control" rows="5" id="msgcontent"></textarea>
              </div>
            </div>
            <div class="form-group">
              <label for="needreply" class="col-sm-2 control-label">是否需要回复</label>
              <div class="col-sm-10">
                <select id="needreply" name="needreply">
                  <optgroup label="请选择...">
                    <option value="0">不需要</option>
                    <option value="1">需要</option>
                  </optgroup>
                </select>
              </div>
            </div>
            <div class="form-group">
			  <c:if test="${error ne null}">
	          <label for="error" class="col-sm-2 control-label"></label>
	           	<div class="col-sm-10">
	           		<p class="text-danger">${error }</p>
	           	</div>
	          </c:if>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button id="send" type="submit" class="btn btn-primary btn-hg btn-embossed btn-block" href="#"><span class="fui-new"></span>我要发送</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>

  <c:import url="footer.jsp"/>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$("#menu3").addClass("active");
  	});
  </script>
    <script type="text/javascript">
  	$("#send").click(
			function(){
				if($("#msgtitle").val()==""){
					alert("标题不能为空!");
					return false;
				}else if($("#msgcontent").val()==""){
					alert("内容不能为空!");
					return false;
				}
				return true;
			});
  </script>
</body>
</html>