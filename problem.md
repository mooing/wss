开发说明
===========================
开发过程中问题互相补充，理解或解决的问题删除掉

## 树列表
目前只有模块列表，不用ajax方式，数据直接输出到listmodule.html里，按照现在里面假数据的格式套用

##分页
分页提出公共模板文件common/pager.html, 需要有分页的html中parse该文件，并提供统一的分页参数写入该文件如下部分

    <span>条，共23条</span>
    ...
    <div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="2"></div>

##velocity
目前velocity根目录为webapp,建议更改为web-inf, 希望添加velocity日期相关处理tools

##接口
删除的接口: url?userid=x
批量删除的接口: url?ids=1,2,3
ajax接口返回格式

    {
    	"statusCode":"200",
    	"message":"操作成功",
    	"navTabId":"",
    	"rel":"",
    	"callbackType":"",
    	"forwardUrl":"",
    	"confirmMsg":""
    }

##出生证模板
- birth/handle 证件办理
	- signedList 已签发列表
	- staySignList 待签发列表
	- special 特殊签发
- birth/query 查询统计
	- useStatistic 证件使用综合统计
	- track 单证追踪
	- dynamicsStock 动态库存
	- handleStatistic 办证查询统计
	- disuseRateStatistic 废证率统计分析
	- statisticReport 统计报表
- birth/manage 证件管理
	- entry 证件入库
	- request 证件申请
	- recall 证件召回
	- disuseEntry 废证入库

总计13个页面，麻烦把controller先写一下，对应到相对模板，目前模板都是空页面



