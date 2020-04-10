package com.noodles.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

import sun.misc.JavaLangAccess;
import sun.misc.SharedSecrets;

/**
 * 日志
 * @filename MySlf4j
 * @author 巫威
 * @date 2020/4/10 17:04
 */
public class MySlf4j {

	/**空数组*/
	private static final Object[] EMPTY_ARRAY = new Object[] {};
	/**全类名*/
	private static final String FQCN = MySlf4j.class.getName();

	/**
	 * 获取栈中类信息
	 * @param stackDepth
	 * @return org.slf4j.spi.LocationAwareLogger
	 * @author 巫威
	 * @date 2020/4/10 17:05
	 */
	private static LocationAwareLogger getLocationAwareLogger(final int stackDepth) {
		/**通过堆栈信息获取调用当前方法的类名和方法名*/
		JavaLangAccess access = SharedSecrets.getJavaLangAccess();
		Throwable throwable = new Throwable();
		StackTraceElement frame = access.getStackTraceElement(throwable, stackDepth);
		return (LocationAwareLogger) LoggerFactory.getLogger(frame.getClassName());
	}

	/**
	 * 封装Debug级别日志
	 * @param msg
	 * @param arguments
	 * @return void
	 * @author 巫威
	 * @date 2020/4/10 17:05
	 */
	public static void textDebug(String msg, Object... arguments) {
		if (arguments != null && arguments.length > 0) {
			MessageFormat temp = new MessageFormat(msg);
			msg = temp.format(arguments);
		}
		getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);
	}

	/**
	 * 封装Info级别日志
	 * @param msg
	 * @param arguments
	 * @return void
	 * @author 巫威
	 * @date 2020/4/10 17:05
	 */
	public static void textInfo(String msg, Object... arguments) {
		if (arguments != null && arguments.length > 0) {
			MessageFormat temp = new MessageFormat(msg);
			msg = temp.format(arguments);
		}
		getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, null);
	}

	/**
	 * 封装Warn级别日志
	 * @param msg
	 * @param arguments
	 * @return void
	 * @author 巫威
	 * @date 2020/4/10 17:05
	 */
	public static void textWarn(String msg, Object... arguments) {
		if (arguments != null && arguments.length > 0) {
			MessageFormat temp = new MessageFormat(msg);
			msg = temp.format(arguments);
		}
		getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, null);
	}

	/**
	 * 封装Error级别日志
	 * @param msg
	 * @param arguments
	 * @return void
	 * @author 巫威
	 * @date 2020/4/10 17:05
	 */
	public static void textError(String msg, Object... arguments) {
		if (arguments != null && arguments.length > 0) {
			MessageFormat temp = new MessageFormat(msg);
			msg = temp.format(arguments);
		}
		getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);
	}

	/**
	 * 异常堆栈转字符串
	 * @param e
	 * @return java.lang.String
	 * @author 巫威
	 * @date 2020/4/10 17:05
	 */
	public static String ExceptionToString(Exception e) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			if (e == null) {
				return "无具体异常信息";
			}
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		} catch (Exception ex) {
			MySlf4j.textError("异常堆栈转字符串异常", ex);
			return "";
		} finally {
			sw.flush();
			pw.flush();
			pw.close();
		}

	}
}
