package com.noodles.trace.utils;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @filename TraceUtils
 * @description 链路追踪工具类
 * @autor noodles
 * @date 2019/8/16 14:20
 */
public class TraceUtils {

	/**链路traceId*/
	public static final String TRACE_ID_KEY = "traceId";

	/**
	 * 私有构造器
	 * @return
	 * @author noodles
	 * @date 2019/8/16 14:22
	 */
	private TraceUtils() {

	}

	/**
	 * 获取traceId
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/16 14:22
	 */
	public static String getTraceId() {
		return String.format("traceId:%s", UUID.randomUUID().toString());
	}

	/**
	 * 判断是否存在TraceId，若不存在返回空
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/8/16 14:22
	 */
	public static String findTraceId() {
		String traceId = "";
		if (StringUtils.isNotEmpty(MDC.get(TraceUtils.TRACE_ID_KEY))) {
			traceId = MDC.get(TraceUtils.TRACE_ID_KEY);
		}
		return traceId;
	}

}
