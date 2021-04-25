package com.scofield.service;

import com.scofield.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import com.scofield.frame.utils.PageUtils;

import java.util.Map;

/**
 * <p>
 * Log 服务类
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
public interface LogService extends IService<Log> {

     /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryByPage(Map<String, Object> params);
}
