// 工具类
var util = {
    addTab: function(title,url) {
        var $content = $("#content");
        if ($content.tabs('exists', title)) {
            $content.tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
            $content.tabs('add', {
                title: title,
                content: content,
                closable: true
            });
        }
    }
};

// main
$(function() {

    // 测边菜单
    $(".menu-link").on("click", function() {
        var url = $(this).attr("href"),
            title = $(this).attr("title");
        util.addTab(title, url);
        return false;
    });

    // 顶导
    $("#navMenu a").on("click",function(){
        var title = $(this).text(),
            rel = $(this).attr("rel");
        $("#navMenu li").removeClass("selected");
        $(this).closest("li").addClass("selected");
        $("#sidebar .toggleCollapse h2").text( title );
        $("#sidebar .accordionContent").hide().filter("[rel="+rel+"]").show();
    });

});