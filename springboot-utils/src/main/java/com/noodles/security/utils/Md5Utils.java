package com.noodles.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.noodles.log.MySlf4j;

/**
 * @filename Md5Utils
 * @description MD5工具类
 * @autor noodles
 * @date 2019/8/6 15:01
 */
public class Md5Utils {

	/**
	 * 私有构造器
	 * @return
	 * @author noodles
	 * @date 2019/8/6 15:01
	 */
	private Md5Utils() {

	}

	/**
	 * 字符串MD5
	 * @param plainText 待Md5字符串
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/6 15:02
	 */
	public static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte[] b = md.digest();
			int i;
			StringBuilder sb = new StringBuilder("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			MySlf4j.textError("MD5异常,异常信息:{0}", MySlf4j.ExceptionToString(e));
			return null;
		}
	}

}
