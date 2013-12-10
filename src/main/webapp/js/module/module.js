// module
function toTreeObj( arr ){
	var res = [];
	// parent
	$(arr).filter(function(){
		if(+this.pId === 0) return true;
	}).each(function(i,v){
		var obj = {};
		obj.id = v.id;
		obj.text = v.name;
		obj.state = "closed";
		obj.attributes = {
			parentName:"",
			pId : 0,
			isleaf:true
		};
		obj.children = [];
		res.push( obj );
	});
	// children
	$(arr).filter(function(){
		if(+this.pId !== 0) return true;
	}).each(function(i,v){
		var obj = {},
			pid;
		obj.id = v.id;
		obj.text = v.name;
		obj.children = [];
		pid = +v.pId;
		obj.attributes = {
			parentName:"",
			pId:pid,
			isleaf:true
		};
		$(res).each(function(){
			if( +this.id === pid){
				obj.attributes.parentName = this.text;
				this.attributes.isleaf = false;
				this.children.push( obj );
			}
		});
	});
	return res;
}
function putDataToForm( data, url ){
	var $form = $("#module-form");
	$form.find("input[name='pId']").val(data.pId);
	$form.find("input[name='name']").val(data.name);
	$form.find("input[name='id']").val(data.id);
	$form.find(".pname").text(data.pname);
	$form.attr("action",url);
}

// 添加根节点
$(".module-addroot").on("click",function(){
	var data = {};
	data.pId = "";
	data.pname = "";
	data.name = "";
	data.id = "";
	putDataToForm(data,'/module/addnode'+'?guid='+new Date().getTime());
});

// 删除
$(".module-del").on("click",function(){
	if(!$('#module-tree').tree('getSelected')) return false;
	var node = $('#module-tree').tree('getSelected'),
		isleaf = node.attributes.isleaf,
		data = {};
	if(!isleaf){
		alert("非叶子节点无法删除");
	}else{
	    data.pId = node.attributes.pId;
	    data.pname = node.attributes.parentName;
	    data.name = node.text;
	    data.id = node.id;
		putDataToForm(data,'/module/delnode/'+node.id+'/?guid='+new Date().getTime() );
		$('#module-form').form('submit', {
			onSubmit: function(){
				var isValid = $(this).form('validate');
				return isValid;	
			},
			success: function(data) {
				if(data){
					alert("删除成功");
					loadTree();
				}
			}
		});
	}
	return false;
});

// 添加
$(".module-add").on("click",function(){
	if(!$('#module-tree').tree('getSelected')) return false;
	var node = $('#module-tree').tree('getSelected'),
		isleaf = node.attributes.isleaf,
		data = {};
	data.pId = node.attributes.pId;
	data.pname = node.attributes.parentName;
	data.name = "";
	data.id = "";
	putDataToForm(data,'/module/addnode?guid='+new Date().getTime() );
	return false;
});

// 保存
$(".module-save").on("click",function(){
	$('#module-form').form('submit', {
		onSubmit: function(){
			var isValid = $(this).form('validate');
			return isValid;	
		},
		success: function(data) {
			if(data){
				alert("保存成功");
				loadTree();
			}
		}
	});
});

// 初始化
function loadTree(){
	$.getJSON("/module/alltree?guid="+new Date().getTime(),function(data){
		data = toTreeObj( data );
		$('#module-tree').tree({
		    "data":data,
		    onClick: function(node){
		    	var data = {};
		    	data.pId = node.attributes.pId;
		    	data.pname = node.attributes.parentName;
		    	data.name = node.text;
		    	data.id = node.id;
		    	putDataToForm(data,'/module/updatenode'+'?guid='+new Date().getTime());
			}
		});
	});
}

loadTree();
