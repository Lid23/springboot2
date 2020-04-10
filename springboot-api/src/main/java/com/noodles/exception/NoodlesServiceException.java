package com.noodles.exception;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @filename public class NoodlesServiceException extends RuntimeException {
 * @description 征信服务自定义异常处理类
 * @author noodles
 * @date 2019/3/26 15:33
 */
public class NoodlesServiceException extends RuntimeException {

	private static final long serialVersionUID = 852443720359797737L;

	/**错误信息集*/
	private static Map<String, String> errMsgMap = new HashMap<String, String>();

	/**错误代码*/
	private String code;

	static {
		errMsgMap.put(NoodlesException.ERR_100000, "系统异常");
		errMsgMap.put(NoodlesException.ERR_100001, "服务器运行时异常");
		errMsgMap.put(NoodlesException.ERR_100002, "未获取到对应信息");
		errMsgMap.put(NoodlesException.ERR_100003, "参数校验不通过");
		errMsgMap.put(NoodlesException.ERR_100004, "商户不可用");
		errMsgMap.put(NoodlesException.ERR_100005, "请求IP不可用或者被不被允许");
		errMsgMap.put(NoodlesException.ERR_100006, "调用异常");
		errMsgMap.put(NoodlesException.ERR_100007, "参数校验异常");
		errMsgMap.put(NoodlesException.ERR_100008, "非法请求");
	}

	/**
	 * 征信服务自定义异常构造方法
	 * @param code 异常代码
	 * @author noodles
	 * @date 2019/4/17 13:42
	 */
	public NoodlesServiceException(String code) {
		super(errMsgMap.get(code) == null ? ("未知代码:" + code) : errMsgMap.get(code));
		this.code = code;
	}

	/**
	 * 征信服务自定义异常构造方法
	 * @param code 异常代码
	 * @param message 异常信息
	 * @author noodles
	 * @date 2019/4/17 13:42
	 */
	public NoodlesServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * 征信服务自定义异常构造方法
	 * @param code 异常代码
	 * @param args 变量
	 * @return
	 * @author noodles
	 * @date 2019/4/17 13:43
	 */
	public NoodlesServiceException(String code, Object[] args) {
		super(errMsgMap.get(code) == null ? ("未知代码:" + code) : (MessageFormat.format(errMsgMap.get(code), args)));
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}