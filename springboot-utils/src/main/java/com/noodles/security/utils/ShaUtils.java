package com.noodles.security.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.noodles.log.MySlf4j;

/**
 * @filename ShaUtils
 * @description 签名加密SHA，支持SHA-1，SHA-256
 * @autor noodles
 * @date 2019/6/27 13:01
 */
public class ShaUtils {

	/**加密方式：SHA-1*/
	public static final String Algorithm_SHA1 = "SHA-1";
	/**加密方式：SHA-256*/
	public static final String Algorithm_SHA256 = "SHA-256";

	/**
	 * 私有构造函数
	 * @return
	 * @author noodles
	 * @date 2019/8/6 14:54
	 */
	private ShaUtils() {

	}

	/**
	 * SHA签名
	 * @param input 请求参数
	 * @param algorithm 签名方式
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/27 13:04
	 */
	public static String digest(String input, String algorithm) {
		String result = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			byte[] output = messageDigest.digest(input.getBytes("UTF-8"));
			result = byte2String(output);
		} catch (NoSuchAlgorithmException ex) {
			MySlf4j.textError("SHA签名异常{0}", MySlf4j.ExceptionToString(ex));
		} catch (UnsupportedEncodingException ex) {
			MySlf4j.textError("SHA编码格式异常{0}", MySlf4j.ExceptionToString(ex));
		}

		return result;
	}

	/**
	 * 转换成十六进制字符串
	 * @param bytes 字节数组
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/6/27 13:33
	 */
	private static String byte2String(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			String temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				sb.append("0").append(temp);
			} else {
				sb.append(temp);
			}
		}
		return sb.toString();
	}
}
