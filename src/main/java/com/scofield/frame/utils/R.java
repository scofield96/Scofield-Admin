package com.scofield.frame.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/21
 * @email: 543196660@qq.com
 * @time: 12:57
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class R implements Serializable {

    //成功状态 1成功 0失败
    private Boolean flag;

    private Integer code;

    private String msg;

    private Object data;

    public static R success(String msg) {
        return new R().setFlag(true).setCode(200).setMsg(msg);
    }

    public static R success(String msg, Object o) {
        return new R().setFlag(true).setCode(200).setMsg(msg).setData(o);
    }

    public static R success(Boolean flag, Integer code, String msg, Object data) {
        return new R().setFlag(true).setCode(code).setMsg(msg).setData(data);
    }

    public static R error(Integer code, String msg) {
        return new R().setFlag(false).setCode(code).setMsg(msg);
    }
    public static R error(String msg) {
        return new R().setFlag(false).setCode(500).setMsg(msg);
    }

}
