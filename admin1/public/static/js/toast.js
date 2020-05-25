//声明类
Toast={
    //成功时执行
    success:function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: message,
            showConfirmButton: false,
            timer:3000
        })
    },
    //失败时提示
    error:function(message){
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 3000
        })
    },

    //警告
    warning:function(message){
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 3000
        })
    }
}