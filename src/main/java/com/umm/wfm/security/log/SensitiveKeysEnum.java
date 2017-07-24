package com.umm.wfm.security.log;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/27
 * Time: 10:52
 * Desc:  敏感信息枚举类
 */
public enum SensitiveKeysEnum {

    PASSWORD("password,passwd"),
    BANKCARD("bankCard"),
    IDCARD("idNumber,identityNumber"),
    IMAGEURL("imageFrontUrl,imageBackUrl,imageLiveUrl"),
    EMAIL("email"),
    ADDRESS("address,identityAddress"),
    PHONE("userName,account,phoneNum"),
    NAME("name,identityName"),;

    private String sensitiveKey;

    public String getSensitiveKey() {
        return this.sensitiveKey;
    }

    SensitiveKeysEnum(String sensitiveKey) {
        this.sensitiveKey = sensitiveKey;
    }

    /***
     * 获取所有的枚举值
     * @return 枚举类所有的枚举值
     */
    public static SensitiveKeysEnum[] getAll() {
        return values();
    }
}
