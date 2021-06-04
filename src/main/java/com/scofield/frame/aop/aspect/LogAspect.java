package com.scofield.frame.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.scofield.frame.aop.annotation.Log;
import com.scofield.frame.utils.AddressUtils;
import com.scofield.frame.utils.AopUtils;
import com.scofield.frame.utils.SecurityUtils;
import com.scofield.frame.utils.SpringContextUtils;
import com.scofield.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author Scofield
 * @description: 操作日志切面
 * @date: 2021/4/21
 * @email: 543196660@qq.com
 * @time: 19:10
 */


@Slf4j
@Aspect
@Component
@Order(1)// @Order 注解用来声明组件的顺序，值越小，优先级越高，越先被执行/初始化。如果没有该注解，则优先级最低。
public class LogAspect {

    @Autowired
    LogService logService;

    @Pointcut("@annotation(com.scofield.frame.aop.annotation.Log)")
    public void logAspect() {
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("logAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        long startTime = System.currentTimeMillis();
        Object result = null;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        Map<String, Object> fieldsName = AopUtils.getFieldsName((proceedingJoinPoint));
        fieldsName.remove("request");

        Log annotation = method.getAnnotation(Log.class);
        String ip = AddressUtils.getIpAddr(request);
        com.scofield.entity.Log sysLog = new com.scofield.entity.Log();
        sysLog.setOperation(annotation.value())
                .setUserName(SecurityUtils.getUsername())
                .setType(request.getMethod())
                .setTime((int) (System.currentTimeMillis() - startTime))
                .setMethod(String.valueOf(proceedingJoinPoint.getSignature()))
                .setParams(JSON.toJSONString(fieldsName))
                .setCreateTime(new Date())
                .setIp(ip)
                .setLocation(AddressUtils.getAddress(ip));
        logService.save(sysLog);
        return result;
    }

}
