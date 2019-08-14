package com.noodles.springbootmybatis.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @filename TeacherBean
 * @description Teacher
 * @author 巫威
 * @date 2019-08-14 21:59
 */
public class TeacherBean implements Serializable {

	private static final long serialVersionUID = -6015213057482148631L;

	private String tno;

	private String tname;

	private String tsex;

	private Date tbirthy;

	private String prof;

	private String depary;

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public Date getTbirthy() {
		return tbirthy;
	}

	public void setTbirthy(Date tbirthy) {
		this.tbirthy = tbirthy;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public String getDepary() {
		return depary;
	}

	public void setDepary(String depary) {
		this.depary = depary;
	}
}
