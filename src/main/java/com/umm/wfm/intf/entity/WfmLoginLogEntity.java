package com.umm.wfm.intf.entity;

import lombok.Data;

import java.util.Date;

/**
 * fmc_member_info表的 实体类
 *
 * @author qifei
 *         <p>
 *         代码迁移后的 mapper统一 @makun 2017-06-20
 */
@Data
public class WfmLoginLogEntity {

    private Integer id;

    private Long memberId;

    private String ipAddr;

    private String pValue;

    private String fingerPrint;

    private Integer operateType;

    private String deviceNo;

    private String systemNo;

    private String deviceType;

    private String version;

    private Date addTime;

    private Date updateTime;

    private Integer delFlag;

    private String deviceToken;

    private String deviceName;
}
