package com.scofield.frame.security.filter;

import com.scofield.frame.security.jwt.JwtUser;
import com.scofield.frame.security.jwt.TokenService;
import com.scofield.frame.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Scofield
 * @description: token过滤器 验证token有效性
 * @date: 2021/4/23
 * @email: 543196660@qq.com
 * @time: 16:57
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        JwtUser loginUser = tokenService.getLoginUser(request);
        //已经登录，没有Authentication
        //如果得到的用户名不为空，或则获得当前的线程里面的权限不为空
        if (loginUser != null && SecurityUtils.getAuthentication() == null) {
            //验证
            tokenService.verifyToken(loginUser);
            //根据新的jwtUser 生成 authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //把新的authentication 加载到线程里面
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
