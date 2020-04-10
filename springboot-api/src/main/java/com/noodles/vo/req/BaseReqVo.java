package com.noodles.vo.req;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 公共请求Vo
 * @filename BaseReqVo
 * @author 巫威
 * @date 2020/4/10 14:11
 */
public class BaseReqVo<T> implements Serializable {

	private static final long serialVersionUID = 6608849649151960909L;

	/**商户ID*/
	@NotBlank(message = "商户ID不能为空")
	private String merchantId;
	/**商户key*/
	@NotBlank(message = "商户key不能为空")
	private String merchantKey;
	/**所属系统*/
	@NotBlank(message = "所属系统不能为空")
	private String sourceSystem;
	/**请求IP*/
	@NotBlank(message = "请求IP不能为空")
	private String ip;
	/**请求参数*/
	@NotNull(message = "请求参数对象不能为空")
	@Valid
	private T data;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
