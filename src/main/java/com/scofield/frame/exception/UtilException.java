package com.scofield.frame.exception;

/**
 * @author Scofield
 * @description: 工具类异常
 * @date: 2021/4/26
 * @email: 543196660@qq.com
 * @time: 14:29
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
