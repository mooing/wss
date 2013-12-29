f.region = {
    getTree:function(data, pId, option){
        var tree;
        if (pId == 0) {
            tree = '<ul class="tree treeFolder '+option.treeCheck+' '+option.collapse+' ">';
        } else {
            tree = '<ul>';
        }
        for (var i in data) {
            if (data[i].pid == pId) {
                tree += "<li  target='uid'  rel='" + data[i].id + "'><a href='javascript:void(0);' "+($.inArray(data[i].id+"",option.ids) >= 0 ? 'checked' : '')+" tname='uIds' tvalue='"+data[i].id+"'>" + data[i].name + "</a>";
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
            url:"/unit/alltree",
            data:{"guid":new Date().getTime()},
            async:false,
            dataType:"json",
            success:function( data ){
            	alert(data);
                 $(option.container).html(that.getTree(data,0,option).replaceAll("<ul></ul>",""));
            }
        });
    },
    option:{
        collapse:"collapse",
        treeCheck:"",
        oncheck:$.noop,
        container:"#unit-tree",
        ids:[]
    }
}