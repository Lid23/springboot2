package com.noodles.springbootmybatis.bean;

import java.io.Serializable;

/**
 * @filename CourseBean
 * @description 课程
 * @author 巫威
 * @date 2019-08-14 21:55
 */
public class CourseBean implements Serializable {
	private static final long serialVersionUID = -5522178342615520368L;

	private String cno;

	private String cname;

	private String tno;

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}
}
