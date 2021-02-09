package com.noodles.springbootdemos.filter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.noodles.json.utils.JsonUtils;
import com.noodles.logback.MySlf4j;
import com.noodles.springbootdemos.annotation.TokenIdempotent;

/**
 * @filename TokenIdempotentCheck
 * @description Token拦截器
 * @author 巫威
 * @date 2021/2/9 10:05
 */
public class TokenIdempotentCheck implements HandlerInterceptor {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Class<?> clazz = method.getClass();
			if (clazz.isAnnotationPresent(TokenIdempotent.class)) {
				if (!checkToken(request)) {
					failure(response);
					return false;
				}
			} else {
				if (method.isAnnotationPresent(TokenIdempotent.class)) {
					if (!checkToken(request)) {
						failure(response);
						return false;
					}
				}
			}
		}

		return true;
	}

	private void failure(HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", -1);
		resultMap.put("message", "重复提交");
		response.getWriter().write(JsonUtils.toJson(resultMap));
	}

	private boolean checkToken(HttpServletRequest request) {
		MySlf4j.textInfo("验证Token");
		String token = request.getParameter("access-token") ;
		if(StringUtils.isBlank(token)) {
			token = request.getHeader("access-token");
		}
		MySlf4j.textInfo("获取Token -> {0}", token);
		if(StringUtils.isBlank(token)){
			return false;
		}
		boolean exists = stringRedisTemplate.hasKey(token) ;
		if (!exists) {
			return false ;
		}
		return stringRedisTemplate.delete(token) ;
	}
}
