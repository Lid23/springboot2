package com.noodles.security.utils;

import java.io.UnsupportedEncodingException;

import com.noodles.log.MySlf4j;

/**
 * @filename AsciiUtils
 * @description 通过ASCII计算字符长度
 * @author noodles
 * @date 2019/12/13 13:51
 */
public class AsciiUtils {

	/**
	 * 私有化无参构造器
	 * @author noodles
	 * @date 2019/12/13 13:51
	 */
	private AsciiUtils() {
	}

	/**
	 * 计算字符串ascii长度
	 * @param value
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/12/13 13:51
	 */
	public static String strAsciiLength(String value) {
		int length = 0;
		try {
			length = value.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String str = length + "";
		for (int j = str.length(); j < 6; j++) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * Ascii转字符串
	 * @param value
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/12/13 13:51
	 */
	public static String asciiToString(String value) {
		StringBuffer sbu = new StringBuffer();
		String[] chars = value.split(",");
		for (int i = 0; i < chars.length; i++) {
			sbu.append((char) Integer.parseInt(chars[i]));
		}
		return sbu.toString();
	}

	/**
	 * 字节数组转十六进制字符串
	 * @param src byte串
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/12/13 13:51
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 十六进制字符串转换为字符串
	 * @param s 十六进制字符串
	 * @param format 编码格式
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/12/13 13:52
	 */
	public static String hexStringToString(String s, String format) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, format);
			new String();
		} catch (Exception e) {
			MySlf4j.textError("十六进制字符串转换为字符串异常,异常信息:{0}", MySlf4j.ExceptionToString(e));
			return null;
		}
		return s;
	}

}
