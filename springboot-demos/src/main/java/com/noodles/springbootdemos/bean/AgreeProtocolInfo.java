package com.noodles.springbootdemos.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @filename AgreeProtocolInfo
 * @description AgreeProtocolInfo
 * @author 巫威
 * @date 2020/4/10 18:28
 */

@Entity
@Table(name = "AgreeProtocolInfo")
public class AgreeProtocolInfo implements Serializable {

	private static final long serialVersionUID = -6639842686325103108L;

	@Id
	@Column
	private Long agreeId;
	@Column
	private String custNo;
	@Column
	private Date createdDate;

	public Long getAgreeId() {
		return agreeId;
	}

	public void setAgreeId(Long agreeId) {
		this.agreeId = agreeId;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
