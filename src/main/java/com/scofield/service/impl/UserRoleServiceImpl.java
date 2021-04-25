package com.scofield.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scofield.entity.UserRole;
import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.Query;
import com.scofield.mapper.UserRoleMapper;
import com.scofield.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * UserRole 服务实现类
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@Service("UserRoleServiceImpl")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>implements UserRoleService {

       @Override
       public PageUtils queryByPage(Map<String, Object> params) {

            IPage<UserRole> page = this.page(
                  new Query<UserRole>().getPage(params),
                  new LambdaQueryWrapper<>()
            );
          return new PageUtils(page);
       }

}
