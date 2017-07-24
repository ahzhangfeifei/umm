package com.umm.wfm.security.utils;

import org.springframework.util.StringUtils;

/**
 * Created by makun on 2017/6/14.
 */
public class StringMaskUtil {

    /**
     * 工具类 不可以实例化
     */
    private StringMaskUtil() {
    }

    private static final int PHONE_UNMASK_HEAD_LENGTH = 3;
    private static final int PHONE_UNMASK_TAIL_LENGTH = 4;

    private static final int BANKCARD_UNMASK_HEAD_LENGTH = 6;
    private static final int BANKCARD_UNMASK_TAIL_LENGTH = 4;

    private static final int IDNUMBER_UNMASK_HEAD_LENGTH = 1;
    private static final int IDNUMBER_UNMASK_TAIL_LENGTH = 1;

    /**
     * mask String
     *
     * @param str                原字符串
     * @param unmaskedHeadLength 保留的头长度
     * @param unmaskedTailLength 保留的尾长度
     * @return mask过的字符串
     */
    private static String mask(String str, int unmaskedHeadLength, int unmaskedTailLength) {
        if (StringUtils.isEmpty(str)) return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i < unmaskedHeadLength) sb.append(str.charAt(i));
            else if (i >= (str.length() - unmaskedTailLength)) sb.append(str.charAt(i));
            else sb.append('*');
        }
        return sb.toString();
    }

    /**
     * @param str 入参
     * @return 出参
     */
    public static String maskIdNumber(String str) {
        return mask(str, IDNUMBER_UNMASK_HEAD_LENGTH, IDNUMBER_UNMASK_TAIL_LENGTH);
    }

    /**
     * @param str 入参
     * @return 出参
     */
    public static String maskBankCard(String str) {
        return mask(str, BANKCARD_UNMASK_HEAD_LENGTH, BANKCARD_UNMASK_TAIL_LENGTH);
    }

    /**
     * @param str 入参
     * @return 出参
     */
    public static String maskPhoneNumber(String str) {
        return mask(str, PHONE_UNMASK_HEAD_LENGTH, PHONE_UNMASK_TAIL_LENGTH);
    }

    /**
     * @param str 入参
     * @return 出参
     */
    public static String maskName(String str) {
        if (StringUtils.isEmpty(str)) return str;
        return mask(str, 0, str.length() / 2);
    }
}
