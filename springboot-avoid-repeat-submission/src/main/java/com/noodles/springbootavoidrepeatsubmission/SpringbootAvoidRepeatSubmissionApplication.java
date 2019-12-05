package com.noodles.springbootavoidrepeatsubmission;

import com.noodles.springbootavoidrepeatsubmission.service.CacheKeyGenerator;
import com.noodles.springbootavoidrepeatsubmission.service.impl.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAvoidRepeatSubmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAvoidRepeatSubmissionApplication.class, args);
	}

	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}

}
