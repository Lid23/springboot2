package com.noodles.springbootmybatis.generate.util;

import java.util.Locale;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

/**
 * @filename GenerateUtil
 * @description 工具类
 * @author 巫威
 * @date 2019/8/15 17:15
 */
public class GenerateUtil {

	private static final String SET = "set";

	private static final String GET = "get";

	public static String getSerialVersionUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static Long getRandomLong(){
		return RandomUtils.nextLong(0, Long.MAX_VALUE);
	}


	public static String getSetter(String name){
		return SET + name.substring(0, 1).toUpperCase(Locale.ENGLISH) + name.substring(1);
	}

	public static String getGetter(String name){
		return GET + name.substring(0, 1).toUpperCase(Locale.ENGLISH) + name.substring(1);
	}

	public static void main(String[] args) {
		System.out.println(getSetter("name"));
	}



}
