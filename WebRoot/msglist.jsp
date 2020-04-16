<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
        <li class="active">
        	<c:if test="${arrow eq 1 }">查看我发送的消息
        		<c:if test="${state eq 1 }">
        			<strong style="color:#FF0000;">（未读）</strong>
        		</c:if>
        	</c:if>
        	<c:if test="${arrow eq -1 }">查看我接收的消息
        		<c:if test="${state eq 1 }">
        			<strong style="color:#FF0000;">（未读）</strong>
        		</c:if>
        	</c:if>
        </li>
      </ol>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
		  
		   <form action="msg?action=list" name="queryForm" method="post">
              	<input id="state" type="hidden" name="state" value="0" />
              	<input id="arrow" type="hidden" name="arrow" value="1" />
              	<input type="hidden" name="pageNo" value="1" />
				<input type="hidden" name="pageSize" value="5" />
          <table class="table table-hover table-striped">
            <thead>
              <tr>
                <th class="col-sm-4">消息标题</th>
                <c:if test="${arrow eq 1 }">
					<th class="col-sm-1">接收者</th>
				</c:if>
				<c:if test="${arrow eq -1 }">
					<th class="col-sm-1">发送者</th>
				</c:if>
                <th class="col-sm-3">时间</th>
                <th class="col-sm-1">状态</th>
                <th class="col-sm-3">操作</th>
              </tr>
            </thead>
            <tbody>   
			  <c:if test="${pageData eq null || pageData.items eq null || fn:length(pageData.items) eq 0}">
			  		<td colspan="5" class="text-center">
			  			<p class="text-info">没有相关数据!</p>
			  		</td>
			  </c:if>
			  <c:if 
			  		test="${pageData ne null || pageData.items ne null || fn:length(pageData.items) gt 0}">
			  		<c:forEach items="${pageData.items }" var="messageinfo">
			  			
			  			<tr>
			  				<td>${messageinfo.msgTitle }</td>
			  				<c:if test="${arrow eq 1 }">
			  					<td>${messageinfo.msgReceiver.userName }</td>
			  				</c:if>
			  				<c:if test="${arrow eq -1 }">
			  					<td>${messageinfo.msgSender.userName }</td>
			  				</c:if>
			  				<td>${messageinfo.msgCreateDate }</td>
			  				<td>
			  					<c:if test="${messageinfo.msgState eq 1 }">
			  						<p class="text-danger">未读</p>
			  					</c:if>
			  					<c:if test="${messageinfo.msgState eq 2 }">
			  						<c:if test="${messageinfo.msgNeedReply }">
			  							<p class="text-warning">已读/未回复</p>
			  						</c:if>
			  						<c:if test="${!messageinfo.msgNeedReply }">
			  							<p class="text-info">已读</p>
			  						</c:if>
			  					</c:if>
			  					<c:if test="${messageinfo.msgState eq 3 }">
			  						<p class="text-info">已回复</p>
			  					</c:if>
			  				</td>
			  				<td><a
			  					<c:if test="${arrow eq -1 }">
href="msg?action=detail&msgid=${messageinfo.msgId }&arrow=${arrow }&state=${state }&pageNo=${pageData.pageNo }&pageSize=${pageData.pageSize }"
			  					</c:if>
			  					<c:if test="${arrow eq 1 }">
href="msg?action=detail&msgid=${messageinfo.msgId }"
			  					</c:if>
			  					class="btn btn-primary" data-toggle="modal"
			  					data-target="#msgModal">								<!-- ${arrow eq -1 }的href是转页面的关闭，提交已读的参数，记录当前页面的arrow(发送收到)和state(未读筛选)方便回转 -->
			  						<span class="fui-eye"></span> 查看					<!-- ${arrow eq 1 }的href不需要交互，普通的关闭 -->
			  					</a>
			  					<c:if test="${arrow eq -1 }">
				  					<c:if test="${messageinfo.msgState eq 1 || messageinfo.msgState eq 2 }">
				  						<c:if test="${messageinfo.msgNeedReply }">
				  							<a
				  		href="msg?action=reply&msgid=${messageinfo.msgId }&arrow=${arrow }&state=${state }&pageNo=${pageData.pageNo }&pageSize=${pageData.pageSize }"
				  								class="btn btn-info"><span
				  								class="glyphicon glyphicon-log-out"></span> 回复</a>
				  						</c:if>
				  					</c:if>
			  					</c:if>
			  					<a onclick="JavaScript:return confirm('确定删除吗？')" class="btn btn-danger" href="msg?action=delete&msgid=${messageinfo.msgId }&arrow=${arrow }&state=${state }"><span class="glyphicon glyphicon-remove"></span> 删除</a>
			  				</td>
			  			</tr>
			  			
			  		</c:forEach>
			  
			  </c:if>
            </tbody>
            
            <tfoot>
              <tr>
                <td colspan="5" class="text-center"><c:if 
	                  test="${pageData ne null && pageData.items ne null && fn:length(pageData.items) gt 0}">
	                  <c:import url="rollpage.jsp" charEncoding="UTF-8">
	                  	<c:param name="arrow" value="${arrow }" />
	                  	<c:param name="state" value="${state }" />
	                  	<c:param name="totalRecordCount" 
	                  		value="${pageData.totalCount }" />
	                  	<c:param name="totalPageCount" 
	                  		value="${pageData.totalPageCount }" />
	                  	<c:param name="pageNo" value="${pageData.pageNo }" />
	                  	<c:param name="pageStart" value="${pageData.pageStart }" />
	                  	<c:param name="pageEnd" value="${pageData.pageEnd }" />
	                  </c:import>
                  </c:if>
                </td>
              </tr>
            </tfoot>
            			
          </table>
          </form>
        </div>
      </div>
    </div>
  </section>


  <div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
  </div>
  <!-- Modal -->
  <div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">来自xxx的消息</h4>
      </div>
      <div class="modal-body">
        <div>
          <p>
            <span class="text-right col-sm-2">标题</span>
            <span>xxx</span>
          </p>
          <p>
            <span class="text-right col-sm-2">时间</span>
            <span>xxx</span>
          </p>
          <p>
            <span class="text-right col-sm-2">内容</span>
            <span>xxx</span>
          </p>

        </div>
		<hr class="simple" color="#6f5499" />
		<form class="form-horizontal" role="form">
			<div class="form-group">
              <label for="message" class="col-sm-2 control-label">消息</label>
              <div class="col-sm-10">
                <textarea placeholder="消息" class="form-control" rows="3" id="message"></textarea>
              </div>
            </div>
          </form>
      </div>
      <div class="modal-footer">
		<button type="button" class="btn btn-primary" href="#"><span class="glyphicon glyphicon-log-out"></span> 回复 </button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭 </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->  
  </div><!-- /.modal -->
  <c:import url="footer.jsp"/>
    <c:if test="${msg eq 1}">
  	<script type="text/javascript">
  		alert("回复成功!");
  	</script>
  </c:if>
    <c:if test="${msg eq 2}">
  	<script type="text/javascript">
  		alert("删除成功!");
  	</script>
  </c:if>
  <script type="text/javascript">
  	$(document).ready(function(){
  		$("#menu2").addClass("active");
  		
  		$('#msgModal').on('hidden.bs.modal',function(e){
  			$(this).removeData('bs.modal');
  		});
  	});
  </script>
</body>
</html>