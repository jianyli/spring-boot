package com.li.support.dto;

import java.util.Date;

public class  RestResultDto<T> {
    private static final Integer RESULT_SUCC = 0;
    private static final Integer RESULT_FAIL = 1;

    private Integer result;
    private String msg;
    private T data;
    private String exception;
    private Date timestamp;

    public RestResultDto() {
        this.result = RESULT_SUCC;
        this.msg = "";
        this.exception = "";
        this.timestamp = new Date();
    }

    public RestResultDto(Integer result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.timestamp = new Date();
    }

    public RestResultDto(Integer result, String msg, T data, String exception) {
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.exception = exception;
        this.timestamp = new Date();
    }

    public static <T> RestResultDto<T> newResult(Integer result, String msg, T data, String exception) {
        return new RestResultDto<T>(result, msg, data, exception);
    }

    public static <T> RestResultDto<T> newSuccess() {
        return newResult(RESULT_SUCC,"",null,"");
    }
    public static <T> RestResultDto<T> newSuccess(T data) {
        return newResult(RESULT_SUCC,"",data,"");
    }
    public static <T> RestResultDto<T> newSuccess(T data, String msg) {
        return newResult(RESULT_SUCC,msg,data,"");
    }


    public static <T> RestResultDto<T> newFail(String exception) {
        return newResult(RESULT_FAIL,"",null, exception);
    }
    public static <T> RestResultDto<T> newFail(String msg, String exception) {
        return newResult(RESULT_FAIL, msg,null,exception);
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
