package com.umm.wfm.intf.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * company umm.com
 * Created by zhangff on 2017/7/6.
 */
public enum MicroPaymentEnum {

    DEFAULT(0, "未设置"),
    IS_TRUE(1, "已设置");

    MicroPaymentEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Setter
    @Getter
    private Integer code;
    @Setter
    @Getter
    private String name;

}
