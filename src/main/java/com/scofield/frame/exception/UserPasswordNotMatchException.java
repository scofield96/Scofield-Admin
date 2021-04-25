package com.scofield.frame.exception;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/25
 * @email: 543196660@qq.com
 * @time: 14:54
 */
public class UserPasswordNotMatchException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match");
    }
}
