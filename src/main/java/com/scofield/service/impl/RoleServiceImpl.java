package com.scofield.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scofield.entity.Role;
import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.Query;
import com.scofield.mapper.RoleMapper;
import com.scofield.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * Role 服务实现类
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@Service("RoleServiceImpl")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>implements RoleService {

       @Override
       public PageUtils queryByPage(Map<String, Object> params) {

            IPage<Role> page = this.page(
                  new Query<Role>().getPage(params),
                  new LambdaQueryWrapper<>()
            );
          return new PageUtils(page);
       }

}
