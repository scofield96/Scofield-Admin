package com.scofield.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author Scofield
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_log")
public class Log implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 操作用户
     */
    private String userName;

    /**
     * 操作描述
     */
    private String operation;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 耗时(毫秒)
     */
    private Integer time;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 操作参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 操作地点
     */
    private String location;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 状态 0-正常  2-删除
     */
    private String delFlag;


}
