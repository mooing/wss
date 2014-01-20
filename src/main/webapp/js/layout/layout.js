f.layout = {
    getTree:function(data, pId, option){
        var tree;
            tree = '<div class="accordionContent"  ><ul class="tree">';
        for (var i in data) {
            if (data[i].pid == pId) {
                tree += "<li  target='mid'  rel='" + data[i].id + "'><a target='navTab'  rel='" + data[i].authorityRel + "' href='"+data[i].href+"' "+($.inArray(data[i].id+"",option.ids) >= 0 ? 'checked' : '')+" tname='moduleIds' tvalue='"+data[i].id+"'>" + data[i].modname + "</a>";
                tree += arguments.callee(data, data[i].id,option);
                tree += "</li>";
            }
        }
        return tree + "</ul></div>";
    },   
    
    loadMain:function(){
   	 $.ajax({
         url:"/module/maintree",
         data:{"guid":new Date().getTime()},
         async:false,
         dataType:"json",
         success:function( data ){
        	 var mainMenu;
         	  mainMenu=' <ul>';
         	  for(var i=0;i<data.length;i++){
         		 mainMenu+='<li>';
         		 mainMenu+='<a href="javascript:void(0)" rel="'+data[i].authorityRel+'">';
         		 mainMenu+='<span>'+data[i].modname+'</span>';
         		 mainMenu+='</a>';
         		 mainMenu+='</li>';
         	  }
              mainMenu+=' </ul>';
              $("#navMenu").html(mainMenu);
         }
     });
    },
    
    loadTree:function( option ){
    	//加载主菜单
    	this.loadMain();
        var that = this;
        option = $.extend(that.option,option);
        if( !!$(option.container).attr("ids") ){
            option.ids = $(option.container).attr("ids").split(",");
        }
        $.ajax({
            url:"/module/layouttree",
            data:{"guid":new Date().getTime()},
            async:false,
            dataType:"json",
            success:function( data ){
                 $(option.container).html(that.getTree(data,2,option).replaceAll("<ul></ul>",""));
            }
        });
    },
    option:{
        collapse:"collapse",
        treeCheck:"",
        oncheck:$.noop,
        container:"#layout-module-tree",
        ids:[]
    }

}