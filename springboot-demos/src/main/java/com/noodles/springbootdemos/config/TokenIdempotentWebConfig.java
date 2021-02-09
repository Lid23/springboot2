package com.noodles.springbootdemos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.noodles.springbootdemos.filter.TokenIdempotentCheck;

/**
 * @filename TokenWebConfig
 * @description Token拦截器配置
 * @author 巫威
 * @date 2021/2/9 10:57
 */
@Configuration
public class TokenIdempotentWebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor());
	}

	@Bean
	public HandlerInterceptor tokenInterceptor() {
		return new TokenIdempotentCheck();
	}
}
