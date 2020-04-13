package com.noodles.security.utils;

import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.noodles.log.MySlf4j;

/**
 * @filename AesUtils
 * @description AES加解密工具类
 * @author noodles
 * @date 2019/8/22 15:19
 */
public class AesUtils {

	/**
	 * 私有化无参构造器
	 * @author noodles
	 * @date 2019/8/22 15:19
	 */
	private AesUtils() {
	}

	/**解密方式*/
	private static final String AES = "AES";

	/**算法/模式/补码方式*/
	private static final String TYPE = "AES/ECB/PKCS5Padding";

	/**编码格式-GBK*/
	public static final String CHARACTER_GBK = "GBK";
	/**编码格式-UTF-8*/
	public static final String CHARACTER_UTF8 = "UTF-8";

	/**
	 * 十六进制字符串转二进制byte[]
	 * @param value 密文
	 * @return byte[]
	 * @author noodles
	 * @date 2019/8/22 17:18
	 */
	private static byte[] hexToByte(String value) {
		ByteBuffer bf = ByteBuffer.allocate(value.length() / 2);
		for (int i = 0; i < value.length(); i++) {
			String hexStr = value.charAt(i) + "";
			i++;
			hexStr += value.charAt(i);
			byte b = (byte) Integer.parseInt(hexStr, 16);
			bf.put(b);
		}
		return bf.array();
	}

	/**
	 * 二行制byte[]转十六进制字符串
	 * @param byteValue byte数组
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/21 9:38
	 */
	private static String byteToHex(byte[] byteValue) {
		String stmp = "";
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < byteValue.length; n++) {
			stmp = (Integer.toHexString(byteValue[n] & 0XFF));
			if (stmp.length() == 1) {
				sb.append("0");
				sb.append(stmp);
			} else {
				sb.append(stmp);
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 加密明文得到byte[]
	 * @param value 明文
	 * @param key 秘钥
	 * @param character 编码格式
	 * @return byte[]
	 * @author noodles
	 * @date 2019/8/22 17:33
	 */
	private static byte[] encryptByte(String value, String key, String character) throws Exception {
		byte[] raw = key.getBytes(character);
		SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
		Cipher cipher = Cipher.getInstance(TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		return cipher.doFinal(value.getBytes(character));
	}

	/**
	 * 解密byte[]得到明文
	 * @param bytes byte[]
	 * @param key 秘钥
	 * @param character 编码格式
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/22 17:35
	 */
	private static String decrypt(byte[] bytes, String key, String character) {
		try {
			byte[] raw = key.getBytes(character);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
			Cipher cipher = Cipher.getInstance(TYPE);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			/** 先用base64解密 */
			try {
				byte[] original = cipher.doFinal(bytes);
				String originalString = new String(original, character);
				return originalString;
			} catch (Exception e) {
				MySlf4j.textInfo("AES解密异常:{0}", MySlf4j.ExceptionToString(e));
				return null;
			}
		} catch (Exception ex) {
			MySlf4j.textInfo(ex.toString());
			return null;
		}
	}

	/**
	 * 加密明文得到密文-二行制byte[]转十六进制字符串方式
	 * @param value 明文
	 * @param key 秘钥
	 * @param character 编码格式
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/22 17:38
	 */
	public static String encryptByHex(String value, String key, String character) throws Exception {
		if (key == null) {
			MySlf4j.textInfo("Key为空null");
			return null;
		}
		/** 判断Key是否为16位 */
		if (key.length() != 16) {
			MySlf4j.textInfo("Key长度不是16位");
			return null;
		}
		return byteToHex(encryptByte(value, key, character));
	}

	/**
	 * 解密密文得到明文-十六进制字符串转二进制byte[]方式
	 * @param value 密文
	 * @param key 秘钥
	 * @param character 编码格式
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/22 15:40
	 */
	public static String decryptByHex(String value, String key, String character) {
		try {
			/** 判断Key是否正确 */
			if (key == null) {
				MySlf4j.textInfo("Key为空null");
				return null;
			}
			/** 判断Key是否为16位 */
			if (key.length() != 16) {
				MySlf4j.textInfo("Key长度不是16位");
				return null;
			}
			return decrypt(hexToByte(value), key, character);
		} catch (Exception ex) {
			MySlf4j.textInfo(ex.toString());
			return null;
		}
	}

}
