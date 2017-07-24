package com.umm.wfm.intf.vo.identity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by makun on 2017/6/16.
 * <p>
 * 身份认证后，可以查到姓名、身份证号
 */

@Getter
@Setter
public class IdentityInfoVo {
    private String identityName; // 身份证上姓名

    private String identityNumber; // 身份证号

    private String identityAddress; // 身份证上地址
}
