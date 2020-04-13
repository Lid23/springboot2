package com.noodles.date.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @filename DateUtils
 * @description 日期工具类
 * @autor noodles
 * @date 2019/7/23 14:34
 */
public class DateUtils {

	/**默认短日期格式 yyyy-MM-dd*/
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
	/**默认日期时间格式 yyyy-MM-dd HH:mm:ss*/
	public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**默认时间格式 HH:mm:ss*/
	public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";
	/**默认日期短格式 yyyyMMdd*/
	public static final String DATE_DEFAULT_SHORT_FORMAT = "yyyyMMdd";
	/**默认日期短格式 yyyy/MM/dd*/
	public static final String DATE_DEFAULT_SHORT_DIVIDE_FORMAT = "yyyy/MM/dd";
	/**默认长日期格式 yyyyMMddHHmmss*/
	public static final String DATE_DEFAULT_FULL_FORMAT = "yyyyMMddHHmmss";
	/**默认长日期格式 yyyyMMddHHmmssSSS*/
	public static final String DATE_DEFAULT_LONG_FORMAT = "yyyyMMddHHmmssSSS";
	/**默认日期短格式 HHmmss*/
	public static final String TIME_DEFAULT_SHORT_FORMAT = "HHmmss";

	/**
	 * 私有构造器
	 * @return
	 * @author noodles
	 * @date 2019/8/6 14:56
	 */
	private DateUtils() {

	}

	/**
	 * 将style格式的日期字符串转化为日期类型
	 * @param s 日期字符串
	 * @param style 格式
	 * @return java.util.Date
	 * @author noodles
	 * @date 2019/7/23 14:42
	 */
	public static Date strToDate(String s, String style) {
		if (null == s || s.length() == 0) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(style);
		try {
			Date date = df.parse(s);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将date转化为style格式的日期字符串
	 * @param date 日期
	 * @param style 格式
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/7/23 14:42
	 */
	public static String dateToStr(Date date, String style) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(style);
		try {
			String str = df.format(date);
			return str;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 日期加减
	 * @param time 日期
	 * @param num 加减数量
	 * @return java.util.Date
	 * @author 巫威
	 * @date 2019/8/8 17:15
	 */
	public static Date dateAddDays(Date time, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.DATE, num);
		Date newTime = calendar.getTime();
		return newTime;
	}

	/**
	 * 获取两个日期相差的天数
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return int
	 * @author 罗文
	 * @date 2019/8/12 18:02
	 */
	public static int daysBetween(Date startDate, Date endDate) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(startDate);
		caled.setTime(endDate);
		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

	/**
	 *生成时间随机数
	 * @return java.lang.String
	 * @author noodles
	 * @date 2019/12/13 11:32
	 */
	public static String getDateRandom() {
		String s = (new SimpleDateFormat(DATE_DEFAULT_LONG_FORMAT)).format(new Date());
		return s;
	}

	/**
	 * 时间戳转日期格式
	 * @param timestamp 时间戳
	 * @return java.lang.String
	 * @author noodles
	 * @date 2020/1/6 16:40
	 */
	public static String timestampToDateStr(long timestamp) {
		Timestamp t = new Timestamp(timestamp);
		try {
			Date date = new Date(t.getTime());
			return dateToStr(date, DATETIME_DEFAULT_FORMAT);
		} catch (Exception ex) {
			return null;
		}
	}

}
