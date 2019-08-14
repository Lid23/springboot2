package com.noodles.springbootmybatis.bean;

import java.io.Serializable;

/**
 * @filename ScoreBean
 * @description Score
 * @author 巫威
 * @date 2019-08-14 21:57
 */
public class ScoreBean implements Serializable {
	private static final long serialVersionUID = -8985548953756030135L;

	private String sno;

	private String cno;

	private float degreee;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public float getDegreee() {
		return degreee;
	}

	public void setDegreee(float degreee) {
		this.degreee = degreee;
	}
}
