Loading={
    //显示等待框
    show:function () {
        $.blockUI({
            message:'<img src="/static/images/block.gif" width="100px" height="100px">',
            css:{
                left:"45%",
                width:"100px",
                height:"100px",
            }
        });
    },
    //隐藏等待框
    hide:function () {
        //本地加载的速度太快，所以这里设置个延迟
        setTimeout(
            function () {
                $.unblockUI();
            },500)
    }
}