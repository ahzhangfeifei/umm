package com.umm.wfm;

import com.umm.finance.base.security.crypto.CryptoType;
import com.umm.finance.base.security.crypto.RSA;
import com.umm.finance.base.security.crypto.RSACipherSpec;
import com.umm.finance.base.security.crypto.RSAKeyStore;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSATest {
	
	/**
	 *  ./keytool -genkey -alias wfm -keypass keypwd -keyalg RSA -keysize 1024 -validity 1095 -keystore D:/wfm.keystore -storepass storepwd
	 */
	
	static String path = "D:\\wfm.keystore";
	static String storePassword = "storepwd";
	static String alias = "wfm";
	static String keyPassword = "keypwd";
	
	static RSACipherSpec getSpec(CryptoType type) throws Exception{
		PublicKey publicKey = RSAKeyStore.getPublicKey(path, storePassword, alias);
		PrivateKey privateKey = RSAKeyStore.getPrivateKey(path, storePassword, alias, keyPassword);
		return RSA.getCipherSpec(publicKey, privateKey, type);
	}
	
	static public String encrypt(String str) throws Exception {
		RSACipherSpec spec = getSpec(CryptoType.ENCRYPT);
		return RSA.process(str, spec);
	}
	
	static public String decrypt(String str) throws Exception {
		RSACipherSpec spec = getSpec(CryptoType.DECRYPT);
		return RSA.process(str, spec);
	}
	
	public static void main(String[] args) throws Exception{
		String cipherText = encrypt("1234567890123456");
		System.out.println(cipherText);
		cipherText = encrypt("abcdefghijklmn11");
		System.out.println(cipherText);
	}
}
