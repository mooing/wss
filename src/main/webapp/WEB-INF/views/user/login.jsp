<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title></title>
		<jsp:include page="../common/head.jsp" />
		<script language="JavaScript" type="text/javascript">
		//获取验证码
		$(function(){
			getValidCode();
		});
		
		   function getValidCode(){
				$("#imgVcode").attr("src", "${pageContext.request.contextPath}/system/validcode?st="+new Date());
		   }
		   
		   function checkValidCode(){
			   var validCode=$("#validCodeTxt").val();
			   $.ajax({
				   type: 'POST',
				   async:false,
				   cache:false,
				   url: "${pageContext.request.contextPath}/system/checkvalidcode",
				   data: "validCode="+validCode+"&st="+new Date(),
				   dataType: "json",
				   success: function(msg){
					   if(!msg){
						   alert("验证码错误，请重新填写！");
					   }
				   },
				   error: function(msg){
					   alert("连接服务器超时，请重试！");
				   }
				});
		   }
		   
		   /*
		   $(document).ready(function(){
			   $("#loginform").validate({
					rules: {
						username: {
							required: true,
							maxlength: 32
						},
						password: {
							required: true
						},
						user_text: {
							required: true,
							rangelength : [4, 4]
							
						}
					},
					messages: {
						username: {
							required: "用户名必须填写",
							maxlength: "用户名最大长度为 {0} 位"
						},
						password: {
							required:  "密码必须填写"
							
						},
						user_text: {
							required: "验证码必须填写",
							rangelength: "请输入{0}位的验证码"
						}
						
					},
					errorClass : "errorValidate",
					errorPlacement : function(error, element) {
						error.appendTo(element.parent());
					}
				}); 
		   });
		   
		   */
		</script>		
	</head>
	<body>
		<div class="qn_header autowidth">
			<div class="inner">
				<h1></h1>
			</div>
		</div>
		<div class="qn_page">
			<div class="qn_usercenter">
				<div class="win winLogin">
					<div class="hd"></div>
					<div class="ct">
					<c:if test="${errorMsg != null && fn:length(errorMsg) > 0 }">
						<div class="errorExplanation">
							<p>错误信息</p>
							<ul>
								<li>${errorMsg }</li>
							</ul>
						</div>
					</c:if>
						<form action="${pageContext.request.contextPath}/system/login" name="loginform" id="loginform" class="form" method="post">
							<p>
								<label>用户名</label>
								<input class="textbox" id="username" name="username" type="text" value="chiperfect"/>
							</p>
							<p>
								<label>密码</label>
								<input class="textbox" id="password" name="password" type="password" value="123456"/>
							</p>
							<p>
								<label>验证码</label>
								<input class="textbox_new textbox"  id="validCodeTxt" name="validCode" onchange="checkValidCode()" type="text" maxlength="10" value=""/>
								<img id="imgVcode"  src=""/>
								<a href="javascript:void(0)" onclick="getValidCode()">换一换</a>
							<p>		
							</p>
							<p class="r">
								<input class="button" name="commit" type="submit" value="登录" />
								<input type="reset" value="重置" class="button"/>
							</p>
							<div class="clr"></div>
						</form>
					</div>
					<div class="ft">
						<div class="b2"></div>	<div class="b1"></div>
					</div>
				</div>			
			</div>						
		</div>
		<div class="qn_footer">
			<div class="inner">
				<div class="cr">
				</div>
			</div>
		</div>
	</body>
</html>