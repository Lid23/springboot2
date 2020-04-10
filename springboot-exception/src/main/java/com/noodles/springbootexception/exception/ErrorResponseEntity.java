package com.noodles.springbootexception.exception;

/**
 * @program: springboot
 * @description: ErrorResponseEntity
 * @author: Eric
 * @create: 2019-06-14 14:00
 **/
public class ErrorResponseEntity {

    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
