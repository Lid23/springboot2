package com.noodles.dubboprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


@SpringBootApplication
public class DubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboConsumerApplication.class, args);
	}

	@Configuration
	@EnableDubbo(scanBasePackages = "com.noodles.dubboapi.service")
	@PropertySource("classpath:/dubbo.properties")
	//@ComponentScan(value = { "com.noodles.dubboapi.service" })
	public static class ConsumerConfiguration {

	}

}
