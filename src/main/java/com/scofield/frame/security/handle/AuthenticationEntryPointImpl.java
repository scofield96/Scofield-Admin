package com.scofield.frame.security.handle;

import com.alibaba.fastjson.JSON;
import com.scofield.frame.constant.HttpStatus;
import com.scofield.frame.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Scofield
 * @description: 认证失败处理类 返回未授权
 * @date: 2021/4/23
 * @email: 543196660@qq.com
 * @time: 16:56
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = "认证失败，无法访问系统资源";
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(R.error(code,msg)));

    }

}
