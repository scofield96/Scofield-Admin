package com.scofield.frame.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Scofield
 * @date 2020/11/05
 */
public class AopUtils {

    /**
     * 获取参数列表
     *
     * @param proceedingJoinPoint
     * @return
     */
    public static Map<String, Object> getFieldsName(ProceedingJoinPoint proceedingJoinPoint) {
        // 参数值
        Object[] args = proceedingJoinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
