package com.noodles.exception;

import java.io.Serializable;

/**
 * @filename NoodlesException
 * @description 统一异常处理常量类
 * @author noodles
 * @date 2019/3/26 15:19
 */
public class NoodlesException implements Serializable {

	private static final long serialVersionUID = -5005924612084198696L;

	/**系统异常*/
	public static final String ERR_100000 = "100000";
	/**服务不可用*/
	public static final String ERR_100001 = "100001";
	/**未获取到对应信息*/
	public static final String ERR_100002 = "100002";
	/**参数校验不通过*/
	public static final String ERR_100003 = "100003";
	/**商户不可用*/
	public static final String ERR_100004 = "100004";
	/**请求IP不可用或者被不被允许*/
	public static final String ERR_100005 = "100005";
	/**调用异常*/
	public static final String ERR_100006 = "100006";
	/**参数校验异常*/
	public static final String ERR_100007 = "100007";
	/**非法请求*/
	public static final String ERR_100008 = "100008";

}
