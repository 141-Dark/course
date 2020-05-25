const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    onOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
})


//声明类
toast={
    //成功时执行
    success:function (message) {
        Toast.fire({
            icon: 'success',
            title: message
        })
    },
    //失败时提示
    error:function(message){
        Toast.fire({
            icon: 'error',
            title: message
        })
    },

    //警告
    warning:function(message){
        Toast.fire({
            icon: 'warning',
            title: message
        })
    }
}