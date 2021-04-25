package com.scofield.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scofield.entity.Log;
import com.scofield.frame.utils.PageUtils;
import com.scofield.frame.utils.Query;
import com.scofield.mapper.LogMapper;
import com.scofield.service.LogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * Log 服务实现类
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@Service("LogServiceImpl")
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>implements LogService {

       @Override
       public PageUtils queryByPage(Map<String, Object> params) {

            IPage<Log> page = this.page(
                  new Query<Log>().getPage(params),
                  new LambdaQueryWrapper<>()
            );
          return new PageUtils(page);
       }

}
