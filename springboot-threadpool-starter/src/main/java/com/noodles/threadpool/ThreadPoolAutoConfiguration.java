package com.noodles.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @filename ThreadPoolAutoConfiguration
 * @description 自动配置类
 * @author 巫威
 * @date 2021/2/8 9:53
 */
@Configuration
public class ThreadPoolAutoConfiguration {

	@Bean
	@ConditionalOnClass(ThreadPoolExecutor.class)
	public ThreadPoolExecutor myThreadPool() {
		return new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
	}
}
