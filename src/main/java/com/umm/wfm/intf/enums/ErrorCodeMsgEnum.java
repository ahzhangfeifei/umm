package com.umm.wfm.intf.enums;

/**
 * Created by makun on 2017/6/15.
 * 错误码和错误信息
 */
public enum ErrorCodeMsgEnum {
    //------------本系统以1xxxxxx开头错误码----------------
    WFM_OPERATE_SUCCESS(100000, "操作成功！"),

    WFM_OPERATE_EXCEPTION(100001, "操作失败！"),

    WFM_INTERACTIVE_ERROR(100002, "与外系统交互数据异常"),

    WFM_MEMBER_NONEXIST(100003, "会员信息不存在"),

    WFM_MEMBER_MODIFY(100004, "修改会员信息失败"),

    WFM_LOGIN_PASSWORD_WRONG(100005, "登录密码不正确"),

    WFM_PASSWORD_RULE_CHECK_NOT_PASS(100006, "密码不符合规则"),

    WFM_IDENTITY_NOT_YET(100007, "没有认证，需要先身份认证！"),

    WFM_SAME_PASSWORD(100008, "您设置的密码与上次密码相同，建议更换密码!"),

    BANKCARD_NONEXIST(100009, "银行卡信息不存在"),

    BANKCARD_NUM_DIFFER(100010, "银行卡号输入有误"),

    BANKCARD_TEL_DIFFER(100011, "预留手机号输入有误"),;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @param errorCode 错误码
     * @param errorMsg  错误信息描述
     */
    ErrorCodeMsgEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
