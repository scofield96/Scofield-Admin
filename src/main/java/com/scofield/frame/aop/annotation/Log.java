package com.scofield.frame.aop.annotation;

import java.lang.annotation.*;

/**
 * @author Scofield
 * @description: 自定义操作日志记录注解
 * @date: 2021/4/21
 * @email: 543196660@qq.com
 * @time: 19:09
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value();

}
