Conform={
    show:function (massage,callback) {
        //加入弹出框
        Swal.fire({
            title: '确定?',
            text:massage,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确认!'
        }).then((result) => {
            if (result.value) {
                if (callback){
                    callback()
                }
            }
        })

    }
}