package com.umm.wfm.security.utils;


import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by wangshiqi on 2017/6/8.
 */
public class PwdUtil {

    /**
     * 工具类 不可实例化
     */
    private PwdUtil() {
    }

    // BCrypt parameters
    //private static final int LOG2_ROUNDS = 6;

    //new BCryptPasswordEncoder(LOG2_ROUNDS);
    private static final PasswordEncoder ENCODER = new StandardPasswordEncoder();

    /**
     * Encrypt string.
     * 加密
     *
     * @param plain the plain
     * @return the string
     */
    public static String encrypt(String plain) {
        return ENCODER.encode(Sha512DigestUtils.shaHex(plain));
    }

    /**
     * Verify boolean.
     * 校验 加密后的字符串和加密前的字符串是否一致
     *
     * @param plain  the plain
     * @param hashed the hashed
     * @return boolean
     */
    public static boolean verify(String plain, String hashed) {
        return ENCODER.matches(Sha512DigestUtils.shaHex(plain), hashed);
    }

}
