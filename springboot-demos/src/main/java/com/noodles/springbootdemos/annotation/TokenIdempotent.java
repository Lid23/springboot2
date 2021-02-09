package com.noodles.springbootdemos.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @filename TokenIdempotent
 * @description 自定义注解类，有该注解的需要验证token是否有效
 * @author 巫威
 * @date 2021/2/9 9:52
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, TYPE})
public @interface TokenIdempotent {

}
