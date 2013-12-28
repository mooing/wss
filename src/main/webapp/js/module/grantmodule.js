f.module = {
    getTree:function(data, pId){
        var tree;
        if (pId == 0) {
            tree = '<ul class="tree treeFolder treeCheck expand" oncheck=""">';
        } else {
            tree = '<ul>';
        }
        for (var i in data) {
            if (data[i].pId == pId) {
                tree += "<li  target='mid'  rel='" + data[i].id + "'><a href='javascript:void(0);' tname='moduleIds' tvalue='"+data[i].id+"'>" + data[i].name + "</a>";
                tree += arguments.callee(data, data[i].id);
                tree += "</li>";
            }
        }
        return tree + "</ul>";
    },
    loadTree:function(){
        var that = this;
        $.ajax({
            url:"/module/alltree",
            data:{"guid":new Date().getTime()},
            async:false,
            dataType:"json",
            success:function( data ){
                 $("#left").html(that.getTree(data,0).replaceAll("<ul></ul>",""));
            }
        })
    }
}
