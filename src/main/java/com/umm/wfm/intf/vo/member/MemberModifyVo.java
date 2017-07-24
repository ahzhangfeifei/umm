package com.umm.wfm.intf.vo.member;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by makun on 2017/6/15.
 * 会员信息修改入参
 */
public class MemberModifyVo {
    @NotNull
    @Min(1)
    private Long memberId;

    private String nickName;

    private String avatarUrl;

    private Integer status;

    private Date loginTime;

    private String fingerPrint;

    private String gender;

    private String birthday;

    private String email;

    private Integer identifyFlag;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Integer getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(Integer identifyFlag) {
        this.identifyFlag = identifyFlag;
    }
}
