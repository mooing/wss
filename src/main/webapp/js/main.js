// 工具类
var util = {
};

// main
$(function() {

    // 顶导
    $("#navMenu a").on("click",function(){
        var title = $(this).text(),
            rel = $(this).attr("rel");
        $("#navMenu li").removeClass("selected");
        $(this).closest("li").addClass("selected");
        $("#sidebar .toggleCollapse h2").text( title );
        $("#sidebar .accordionContent").hide().filter("[rel="+rel+"]").show();
    });

    // chechbox
    $("body").on("click",".toggleDisabled",function(){
        var isCheck = $(this).is(":checked"),
            $target = $(this).siblings("select,input");
        if(!!isCheck){
            $target.prop("disabled",false);
        }else{
            $target.prop("disabled",true);
        }
    });

    // test
    setTimeout(function(){
        $(".accordionContent[rel=cert] a[href]").eq(0).click();
    },500)

});