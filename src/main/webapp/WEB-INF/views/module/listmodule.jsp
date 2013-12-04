<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.mooing.com/admin/tag/page" prefix="page"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<jsp:include page="../common/ztreehead.jsp" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/module.js"></script>

</head>
  
  <body style="height:3000px;" >
  <form action="" method="post" name="form1">
  <input type="hidden" id="nodeid"/><!-- 点击节点对应各id值保存在这里 -->
  <input type="hidden" id="nodename"/><!-- 点击节点对应各name值保存在这里 -->
  <input type="hidden" id="nodepId"/><!-- 点击节点对应各父类id值保存在这里 -->
  <input type="hidden" id="isParent"/><!-- 是否为父节点 -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">


<!-- 内容部分 -->
  <tr style="padding-top: 20px;">
    <td valign="middle">
		<table width="58%" border="0" style="margin-top:70px" align="center" cellpadding="0" cellspacing="0">
    		<tr>
			<td colspan="2" style="text-align: center;"><h3>jqueryztree整合ssh实例</h3></td>
			<tr>
			<tr><td colspan="2"><hr/></td></tr>
			<tr>
				<td align="center" valign="middle" rowspan="6">
					<ul id="treeDemo" class="ztree"></ul>
				</td>
				<td>
					<input type="button" class="BUTTON_STYLE1" value="添加同级" onclick="return addNode_click()"/><br/><br/><br/>
				</td>
			</tr>
			<tr><td><input type="button" class="BUTTON_STYLE1" value="删除所选" onclick="return deleteNode_click()"/></td></tr>
			<tr><td><input type="button" class="BUTTON_STYLE1" value="修改所选" onclick="return updateNode_click()"/></td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td colspan="2"><hr/></td></tr>
		</table>
	</td>
  </tr>

</table>

<!----------------------------------- 所有弹出层放这里 隐藏起来-------------------------------------------->

<!-- 点击添加同级弹出层 -->
<div id="adddialog" style="display: none;">
	<table border="0" align="center" cellspacing="20" cellpadding="0">
		<tr>
			<td>模块名称：</td>
			<td><input type="text"  id="modulename"/></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;" class="btnbox"><input type="button" class="BUTTON_STYLE1" value="确 定" onclick="addnodeok_click()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="BUTTON_STYLE1" value="取 消" onclick="closeDiv()"/></td>
		</tr>
	</table>
</div>



<!-- 点击修改所选弹出层 -->
<div id="updatedialog" style="display: none;">
	<table border="0" align="center" cellspacing="20" cellpadding="0">
		<tr>
			<td>模块名称：</td>
			<td><input type="text" id="classifyname_update"/></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><input type="button" class="BUTTON_STYLE1" value="确 定" onclick="updateNodeok_click()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="BUTTON_STYLE1" value="取 消" onclick="closeDiv()"/></td>
		</tr>
	</table>
</div>

</form>
<br/><br/><br/><br/>

  </body>
</html>