package com.noodles.valid;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @filename EnumValue
 * @description 字符串或数字指定值校验
 * @author noodles
 * @date 2019/12/18 15:19
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { EnumValueValidator.class })
public @interface EnumValue {

	/**默认错误消息*/
	String message()

			default "必须为指定值";

	/**字符串数组*/
	String[] strValues() default {};

	/**数字数组*/
	int[] intValues() default {};

	/**分组*/
	Class<?>[] groups() default {};

	/**负载*/
	Class<? extends Payload>[] payload() default {};

	/**指定多个时使用*/
	@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		/**获取数据数组*/
		EnumValue[] value();
	}
}