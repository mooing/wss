f.region = {
    getTree:function(data, pId, option,flag){
        var tree,
            onclick = "";
        if (flag==1) {
            tree = '<ul class="tree treeFolder '+option.collapse+' ">';
        } else {
            tree = '<ul>';
        }
        if(flag==1){
        	data=data.unitList;
        }
        
        console.log(data);
        for (var i in data) {
            if (data[i].pid == pId) {
                // 带回的id和值
                onclick = option.lookup ? '$.bringBack({hospitalId:"'+data[i].id+'", hospitalName:"'+data[i].name+'"})' : "";
                tree += "<li  target='rid'  rel='" + data[i].code + "'><a href='javascript:void(0);' "+($.inArray(data[i].id+"",option.ids) >= 0 ? 'checked' : '')+"' "+(!!onclick ? "onclick='"+onclick+"'" : "")+">" + data[i].name + "</a>";
                tree += arguments.callee(data, data[i].id,option,2);
                tree += "</li>";
            }
        }
        return tree + "</ul>";
    },
    loadTree:function( option ){
        var that = this;
        option = $.extend(that.option,option);
        if( !!$(option.container).attr("ids") ){
            option.ids = $(option.container).attr("ids").split(",");
        }
        $.ajax({
            url:"/unit/curunit",
            data:{"guid":new Date().getTime()},
            async:false,
            dataType:"json",
            success:function( data ){
                 $(option.container).html(that.getTree(data,data.pUnitId,option,1).replaceAll("<ul></ul>",""));
            }
        });
    },
    option:{
        collapse:"collapse",
        treeCheck:"",
        oncheck:$.noop,
        container:"#unit-tree-lookup",
        ids:[]
    }
}