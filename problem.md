开发说明
===========================
开发过程中问题互相补充，理解或解决的问题删除掉

##ajax
####成功返回：

	{
		"statusCode": "200",
		"message": "操作成功", //正确提示
		"navTabId": "systemUser",
		"rel": "",
		"callbackType": "", 
		"forwardUrl": "",
		"confirmMsg": ""
	}

如需要刷新列表，navTabId为必须返回，目前已有：
/user/list - systemUser
/role/list - systemRole
/module/totree -systemModule
需要刷新的页面都对应一个navTabId, 例如/user/list列表页增删改返回navTabId:"systemUser",则该列表页会刷新。
navTabId也需要写入layout.html模板的菜单链接的rel属性中，如 

	<a href="/user/list" target="navTab" rel="systemUser">用户管理</a> 

表示用户管理的navTabId为systemUser

####失败返回:

	{
		"statusCode": "300",
		"message": "操作失败", //错误信息
		"navTabId": "",
		"rel": "",
		"callbackType": "",
		"forwardUrl": "",
		"confirmMsg": ""
	}

未用到的项先留空，遇到特殊处理的会用到


