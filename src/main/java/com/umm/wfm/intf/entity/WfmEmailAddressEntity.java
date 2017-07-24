package com.umm.wfm.intf.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/13
 * Time: 10:52
 * Desc:
 */
@Data
public class WfmEmailAddressEntity {

    private Integer id;

    private Long memberId;

    private Integer isDefault;

    private String name;

    private String phone;

    private String address;

    private String zipcode;

    private Date addTime;

    private Date updateTime;

    private Integer delFlag;

}
