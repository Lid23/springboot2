package com.noodles.springbootdemos.bean;

import java.io.Serializable;

/**
 * @filename TokenUser
 * @description Token测试user
 * @author 巫威
 * @date 2021/2/9 11:13
 */
public class TokenUser implements Serializable {

	private static final long serialVersionUID = 153080836980585027L;

	private String name;

	private Integer age;

	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
