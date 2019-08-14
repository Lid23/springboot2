package com.noodles.springbootmybatis.bean;

import java.io.Serializable;

/**
 * @filename GradeBean
 * @description Grade
 * @author 巫威
 * @date 2019-08-14 21:56
 */
public class GradeBean implements Serializable {
	private static final long serialVersionUID = 7670610021224350946L;

	private int low;

	private int upp;

	private String grade;

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getUpp() {
		return upp;
	}

	public void setUpp(int upp) {
		this.upp = upp;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
