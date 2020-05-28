package com.course.server.dto;

/*
* 统一返回参数，在调用接口时可以快速得到返回的参数
* 前端也可以针对此返回值做处理
* */
public class ResponseDto<T> {
    private boolean success = true;//标识业务上的调用是成功还是失败
    private String code;//返回码
    private  String message;//返回信息
    private T content;//返回的数据集

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
