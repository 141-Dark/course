let formatSecond = (value)=>{
    value = value||0
    let second = parseInt(value,10)//秒
    let minute = 0//分
    let hour = 0//时
    if(second>60){
        //当秒大于60时才需要做转换
        minute = Math.floor(second/60);
        second = Math.floor(second%60);
        if (minute>60){
            //当分大于60时，做转换
            hour = Math.floor(minute/60);
            minute = Math.floor(minute%60);
        }
    }
    //完成拼接
    let result = "" + pre(second,2)+"";
    if(minute>0){
        //拼上分钟
        result = ""+pre(minute,2)+":"+result
    }
    if(hour>0){
        result = ""+pre(hour,2)+":"+result
    }
    return result
};

//格式化长度，前面加0
function pre(num,length) {
    return (Array(length).join('0')+num).slice(-length)
}
export default {
    formatSecond
}