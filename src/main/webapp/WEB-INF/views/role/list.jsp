<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.mooing.com/admin/tag/page" prefix="page"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	</head>
	<body>
	<table cellspacing="0" cellpadding="0" class="tblBookRec">
					<tr class="line">
						<th>角色名称</th>
					</tr>
					<c:forEach items="${page.data }" var="role">
						<tr class="line" >
							<td class="c3"><c:out value="${role.rolename }"/></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(page.data) == 0}">
				        <tr>
				            <td colspan="8">没有找到记录</td>
				        </tr>
				    </c:if>
				</table>
				<div class="comm_page">
					<page:pagination controller="${pageContext.request.contextPath}/role" action="list" page="${page}" params="${param}" />
				</div>
	</body>
</html>