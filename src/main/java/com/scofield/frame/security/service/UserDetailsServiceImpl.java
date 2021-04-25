package com.scofield.frame.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scofield.entity.Role;
import com.scofield.entity.User;
import com.scofield.entity.UserRole;
import com.scofield.frame.enums.UserStatus;
import com.scofield.frame.exception.BaseException;
import com.scofield.frame.security.jwt.JwtUser;
import com.scofield.service.RoleService;
import com.scofield.service.UserRoleService;
import com.scofield.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/23
 * @email: 543196660@qq.com
 * @time: 16:58
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {
        //查权限
        UserRole userRole = userRoleService.getById(user.getUserId());
        Role role = roleService.getById(userRole.getRoleId());
        JwtUser jwtUser = new JwtUser(role.getRoleKey(), user);
        return jwtUser;
    }

}
