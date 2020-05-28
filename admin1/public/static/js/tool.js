Tools={
    /*
    * 空校验，null或者""都返回true
    * */
    isEmpty:function (obj) {
        if(typeof obj=='string') {
            return !obj || obj.replace(/\s+/g,"")=="" //意思就是将原字符串中的所有空白字符替换成"",\s代表空格，/g代表全局
        }else{

            return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
        }
    },
    /*
    * 非空校验，
    * */
    isNotEmpty:function (obj) {
        return !this.isEmpty();
    }
}