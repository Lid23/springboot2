package com.noodles.dubboprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


@SpringBootApplication
public class DubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProviderApplication.class, args);
	}

	@Configuration
	@EnableDubbo(scanBasePackages = "com.noodles.dubboapi.service")
	@PropertySource("classpath:/dubbo.properties")
	public static class ProviderConfiguration {
		@Bean
		public ProviderConfig providerConfig() {
			ProviderConfig providerConfig = new ProviderConfig();
			providerConfig.setTimeout(3000);
			return providerConfig;
		}
	}

}
