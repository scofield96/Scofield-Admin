package com.scofield.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/25
 * @email: 543196660@qq.com
 * @time: 14:45
 */
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;
}
