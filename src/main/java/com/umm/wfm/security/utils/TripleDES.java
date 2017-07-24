package com.umm.wfm.security.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {	
	private static final String ALGORITHM = "DESede"; // 定义 加密算法,可用DES,DESede,Blowfish

	/**
	 * 工具类 不可实例化
	 */
	private TripleDES(){}

	/**
	 * 加密
	 * 
	 * @param des3Key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String des3Key, String src) throws Exception {
		Base64 encoder = new Base64();
		byte[] byteMing = src.getBytes("utf-8");
		SecretKey deskey = new SecretKeySpec(des3Key.getBytes("utf-8"), ALGORITHM); // 加密
		Cipher c1 = Cipher.getInstance(ALGORITHM);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] byteMi=c1.doFinal(byteMing);
		String strMi = new String(encoder.encode(byteMi));
		return strMi;
	}

	/**
	 * 解密
	 * 
	 * @param des3Key
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String des3Key, String src) throws Exception {
		Base64 base64Decoder = new Base64();
		byte[] byteMi = base64Decoder.decode(src);
		SecretKey deskey = new SecretKeySpec(des3Key.getBytes(), ALGORITHM); // 解密
		Cipher c1 = Cipher.getInstance(ALGORITHM);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] byteMing=c1.doFinal(byteMi);
		String strMing=new String(byteMing, "utf-8");
		return strMing;

	}		
}
