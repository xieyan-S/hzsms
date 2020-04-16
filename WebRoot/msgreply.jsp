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
          <a href="msg?action=main">首页</a>
        </li>
        <li class="active">回复消息</li>
      </ol>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
          <form class="form-horizontal" role="form" method="post" action="msg?href=${href }">
          	<input type="hidden" name="action" value="update">
          	<input type="hidden" name="msgid" value="${messageinfo.msgId }">
 <%--          	<input type="hidden" name="href" value="${href }"> --%>
            <div class="form-group">
              <label for="title" class="col-sm-2 control-label">标题</label>
              <div class="col-sm-10">
                ${messageinfo.msgTitle }
              </div>
			</div>
			<div class="form-group">
              <label for="title" class="col-sm-2 control-label">发送者</label>
              <div class="col-sm-10">
                ${messageinfo.msgSender.userName }
              </div>
			</div>
			<%-- <p>
              <span class="col-sm-2">发送者</span>
              <span class="col-sm-10">
                ${messageinfo.msgSender.userName }
              </span>
			</p> --%>
			<div class="form-group">
              <label for="title" class="col-sm-2 control-label">标题</label>
              <div class="col-sm-10">
                ${messageinfo.msgTitle }
              </div>
			</div>
			<div class="form-group">
              <label for="title" class="col-sm-2 control-label">发送时间</label>
              <div class="col-sm-10">
                ${messageinfo.msgCreateDate }
              </div>
			</div>
			<div class="form-group">
              <label for="title" class="col-sm-2 control-label">内容</label>
              <div class="col-sm-10">
                ${messageinfo.msgContent }
              </div>
			</div>
			<hr>
			<div class="form-group">
              <label for="message" class="col-sm-2 control-label">回复</label>
              <div class="col-sm-10">
                <textarea name="reply" placeholder="回复" class="form-control" rows="5" id="reply"></textarea>
              </div>
            </div>
            <hr>
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
                <button id="send" type="submit" class="btn btn-primary btn-hg btn-embossed btn-block" href="#"><span class="glyphicon glyphicon-log-out"></span>我要回复</button>
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
  		$("#menu2").addClass("active");
  	});
  </script>
    <script type="text/javascript">
  	$("#send").click(
			function(){
				if($("#reply").val()==""){
					alert("回复消息不能为空!");
					return false;
				}
				return true;
			});
  </script>
</body>
</html>