package com.noodles.springbootmybatis.generate;

import java.io.Serializable;

/**
 * @filename FieldDescBean
 * @description 字段描述类
 * @author 巫威
 * @date 2019/8/15 16:53
 */
public class FieldDescBean implements Serializable {

	private static final long serialVersionUID = -5689669846802361175L;

	public FieldDescBean(String fieldName, String fieldType, String fieldComment) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldComment = fieldComment;
	}

	private String fieldName;

	private String fieldType;

	private String fieldComment;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldComment() {
		return fieldComment;
	}

	public void setFieldComment(String fieldComment) {
		this.fieldComment = fieldComment;
	}
}
