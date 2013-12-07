// user
$("#user-form").submit(function() {
    var isValid = $(this).form('validate');
    if (!isValid) {
        $.messager.progress('close');
    }
    return isValid;
});