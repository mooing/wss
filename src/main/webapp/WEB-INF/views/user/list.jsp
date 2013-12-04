<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.mooing.com/admin/tag/page" prefix="page"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<jsp:include page="../common/head.jsp" />
	</head>
	<body>
	<table cellspacing="0" cellpadding="0" class="tblBookRec">
					<tr class="line">
						<th>用户名</th>
						<th>真实姓名</th>
						<th>创建时间</th>
						<th>状态</th>
					</tr>
					<c:forEach items="${page.data }" var="user">
						<tr class="line" >
							<td class="c3"><c:out value="${user.username }"/></td>
							<td class="c2"><c:out value="${user.realname }"/></td>
							<td class="c1"><fmt:formatDate value="${user.regtime }" pattern="yyyy-MM-dd HH:ss:mm" /></td>
							<td class="c2"><c:out value="${user.status }"/></td>
							<td class="c2"><a href="javascript:void(0);" onclick="grantRole(${user.id})" >授予角色</a></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(page.data) == 0}">
				        <tr>
				            <td colspan="8">没有找到记录</td>
				        </tr>
				    </c:if>
				</table>
				<div class="comm_page">
					<page:pagination controller="${pageContext.request.contextPath}/user" action="list" page="${page}" params="${param}" />
				</div>
				
				<div style="display: none;" id="userRoleDiv">
					<form action="${pageContext.request.contextPath}/user/grantrole"
					method="post" id="userRoleform">
					 <input type='checkbox' name='roleIds' />1
					 <input type='checkbox' name='roleIds' />1
					 <input type='checkbox' name='roleIds' />1
					</form>
				</div>
	</body>
	
<script>
	function grantRole(userid){
		$.ajax({
			   type: 'POST',
			  // async:false,
			   cache:false,
			   url: "${pageContext.request.contextPath}/user/finduserrole",
			   data: "userid="+userid+"&st="+new Date(),
			   success: function(msg){
				   var roleIds='${user.roleIds}';
				   for(var i=0;i<roleIds.length;i++){
					   alert(roleIds);
				   }
			   },
			   error: function(msg){
				   alert("连接服务器超时，请重试！");
			   }
			});
		$("#userRoleDiv").show();
	}
</script>
</html>