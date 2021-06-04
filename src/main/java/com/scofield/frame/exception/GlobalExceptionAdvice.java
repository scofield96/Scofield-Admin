package com.scofield.frame.exception;


import com.scofield.frame.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Scofield
 * @description: 集中处理所有异常
 * @date: 2021/4/22
 * @email: 543196660@qq.com
 * @time: 9:29
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("错误异常{}, --- 异常类型{}", throwable, throwable.getMessage().getClass());
        return R.error("系统错误");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException e) {
        log.error("运行时异常{}, --- 异常类型{}", e, e.getMessage().getClass());
        return R.error("系统运行发生异常");
    }


}
