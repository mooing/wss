var f = {};
f.layout = {
    getTree:function(data, pId, option,isTree){
        var tree;
        tree = isTree ? '<ul class="tree">' : '<ul>';
        for (var i in data) {
            if (data[i].pid == pId) {
                tree += "<li  target='mid'  rel='" + data[i].id + "'><a target='navTab'  rel='" + data[i].authorityRel + "' href='"+data[i].href+"' "+($.inArray(data[i].id+"",option.ids) >= 0 ? 'checked' : '')+" tname='moduleIds' tvalue='"+data[i].id+"'>" + data[i].modname + "</a>";
                tree += arguments.callee(data, data[i].id,option);
                tree += "</li>";
            }
        }
        return tree + "</ul>";
    },   
    
    loadMain:function(){
   	 $.ajax({
         url:"/module/maintree",
         data:{"guid":new Date().getTime()},
         async:false,
         dataType:"json",
         success:function( data ){
        	 var mainMenu,
                $menu = $("");
         	  mainMenu=' <ul>';
         	  for(var i=0;i<data.length;i++){
         		 mainMenu+='<li>';
         		 mainMenu+='<a href="javascript:void(0)" rel="'+data[i].authorityRel+'">';
         		 mainMenu+='<span>'+data[i].modname+'</span>';
         		 mainMenu+='</a>';
         		 mainMenu+='</li>';
                $("#menuContainer").append('<div class="accordionContent" rel="'+data[i].authorityRel+'" pid="'+data[i].id+'"></div>');
              }
              mainMenu+=' </ul>';
              $("#navMenu").html(mainMenu);
              // 顶导
            $("#navMenu a").click(function(){
                var title = $(this).text(),
                    rel = $(this).attr("rel");
                $("#navMenu li").removeClass("selected");
                $(this).closest("li").addClass("selected");
                $("#sidebar .toggleCollapse h2").text( title );
                $("#sidebar .accordionContent").hide().filter("[rel="+rel+"]").show();
            });
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
                $("#menuContainer .accordionContent").each(function(){
                    var pid = $(this).attr("pid");
                    $(this).html( that.getTree(data,pid,option,true).replaceAll('<ul></ul>',"") );
                });
            }
        });
    },
    option:{
        collapse:"collapse",
        treeCheck:"",
        oncheck:$.noop,
        container:"",
        ids:[]
    }

}