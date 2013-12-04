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
	<form:form commandName="role" action="${pageContext.request.contextPath}/role/updaterole"
		method="post" id="roleform">
	<input type="hidden" name="id" value="${role.id }"/>
		<table cellspacing="0" cellpadding="0" class="tblBookRec">
			<tr class="line">
				<td>角色名称:<input name="rolename" value="${role.rolename }"/></td>
			</tr>
			<a onclick="submit()" href="javascript:void(0);" id="savenow">保存</a><a>取消</a>
		</table>
	</form:form>
</body>
<script>
	if('${error}'!=''){
		$("#savenow").hide();
		alert("数据异常，您选择的用户不存在或无效！");
	}
	function submit(){
		$("#roleform").submit();
	}
</script>
</html>