package com.scofield.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scofield.entity.User;
import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.Query;
import com.scofield.mapper.UserMapper;
import com.scofield.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * User 服务实现类
 * </p>
 *
 * @author Scofield
 * @since 2021-04-26
 */
@Service("UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {

       @Override
       public PageUtils queryByPage(Map<String, Object> params) {

            IPage<User> page = this.page(
                  new Query<User>().getPage(params),
                  new LambdaQueryWrapper<>()
            );
          return new PageUtils(page);
       }

}
