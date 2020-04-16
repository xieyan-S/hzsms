<%@ page language="java" import="java.util.*,cn.edu.hzvtc.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.loginuser eq null }">
	<script type="text/javascript">
		window.location.href="login.jsp";
	</script>
</c:if>

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
			<li id="menu1"><a href="msg?action=main">首页</a></li>
            <li id="menu2" class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">消息管理<b class="caret"></b></a>
          <span class="dropdown-arrow"></span>
          <ul class="dropdown-menu">
			<li><a href="msg?action=list&arrow=-1">查看我接收的消息</a></li>
            <li><a href="msg?action=list&arrow=1">查看我发送的消息</a></li>
          </ul>
        </li>			
			<li id="menu3"><a href="msg?action=new">发送消息</a></li>
      </ul>   
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="fui-user"></span><%UserInfo user=(UserInfo)session.getAttribute("loginuser");%>
            <%=user.getUserName()%>
            <b class="caret"></b>
          </a>
          <ul class="dropdown-menu">
            <li>
              <a href="pwdupdate.jsp">
                <span class="glyphicon glyphicon-edit"></span>
                修改密码
              </a>
            </li>
            <li class="divider"></li>
            <li>
              <a href="user?action=logout">
                <span class="glyphicon glyphicon-off"></span>
                退出登录
              </a>
            </li>
          </ul>
        </li>
      </ul>        
    </div><!-- /.navbar-collapse -->
  </div>
  </nav><!-- /navbar -->