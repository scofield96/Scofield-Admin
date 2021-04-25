package com.scofield.frame.exception;

/**
 * @author Scofield
 * @description: 基础异常
 * @date: 2021/4/23
 * @email: 543196660@qq.com
 * @time: 15:20
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public BaseException() {
    }

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        this.message = message;
    }
}
