package com.course.business.controller;

import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {
    /*
    * @ResponseBody即说明以json格式返回的
    * @ExceptionHandler(value = ValidatorException.class)的意思是只要抛出的异常
    * 是ValidatorException，那么就会在这里被拦截并增强
    * */
    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e){
        ResponseDto responseDto= new ResponseDto();
        responseDto.setSuccess(false);//即返回失败信息（因为这时并没有添加数据）
        System.out.println(e.getMessage());//获取自定义异常中的错误（这个错误是在server中的service中返回的）
        responseDto.setMessage("请求参数异常");//统一在页面显示“请求参数异常”，防止别人直接调用接口显示相应信息给他
        return responseDto;
    }
}
