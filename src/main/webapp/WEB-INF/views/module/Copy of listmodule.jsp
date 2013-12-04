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
	</head>
	<body>
	<ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
	</body>
	
	<SCRIPT type="text/javascript" >
		var zTreeNodes="";
		var zTreeObj,
		setting = {
	            data: {  
                simpleData: {  
                    enable: true,//如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId，并且让数据满足父子关系。  
                    idKey: "id",  
                    pIdKey: "pid",  
                    rootPId: 0  //用于修正根节点父节点数据，即 pIdKey 指定的属性值。[setting.data.simpleData.enable = true 时生效]默认值：null
                } ,
                key:{
                	name:"modname"
                }
            },
			callback: {
				onClick: onClick
			}
		};
		
		function onClick(){
		}
	

	$(function(){
			   $.ajax({
				   cache:false,
				   async:false,
				   url: "${pageContext.request.contextPath}/module/all",
				   data: "st="+new Date(),
				   success: function(msg){
					   zTreeNodes=eval("("+msg+")");
				   }
				});
		
		zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);

	});
  </SCRIPT>
	
</html>