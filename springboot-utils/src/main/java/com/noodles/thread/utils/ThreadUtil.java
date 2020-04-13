package com.noodles.thread.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @filename ThreadUtil
 * @description 线程工具类
 * @author noodles
 * @date 2020/3/17 15:19
 */
public class ThreadUtil {

	/** 获取批量违章结果控制表示 true:表示需要停止取结果操作，false:表示不需要控制 */
	public static AtomicBoolean controlGetBatchVRFlag = new AtomicBoolean(false);

	/**
	 * 功能描述 休眠多少秒
	 * @param second 秒为单位
	 * @author noodles
	 * @date 2020/3/17 16:51
	 */
	public static void sleepSecord(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (Exception e) {
		}
	}

	/**
	 * 功能描述 休眠多少毫秒
	 * @param second  以毫秒为单位
	 * @author noodles
	 * @date 2020/3/17 16:52
	 */
	public static void sleepMS(long second) {
		try {
			Thread.sleep(second);
		} catch (Exception e) {
		}
	}
}
