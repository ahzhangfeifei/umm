package com.umm.wfm.intf.enums;

/**
 * Created by makun on 2017/6/15.
 * 登录状态枚举
 */
public enum LoginStatusEnum {
    /**
     * 1,"登录进来"
     */
    LOGIN_IN(1, "登录进来"),

    /**
     * 2,"退出登录"
     */
    LOGIN_OUT(2, "退出登录"),;

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param code 编码
     * @param name 名字
     */
    LoginStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
