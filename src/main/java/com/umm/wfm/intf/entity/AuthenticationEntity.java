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
 * company : umm.com
 * Created by zhangff on 2017/7/6.
 */
@Getter
@Setter
@Table(name = "wfm_authentication")
public class AuthenticationEntity {
    /*还款设置ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*会员ID*/
    @Column(name = "member_id")
    private Long memberId;
    /*信用等级*/
    @Column(name = "authentication_grade")
    private Integer authenticationGrade;
    /*设置时间*/
    @Column(name = "insert_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    /*最后更新时间*/
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
