<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pagination">
	<ul>
		<li class="previous"><a
			href="javascript:page_nav(${param.pageStart },${param.arrow},${param.state });" 
			class="fui-arrow-left"></a></li>
		<c:forEach begin="${param.pageStart }" end="${param.pageEnd }" var="i">
			<c:if test="${param.pageNo eq i}">
				<li class="active"><a>${i }</a></li>
			</c:if>
			<c:if test="${param.pageNo ne i}">
				<li><a
					href="javascript:page_nav(${i },${param.arrow},${param.state });">${i }</a></li>
			</c:if>
		</c:forEach>
		<li class="next"><a
			href="javascript:page_nav(${param.pageEnd },${param.arrow},${param.state });" 
			class="fui-arrow-right"></a></li>
	</ul>
</div>
<!-- /pagination -->

<script type="text/javascript">
	function page_nav(num,arrow,state){
		var thisForm = document.forms["queryForm"];
		thisForm.pageNo.value = num;
		thisForm.arrow.value = arrow;
		thisForm.state.value = state;
		thisForm.submit();
	}
</script>