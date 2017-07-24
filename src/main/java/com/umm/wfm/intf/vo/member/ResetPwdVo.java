package com.umm.wfm.intf.vo.member;

/**
 * Created by makun on 2017/6/15.
 */
public class ResetPwdVo {
    //    private Long memberId;
    private String phone; // 忘记密码的时候 重置密码，处于未登录状态，没有memberId，改用手机号
    private String newPassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
