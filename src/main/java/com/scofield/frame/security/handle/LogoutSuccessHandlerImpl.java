package com.scofield.frame.security.handle;

import com.scofield.frame.security.jwt.JwtUser;
import com.scofield.frame.security.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Scofield
 * @description: 自定义退出处理类 返回成功
 * @date: 2021/4/23
 * @email: 543196660@qq.com
 * @time: 16:56
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        JwtUser loginUser = tokenService.getLoginUser(request);

        if (loginUser != null) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }

    }

}
