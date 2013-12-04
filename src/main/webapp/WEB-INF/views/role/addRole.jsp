<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp" />
</head>
<body>
	<form:form commandName="role" action="${pageContext.request.contextPath}/role/addrole"
		method="post" id="roleform">

		<table cellspacing="0" cellpadding="0" class="tblBookRec">
			<tr class="line">
				<td>角色名:<input name="rolename" /></td>
			</tr>
			<a onclick="submit()" href="javascript:void(0);" id="save">保存</a><a>取消</a>
		</table>
	</form:form>
</body>
<script>
$(function(){
	if('${error}'=='1'){
		$("#save").hide();
		alert("当前登录用户没有新增用户权限,请用管理员身份登录！");
	}
});
	function submit(){
		$("#roleform").submit();
	}
</script>
</html>