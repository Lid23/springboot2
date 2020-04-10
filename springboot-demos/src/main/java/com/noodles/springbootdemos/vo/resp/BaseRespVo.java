package com.noodles.springbootdemos.vo.resp;

import java.io.Serializable;

/**
 * 公共响应Vo
 * @filename BaseRespVo
 * @author 巫威
 * @date 2020/4/10 14:11
 */
public class BaseRespVo<T> implements Serializable {

	private static final long serialVersionUID = -4116539579442322719L;

	/**服务返回成功编码*/
	private static final String RESULT_CODE_SUCC = "000000";

	/**返回消息-请求成功*/
	private static final String RESULT_MSG_SUCC = "请求成功";

	/** 返回码 */
	private String code = "";
	/** 返回信息 */
	private String message = "";
	/** 返回数据 */
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 无参构造器
	 * @author noodles
	 * @date 2019/5/15 10:05
	 */
	public BaseRespVo() {
	}

	/**
	 * 构造器
	 * @param data 返回数据
	 * @author noodles
	 * @date 2019/5/15 10:06
	 */
	public BaseRespVo(T data) {
		this.code = this.RESULT_CODE_SUCC;
		this.message = this.RESULT_MSG_SUCC;
		this.data = data;
	}

	/**
	 * 构造器
	 * @param code 返回码
	 * @param message 返回信息
	 * @author noodles
	 * @date 2019/5/15 10:05
	 */
	public BaseRespVo(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
