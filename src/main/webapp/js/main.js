 function addTab(title, url){
    if ($('#content').tabs('exists', title)){
        $('#content').tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        $('#content').tabs('add',{
            title:title,
            content:content,
            closable:true
        });
    }
    return false;
}