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


