1. 替换图片,登录输入框稍调大；登录后，加退出系统连接
2. 省市  模块，下拉框树；单位管理 --新增--选择地区时


开发说明
===========================
开发过程中问题互相补充，理解或解决的问题删除掉

##js模块划分
module目录下对应各业务的js，入角色管理的role.js 模块管理的 module.js
module下js结构:

	f.role = {
		method : function(){
			...
		},
		...
	}

f为已声明全局变量，各文件基于f下写各自的业务处理，如f.module,f.role,f.user等
module文件夹下模块js只写方法，不写调用，调用在具体模板里写

##tree
tree的外层包含一个div，拥有唯一id,建议各tree的列表页命名规则 xxx-tree, 如unit-tree
各弹出层tree命名为 xxx-tree-xxx, 如果 module-tree-grant

	<div class="accordionContent" id="module-tree">
	</div>

如果需要选中状态，传入

	<div class="accordionContent" id="module-tree" ids="1,2,3">
	</div>

即id为1,2,3的会处于选中状态

调用时参数：

	 f.module.loadTree({container:"#module-tree-grant",collapse:"expand",treeCheck:"treeCheck"});

container:要生成tree的外层div的id,默认值写在js中,默认一般为列表页树的id
collapse: 默认collapse, expand为全部展开
treeCheck: 默认空，treeCheck为带有复选框


