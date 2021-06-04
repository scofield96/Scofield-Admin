package com.scofield.frame.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.scofield.frame.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/26
 * @email: 543196660@qq.com
 * @time: 14:05
 */
@Component
public class MybatisPlusObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("createBy", SecurityUtils.getUsername(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
        this.setFieldValByName("password", SecurityUtils.encryptPassword("123456"), metaObject);
        this.setFieldValByName("status", "0", metaObject);
        this.setFieldValByName("delFlag", "0", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", SecurityUtils.getUsername(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}
