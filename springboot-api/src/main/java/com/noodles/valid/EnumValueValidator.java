package com.noodles.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @filename EnumValueValidator
 * @description 字符串或数字指定值校验
 * @author noodles
 * @date 2019/12/18 15:19
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

	/**字符串数组*/
	private String[] strValues;

	/**数字数组*/
	private int[] intValues;

	@Override
	public void initialize(EnumValue constraintAnnotation) {
		strValues = constraintAnnotation.strValues();
		intValues = constraintAnnotation.intValues();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value instanceof String) {
			for (String s : strValues) {
				if (s.equals(value)) {
					return true;
				}
			}
		} else if (value instanceof Integer) {
			for (Integer s : intValues) {
				if (s == value) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;

	}
}