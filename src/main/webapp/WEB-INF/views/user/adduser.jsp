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
	<form:form commandName="user" action="${pageContext.request.contextPath}/user/add"
		method="post" id="userform">

		<table cellspacing="0" cellpadding="0" class="tblBookRec">
			<tr class="line">
				<td>用户名:<input name="username" /></td>
				<td>真实姓名:<input name="realname" /></td>
				<th>用户类型:
					<form:select path="usertype" >
								<form:option value="0" label="--请选择--"></form:option>
								<form:options items="${userTypeList}" itemLabel="name" itemValue="type"/>
					</form:select>
				</th>
				<th>角色类型:
				<form:checkboxes path="roleIds" items="${roleList}" itemLabel="rolename" itemValue="id" />
				</th>
				<th>初始密码:<input name="password" value="123456" /></th>
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
		$("#userform").submit();
	}
</script>
</html>