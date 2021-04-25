package com.scofield.service;

import com.scofield.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import com.scofield.frame.utils.PageUtils;

import java.util.Map;

/**
 * <p>
 * User 服务类
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
public interface UserService extends IService<User> {

     /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryByPage(Map<String, Object> params);
}
