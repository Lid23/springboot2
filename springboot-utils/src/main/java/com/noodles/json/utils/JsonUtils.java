package com.noodles.json.utils;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 文件名：JsonUtils.java
 * 描述：JSON格式化工具类(对象转JSON，JSON转对象)
 * 作者：noodles
 * 日期：2016年6月8日下午3:35:52
 */
public class JsonUtils {

	/**
	 * 私有构造器
	 * @return
	 * @author noodles
	 * @date 2019/8/6 14:55
	 */
	private JsonUtils() {

	}

	/**
	 * 对象转换成json字符串 
	 * @param obj 对象
	 * @return
	 * 作者：noodles
	 * 日期：2016年6月22日下午12:29:26
	 */
	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof String && StringUtils.isBlank(obj.toString())) {
			return null;
		}
		// disableHtmlEscaping:特殊字符不转义,统一日期格式
		Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj);
	}

	/**
	 * json字符串转成对象
	 * @param str  json串
	 * @param type 泛型
	 * @return
	 * 作者：noodles
	 * 日期：2016年6月22日下午12:29:56
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(str, type);
	}

	/**
	 * json字符串转成对象
	 * @param str  json串
	 * @param type 泛型
	 * @return
	 * 作者：noodles
	 * 日期：2016年6月22日下午12:29:56
	 */
	public static <T> T fromJson(String str, Type type) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		Gson gson = (new GsonBuilder()).disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(str, type);
	}

}
