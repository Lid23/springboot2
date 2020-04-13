package com.noodles.random.utils;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
/**
 * @filename SequenceUtils
 * @description 生成唯一序列工具类
 * @autor noodles
 * @date 2019/8/6 10:54
 */
public class RandomUtils {

	/**种子-数字&英文字符*/
	public static final String NUMBERS_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**种子-数字*/
	public static final String NUMBERS = "0123456789";
	/**种子-英文字符*/
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 空构造函数
	 * @return
	 * @author noodles
	 * @date 2019/8/6 10:59
	 */
	private RandomUtils() {

	}

	/**
	 * 得到32位唯一的UUID
	 * @return java.io.Serializable
	 * @author noodles
	 * @date 2019/8/6 11:04
	 */
	public static String getUuid() {
		UUID uid = UUID.randomUUID();
		return uid.toString().replace("-", "");
	}

	/**
	 * 得到特定长度的UUID
	 * @param length 指定长度
	 * @return java.io.Serializable
	 * @author noodles
	 * @date 2019/8/6 11:15
	 */
	public static String getUuid(int length) {
		UUID uid = UUID.randomUUID();
		String temp = uid.toString().replace("-", "");
		if (length > 0 && length < temp.length()) {
			temp = temp.substring(temp.length() - length);
		}
		return temp;
	}

	/**
	 * 获取特定长度随机数
	 * @param length 长度
	 * @param seedStr 种子
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/6 11:19
	 */
	public static String getRandomNumbers(int length, final String seedStr) {
		return StringUtils.isEmpty(seedStr) ? null : getRandom(seedStr.toCharArray(), length);
	}

	/**
	 * 得到一个固定长度的随机字符串
	 * @param sourceChar 源字符
	 * @param length 目标长度
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/6 11:23
	 */
	private static String getRandom(char[] sourceChar, int length) {
		if (sourceChar == null || sourceChar.length == 0 || length < 0) {
			return null;
		}
		StringBuilder str = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str.append(sourceChar[random.nextInt(sourceChar.length)]);
		}
		return str.toString();
	}

}
