package com.course.server.utils;

import com.course.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

/*
* 后台校验验证， 防止有人直接访问后台调用接口
* */
public class ValidatorUtil {
    /*
    * 空校验
    * */
    public static void require(String str,String fileName){
        if (StringUtils.isEmpty(str)){
            throw new ValidatorException(fileName+"不能为空");
        }
    }
    /*
    * 长度校验
    * */
    public static void length(String str,String fileName,int min,int max){
        int length = 0;
        if (!StringUtils.isEmpty(str)){
            length = str.length();
        }
        if(length < min||length > max){
            throw new ValidatorException(fileName+"的长度必须是"+min+"~"+max+"位");
        }
    }
}
