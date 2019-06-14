package com.noodles.springbootexception.exception;

/**
 * @program: springboot2
 * @description: CustomException
 * @author: Eric
 * @create: 2019-06-14 14:00
 **/
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomException() {
        super();
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
