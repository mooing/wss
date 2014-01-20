// 工具类
var util = {
};

// main
$(function() {

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
    // setTimeout(function(){
    //     $(".accordionContent[rel=cert] a[href]").eq(0).click();
    // },500)

});