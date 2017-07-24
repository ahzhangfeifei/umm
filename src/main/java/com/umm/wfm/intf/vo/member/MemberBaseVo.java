package com.umm.wfm.intf.vo.member;

/**
 * Created by makun on 2017/6/15.
 */
public class MemberBaseVo {

    private Long memberId;

    private String userName;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
