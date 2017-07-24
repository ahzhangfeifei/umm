package com.umm.wfm.intf.vo.member;

import lombok.Data;

/**
 * Created by makun on 2017/6/15.
 */
@Data
public class MemberVo extends MemberBaseVo {
    private String nickName;

    private String avatarUrl;

    private Integer status;

    private String gender;

    private String loginTime;

    private String fingerPrint;

    private String birthday;

    private String email;

    private String authenticationGrade;
}
