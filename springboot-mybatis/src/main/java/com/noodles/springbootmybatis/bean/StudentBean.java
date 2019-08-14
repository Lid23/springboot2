package com.noodles.springbootmybatis.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @filename StudentBean
 * @description 学生
 * @author 巫威
 * @date 2019-08-14 21:43
 */

@Entity
@Table(name = "Student")
public class StudentBean implements Serializable {
	private static final long serialVersionUID = -346643669532878744L;


	@Id
	@Column
	private String sno;
	@Column
	private String sname;
	@Column
	private String ssex;
	@Column
	private Date sbirthday;
	@Column
	private String sclass;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public Date getSbirthday() {
		return sbirthday;
	}

	public void setSbirthday(Date sbirthday) {
		this.sbirthday = sbirthday;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
}
