package com.noodles.springbootavoidrepeatsubmission.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 锁的注解
 * @program: springboot2
 * @description: 本地锁
 * @author: Eric
 * @create: 2019-06-14 15:45
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {

    /**
     * @author fly
     */
    String key() default "";

    /**
     * 过期时间 TODO 由于用的 guava 暂时就忽略这属性吧 集成 redis 需要用到
     * @author fly
     */
    int expire() default 5;
}
