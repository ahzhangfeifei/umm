package com.umm.wfm.security.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Aes加密解密
 * Created by wangshiqi on 2017/6/8.
 */
public class AesUtil {
    /**
     * 工具类不可实例化
     */
    private AesUtil() {
    }

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final String ALGORITHM_NAME = "AES";

    // 私钥
    private static String aeskey = "F91797EDC92BD44E";

    // 初始化向量
    private static String aesiv = "DE2F15D014D40B93";

    /**
     * @param encryptContent 加密的内容
     * @return String
     * @throws javax.crypto.NoSuchPaddingException exception
     * @throws java.security.NoSuchAlgorithmException exception
     * @throws java.security.InvalidAlgorithmParameterException exception
     * @throws java.security.InvalidKeyException exception
     */
    public static String encrypt(String encryptContent) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aeskey.getBytes(), ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(aesiv.getBytes());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] ret = cipher.doFinal(encryptContent.getBytes("utf-8"));
        // return new String(Base64.getEncoder().encode(ret));  // Base64编码
        return Hex.encodeHexString(ret);  // HEX编码
    }

    /**
     * @param decryptContent 解密的内容
     * @return String
     * @throws javax.crypto.NoSuchPaddingException exception
     * @throws java.security.NoSuchAlgorithmException exception
     * @throws java.security.InvalidAlgorithmParameterException exception
     * @throws java.security.InvalidKeyException exception
     * @throws javax.crypto.BadPaddingException exception
     * @throws javax.crypto.IllegalBlockSizeException exception
     */
    public static String decrypt(String decryptContent) throws Exception {
        // byte[] bytes = Base64.getDecoder().decode(decryptContent); // Base64解码
        byte[] bytes = Hex.decodeHex(decryptContent.toCharArray()); // HEX编码
        SecretKeySpec secretKeySpec = new SecretKeySpec(aeskey.getBytes(), ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(aesiv.getBytes());
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] ret = cipher.doFinal(bytes);
        return new String(ret, "utf-8");
    }

    public static String getAeskey() {
        return aeskey;
    }

    public static void setAeskey(String aeskey) {
        AesUtil.aeskey = aeskey;
    }

    public static String getAesiv() {
        return aesiv;
    }

    public static void setAesiv(String aesiv) {
        AesUtil.aesiv = aesiv;
    }
}
