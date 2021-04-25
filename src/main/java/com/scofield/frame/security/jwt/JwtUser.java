package com.scofield.frame.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scofield.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/25
 * @email: 543196660@qq.com
 * @time: 13:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUser implements UserDetails {

    /**
     * 用户唯一标识
     */
    private String token;
    private String role;//角色
    private User user;  //用户对象
    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    private String ipaddr; //登录IP地址

    private String loginLocation; //登录地点

    public JwtUser(String role, User user) {
        this.role = role;
        this.user = user;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { //账户是否未过期,过期无法验证
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {  //指定用户是否解锁,锁定的用户无法进行身份验证
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() { // 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() { //是否可用 ,禁用的用户不能身份验证
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
