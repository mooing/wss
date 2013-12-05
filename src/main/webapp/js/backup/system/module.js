/**-----------初始化ZTree获取数据库节点数据-----------**/
		var setting = {
			async: {
				enable: true, //开启异步加载方式
				url: "../module/alltree?guid="+new Date().getTime(), //异步加载时请求路径
				autoParam: ["id", "name", "pId"] //异步加载时加载哪些属性
			},	
			view: {
				dblClickExpand: false, //需要设置是否双击切换展开状态的节点 JSON 数据对象
				showLine: false, //是否显示连接线
				treeNodeKey : "id", //当前节点id属性 
				treeNodeParentKey : "pId" //当前节点的父节点id属性
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {//回调函数
				onClick: onClick
			}
		};
		
		var zNodes;//节点集合
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");//当前的ztree对象
		
		function onClick(e,treeId, treeNode) {//点击节点事件
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(treeNode);
			$("#nodeid").val(treeNode.id); //点击节点时将id值装入
			$("#nodename").val(treeNode.name); //点击节点时将name值装入
			$("#nodepId").val(treeNode.pId); //点击节点时将pId值装入
			$("#isParent").val(treeNode.isParent); //是否为父节点
			//alert(treeNode.id+"|"+treeNode.name+"|"+treeNode.pId);
		}
		
		/**------加载事件中读取ztree与数据库进行异步交互----**/
		$(document).ready(function(){
			$.ajax({ 
				async : false, 
				cache:false, 
				type: 'POST', 
				url: '../module/alltree?guid='+new Date().getTime(),//请求的action路径 
				contentType:"application/json;charset=utf-8",
				error: function () {//请求失败处理函数 
					alert('请求失败'); 
				}, 
				success:function(data){ //请求成功后处理函数。 
					//alert("获取数据成功"); 
					zNodes = eval("("+data+")"); //把后台封装好的简单Json格式赋给treeNodes 
				} 
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);//将节点集合zNodes装入
		});

		
		/**--------------------------添加同级---------------------------**/
		
		/**-----------点击添加下级按钮触发事件-----------**/
		function addNode_click(){
			
			if($('#nodeid').val()==="" || $('#nodeid').val()===null){alert("请选中一个节点!");return false;}
			else{
				//弹出层调用
					tipsWindown("添加同级","id:adddialog","400","200","true","","true","id");
			}
		}
		//确定添加
		function addnodeok_click(){
			$.ajax({ 
				async : false, 
				cache:false, 
				type: 'GET', 
				url: "../module/addnode?pId="+$("#nodeid").val()+"&name="+encodeURI($('#windown-box').find('input[id="modulename"]').val())+"&guid="+new Date().getTime(),//请求的action路径 
				contentType:"application/json;charset=utf-8",
				error: function () {//请求失败处理函数 
					alert('请求失败'); 
				}, 
				success:function(data){ //请求成功后处理函数。 
					alert("添加节点成功"); 
					zTree.reAsyncChildNodes(null,"refresh");//重新异步加载ztree
					closeWindown();
				} 
			});
		}
		
		/**-----------点击删除按钮触发事件-----------**/
		function deleteNode_click(){
			var nodeid=$('#nodeid').val();
			var parent=$('#isParent').val();
			if(parent==true){
				alert("请选择叶子节点删除！");
				return ;
			}
			if(confirm('确定删除节点吗？')){
				$.ajax({ 
					async : false, 
					cache:false, 
					type: 'POST', 
					url: "../module/delnode/"+nodeid+"?guid="+new Date().getTime(),//请求的action路径 
					contentType:"application/json;charset=utf-8",
					error: function () {//请求失败处理函数 
						alert('请求失败'); 
					}, 
					success:function(data){ //请求成功后处理函数。 
						alert("删除节点成功"); 
						zTree.reAsyncChildNodes(null,"refresh");//重新异步加载ztree
					} 
				});
			}
			else{
				return false;
			}
		}
		
		
		
		/**--------------------------修改所选---------------------------**/
		/**-----------点击修改所选按钮触发事件-----------**/
		function updateNode_click(){
			if($('#nodeid').val()==="" || $('#nodeid').val()===null){alert("请选中一个节点!");return false;}
			else{
				var nodename = $("#nodename").val();
				$("#classifyname_update").val(nodename);
				//弹出层调用
				tipsWindown("修改所选","id:updatedialog","400","200","true","","true","id");
			}
		}
		//确定修改
		function updateNodeok_click(){
			if(confirm('确定修改节点吗？')){
				$.ajax({ 
					async : false, 
					cache:false, 
					type: 'POST', 
					url: "../module/updatenode?id="+$("#nodeid").val()+"&name="+encodeURI($('#windown-box').find('input[id="classifyname_update"]').val())+"&guid="+new Date().getTime(),//请求的action路径 
					contentType:"application/json;charset=utf-8",
					error: function () {//请求失败处理函数 
						alert('请求失败'); 
					}, 
					success:function(data){ //请求成功后处理函数。 
						alert("修改节点成功"); 
						zTree.reAsyncChildNodes(null,"refresh");//重新异步加载ztree
						closeWindown();
					} 
				});
			}
			else{
				return false;
			}
		}
		
		/**----关闭弹出层----**/
		function closeDiv() {
			closeWindown();
		}
		