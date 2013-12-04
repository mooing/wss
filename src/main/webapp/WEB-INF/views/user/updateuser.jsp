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
	<form:form commandName="user" action="${pageContext.request.contextPath}/user/update"
		method="post" id="userform">
	<input type="hidden" name="id" value="${user.id }"/>
		<table cellspacing="0" cellpadding="0" class="tblBookRec">
			<tr class="line">
				<td>真实姓名:<input name="realname" value="${user.realname }"/></td>
				<th>用户类型:
					<form:select path="usertype" >
						<form:option value="0" label="--请选择--"></form:option>
						<form:options items="${userTypeList}" itemLabel="name" itemValue="type"/>
					</form:select>
				</th>
				<th>用户角色:
				<form:checkboxes path="roleIds" items="${user.roleList}" itemLabel="rolename" itemValue="id" />
				</th>
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
		$("#userform").submit();
	}
</script>
</html>