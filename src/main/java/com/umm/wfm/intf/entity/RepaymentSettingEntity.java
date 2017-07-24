package com.umm.wfm.intf.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * Created by zhangff on 2017/6/15.
 */
@Getter
@Setter
@Table(name = "wfm_repayment_setting")
public class RepaymentSettingEntity {

    /*还款设置ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*系统编码*/
    @Column(name = "sys_code")
    private String sysCode;
    /*会员ID*/
    @Column(name = "member_id")
    private Long memberId;
    /*自动还款设置0:默认,未设置1:已设置*/
    @Column(name = "auto_repayment")
    private Integer autoRepayment;
    /*自动还款代扣时间*/
    @Column(name = "repayment_date")
    private Integer repaymentDate;
    /*设置时间*/
    @Column(name = "insert_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    /*最后更新时间*/
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
