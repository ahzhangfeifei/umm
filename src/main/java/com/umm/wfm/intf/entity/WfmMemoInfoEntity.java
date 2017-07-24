package com.umm.wfm.intf.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/13
 * Time: 10:51
 * Desc:
 */
@Data
public class WfmMemoInfoEntity {

    //表主键id
    private Integer id;
    //会员id
    private Long memberId;
    //添加人uid
    private Integer addUid;
    //添加人 姓名
    private String addName;
    //备注内容
    private String memoContent;
    //添加时间
    private Date addTime;

}
