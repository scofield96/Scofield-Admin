package com.scofield.controller;

import com.scofield.frame.constant.HttpStatus;
import com.scofield.frame.enums.UserStatus;
import com.scofield.frame.security.jwt.JwtUser;
import com.scofield.frame.security.jwt.TokenService;
import com.scofield.frame.utils.R;
import com.scofield.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Scofield
 * @description: 登录控制器
 * @date: 2021/4/25
 * @email: 543196660@qq.com
 * @time: 14:46
 */
@RestController
public class LoginController {

    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword()));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //账号密码匹配不上
                return R.error(HttpStatus.UNAUTHORIZED, "认证失败,请检查账号密码");
            } else {
                return R.error(UserStatus.DISABLE.getValue());
            }
        }
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        String token = tokenService.createToken(jwtUser);
        return R.success("登录成功！").setData(token);
    }
}
