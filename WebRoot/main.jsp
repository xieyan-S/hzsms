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
    <div class="row">
      <div class="col-xs-6">
          <div class="tile">
            <img src="images/icons/svg/chat.svg" alt="Clocks" class="tile-image big-illustration">
            <h3 class="tile-title">我接收的消息</h3>
			<p></p>
            <!-- <a class="btn btn-danger btn-hg btn-embossed btn-block" href="msglist.jsp"><span class="fui-list"></span> 未读 <span class="badge">10</span></a> -->
			<a
				<c:if test="${recvcount eq 0}">
					class="btn btn-danger btn-hg btn-embossed btn-block disabled"
				</c:if>
				<c:if test="${recvcount gt 0}">
					class="btn btn-danger btn-hg btn-embossed btn-block"
				</c:if>
				href="msg?action=list&state=1&arrow=-1"><span class="fui-list"></span>
				未读 <span class="badge">${recvcount }</span>
			</a>
			<!-- <a class="btn btn-info btn-hg btn-embossed btn-block" href="msglist.jsp"><span class="fui-checkbox-checked"></span> 全部 </a> -->
			<a class="btn btn-info btn-hg btn-embossed btn-block" 
			href="msg?action=list&arrow=-1"><span 
			class="fui-checkbox-checked"></span> 全部 </a>
          </div>
        </div>
      <div class="col-xs-6">
          <div class="tile">
            <img src="images/icons/svg/clipboard.svg" alt="Clipboard" class="tile-image big-illustration">
            <h3 class="tile-title">我发送的消息</h3>
            <p></p>
			<a
				<c:if test="${sendcount eq 0}">
					class="btn btn-danger btn-hg btn-embossed btn-block disabled"
				</c:if>
				<c:if test="${sendcount gt 0}">
					class="btn btn-danger btn-hg btn-embossed btn-block"
				</c:if>
				href="msg?action=list&state=1&arrow=1">
				<span class="fui-list"></span> 未读 
				<span class="badge">${sendcount }</span>
			</a>
			<a class="btn btn-info btn-hg btn-embossed btn-block" href="msg?action=list&arrow=1"><span class="fui-checkbox-checked"></span> 全部 </a>
          </div>
        </div>
  </div>
  </section>

  <c:import url="footer.jsp"/>
  <c:if test="${msg eq 1}">
  	<script type="text/javascript">
  		alert("用户密码修改成功!");
  	</script>
  </c:if>
  <c:if test="${msg eq 2}">
  	<script type="text/javascript">
  		alert("短消息发送成功!");
  	</script>
  </c:if>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$("#menu1").addClass("active");
  	});
  </script>
</body>
</html>