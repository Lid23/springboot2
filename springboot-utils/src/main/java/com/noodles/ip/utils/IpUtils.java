package com.noodles.ip.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ipg工具类
 * @filename IpUtils
 * @author 巫威
 * @date 2019/8/9 14:05
 */
public class IpUtils {

	/**
	 * 获取本机服务期ip
	 * @param
	 * @return java.lang.String
	 * @author 巫威
	 * @date 2019/8/9 14:06
	 */
	public static String getServerIpAddr() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

}
