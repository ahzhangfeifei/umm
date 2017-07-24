package com.umm.wfm.intf.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhangff on 2017/6/19.
 */


public enum SystemCodeEnum {

    PDL("PDL","小黑鱼系统");

    SystemCodeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

    @Setter
    @Getter
    private String code;
    @Setter
    @Getter
    private String name;




}
