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
	<ul id="treeDemo" class="ztree"></ul>
	 <a id="addNode" href="#" onclick="return false;">追加</a> 
	</body>
	
	<SCRIPT type="text/javascript" >
	<!--
	var setting = {
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
		view: {
			selectedMulti: false
		},
		async: {
			enable: true,
			url:"${pageContext.request.contextPath}/module/bypid",
			autoParam:["pid"],
			dataFilter: filter
		},
		callback: {
			beforeClick: beforeClick,
			beforeAsync: beforeAsync,
			onAsyncError: onAsyncError,
			onAsyncSuccess: onAsyncSuccess
		}
	};
	

	function filter(treeId, parentNode, childNodes) {
	}
	function beforeClick(treeId, treeNode) {
		if (!treeNode.isParent) {
			alert("请选择父节点");
			return false;
		} else {
			return true;
		}
	}
	var log, className = "dark";
	function beforeAsync(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeAsync ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
		return true;
	}
	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		showLog("[ "+getTime()+" onAsyncError ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
	}
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		showLog("[ "+getTime()+" onAsyncSuccess ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
	}
	
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}

	function refreshNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		type = e.data.type,
		silent = e.data.silent,
		nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			//alert("请先选择一个父节点");
		}
		for (var i=0, l=nodes.length; i<l; i++) {
			zTree.reAsyncChildNodes(nodes[i], type, silent);
			if (!silent) zTree.selectNode(nodes[i]);
		}
	}

	$(document).ready(function(){
		var zTreeNodes="";
		//$.fn.zTree.init($("#treeDemo"), setting);
		 $.ajax({
				   cache:false,
				   async:false,
				   url: "${pageContext.request.contextPath}/module/bypid",
				   data: "pid=0&st="+new Date(),
				   success: function(msg){
					   zTreeNodes=eval("("+msg+")");
				   }
				});
		
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zTreeNodes);
		
		$("#refreshNode").bind("click", {type:"refresh", silent:false}, refreshNode);
		$("#refreshNodeSilent").bind("click", {type:"refresh", silent:true}, refreshNode);
		$("#addNode").bind("click", {type:"add", silent:false}, refreshNode);
		$("#addNodeSilent").bind("click", {type:"add", silent:true}, refreshNode);
	});
	//-->
  </SCRIPT>
	
</html>