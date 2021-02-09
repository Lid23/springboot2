package com.noodles.springbootdemos.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noodles.random.utils.RandomUtils;
import com.noodles.springbootdemos.annotation.TokenIdempotent;
import com.noodles.springbootdemos.bean.TokenUser;

/**
 * @filename TokenIdempotentController
 * @description Token幂等Controller
 * @author 巫威
 * @date 2021/2/9 11:06
 */
@RestController
@RequestMapping("/token")
public class TokenIdempotentController {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping("/getToken")
	public String getToken() {
		String accessToken = RandomUtils.getUuid();
		stringRedisTemplate.opsForValue().set(accessToken, accessToken, 10, TimeUnit.MINUTES);
		return accessToken;
	}

	@PostMapping("/saveTokenUser")
	@TokenIdempotent
	public Map<String, Object> saveTokenUser(@RequestBody TokenUser tokenUser) {
		Map<String, Object> result = new HashMap<>() ;
		// TODO save Users
		result.put("code", 0) ;
		result.put("message", "创建成功") ;
		return result ;
	}
}