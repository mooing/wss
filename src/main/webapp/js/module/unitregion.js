f.region = {
    getTree:function(data, pId, option){
        var tree,
            onclick = "";
        if (pId == 0) {
            tree = '<ul class="tree treeFolder '+option.collapse+' ">';
        } else {
            tree = '<ul>';
        }
        for (var i in data) {
            if (data[i].pId == pId) {
                // 带回的id和值
                onclick = option.lookup ? '$.bringBack({regionCode:"'+data[i].id+'", regionName:"'+data[i].name+'"})' : "";
                tree += "<li  target='rid'  rel='" + data[i].id + "'><a href='javascript:void(0);' "+($.inArray(data[i].id+"",option.ids) >= 0 ? 'checked' : '')+" tname='moduleIds' tvalue='"+data[i].id+"' "+(!!onclick ? "onclick='"+onclick+"'" : "")+">" + data[i].name + "</a>";
                tree += arguments.callee(data, data[i].id,option);
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
            url:"/region/curregion",
            data:{"guid":new Date().getTime()},
            async:false,
            dataType:"json",
            success:function( data ){
                 $(option.container).html(that.getTree(data,0,option).replaceAll("<ul></ul>",""));
            }
        });
    },
    option:{
        collapse:"collapse",
        treeCheck:"",
        oncheck:$.noop,
        container:"#region-tree-lookup",
        ids:[]
    }
}