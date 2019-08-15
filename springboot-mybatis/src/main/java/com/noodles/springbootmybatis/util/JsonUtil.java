package com.noodles.springbootmybatis.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 文件名：JsonUtil.java
 * 描述：
 * 作者：KJ00019
 * 日期：2017年6月15日下午4:09:17
 */
public class JsonUtil {


	public static String toJson(Object obj) {
		// disableHtmlEscaping:特殊字符不转义,统一日期格式
		Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj);
	}


	public static String toJsonFormatDay(Object obj) {
		// disableHtmlEscaping:特殊字符不转义,统一日期格式
		Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(obj);
	}


	public static String toJson_null(Object obj) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
			@Override
			public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
				if (src == src.longValue())
					return new JsonPrimitive(src.longValue());
				return new JsonPrimitive(src);
			}
		}).serializeNulls().create();
		return gson.toJson(obj);
	}


	public static <T> T fromJson(String str, Type type) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(str, type);
	}


	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(str, type);
	}

}
