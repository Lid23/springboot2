package com.noodles.springbootdemos.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.noodles.springbootdemos.SpringbootDemosApplication;

/**
 * @filename RedisTest
 * @description 测试redis
 * @author 巫威
 * @date 2021/2/8 10:55
 */
@SpringBootTest(classes = SpringbootDemosApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedis(){
		stringRedisTemplate.opsForValue().set("name", "eric");
		System.out.println("done");
	}
}
