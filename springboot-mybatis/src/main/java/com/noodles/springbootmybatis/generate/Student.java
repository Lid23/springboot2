package com.noodles.springbootmybatis.generate;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;

public class Student implements Serializable {
	private static final long serialVersionUID = 6553052389575796736L;

	/**
	 * 姓名 */
	private String name;

	/**
	 * 年龄 */
	private Integer age;

	/**
	 * 性别 */
	private Boolean sex;

	private void setName(String name) {
		this.name = name;
	}

	private String getName() {
		return name;
	}

	private void setAge(Integer age) {
		this.age = age;
	}

	private Integer getAge() {
		return age;
	}

	private void setSex(Boolean sex) {
		this.sex = sex;
	}

	private Boolean getSex() {
		return sex;
	}
}
