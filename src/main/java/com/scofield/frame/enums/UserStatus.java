package com.scofield.frame.enums;

/**
 * @author Scofield
 * @description:
 * @date: 2021/4/25
 * @email: 543196660@qq.com
 * @time: 14:34
 */
public enum UserStatus {

    OK("0", "正常"), DISABLE("1", "该账户已停用"), DELETED("2", "该账户已删除");

    private final String code;
    private final String value;

    UserStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
