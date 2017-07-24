package com.umm.wfm.intf.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangff on 2017/6/15.
 */
@Getter
@Setter
@Table(name = "wfm_payment_setting")
public class PaymentSettingEntity {

    /*支付设置ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*会员ID*/
    @Column(name = "member_id")
    private Long memberId;

    /*支付密码*/
    @Column(name = "payment_pwd")
    private String paymentPwd;

    /*指纹支付设置0:默认,未设置1:已设置*/
    @Column(name = "fingerprint_verify")
    private Integer fingerprintVerify;

    /*手势设置0:默认,未设置1:已设置*/
    @Column(name = "gesture_verify")
    private Integer gestureVerify;

    /*小额免密设置0:默认,未设置1:已设置*/
    @Column(name = "micro_payment")
    private Integer microPayment;

    /*小额免密金额*/
    @Column(name = "micro_payment_amount")
    private BigDecimal microPaymentAmount;

    /*是否删除0:默认,未删除1:配置已删除*/
    @Column(name = "is_del")
    private Integer isDel;

    /*设置时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "insert_time")
    private Date insertTime;

    /*最后更新时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;


}
