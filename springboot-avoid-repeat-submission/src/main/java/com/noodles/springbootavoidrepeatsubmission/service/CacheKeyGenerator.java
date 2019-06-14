package com.noodles.springbootavoidrepeatsubmission.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
* @Description: key生成器
* @return:
* @Author: Eric
* @Date: 2019/6/14 16:11
*/
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
