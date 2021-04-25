package com.scofield.frame.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/21
 * @email: 543196660@qq.com
 * @time: 16:25
 */
public class Query<T> {
    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1; //默认第一页
        long limit = 10;  //默认10条

        if (params.get("page") != null) {
            curPage = Long.parseLong((String) params.get("page"));
        }
        if (params.get("limit") != null) {
            limit = Long.parseLong((String) params.get("limit"));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put("page", page);


        //没有排序字段，则不排序
        if (StringUtils.isBlank(defaultOrderField)) {
            return page;
        }

        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
