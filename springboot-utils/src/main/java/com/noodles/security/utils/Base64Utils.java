package com.noodles.security.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * @filename Base64Utils
 * @description base64编码解密工具包
 * @author noodles
 * @date 2019/3/26 16:51
 */
public class Base64Utils {

	/**
	 * 私有构造函数
	 * @return
	 * @author noodles
	 * @date 2019/8/6 14:53
	 */
	private Base64Utils() {

	}

	/**
	 * 将 s 进行 BASE64 编码
	 * @param bt
	 * @return
	 * 作者：noodles
	 * 日期：2016年4月6日下午3:21:37
	 */
	public static String encode(byte[] bt) throws UnsupportedEncodingException {
		if (bt == null) {
			return null;
		}
		Base64 base64 = new Base64();
		bt = base64.encode(bt);
		String str = new String(bt, "UTF-8");
		return str;
	}


	/**
	 * 将 s 进行 BASE64 编码
	 * @param s
	 * @return
	 * 作者：noodles
	 * 日期：2016年4月6日下午3:22:01
	 */
	public static String encode(String s) throws UnsupportedEncodingException {
		if (s == null) {
			return null;
		}
		return encode(s.getBytes("UTF-8"));
	}

	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * @param str
	 * @return
	 * 作者：noodles
	 * 日期：2016年4月6日下午3:22:08
	 */
	public static byte[] decode(String str) throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		byte[] bt = str.getBytes("UTF-8");
		try {
			Base64 base64 = new Base64();
			bt = base64.decode(bt);
			return bt;
		} catch (Exception e) {
			return null;
		}
	}

}