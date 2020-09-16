package com.noodles.springbootredis.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.noodles.springbootredis.service.IFakeWebRetailerService;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Redis实战 : Redis重新实现登陆cookie功能，采用token令牌的方式
 * @filename FakeWebRetailerService
 * @description Redis构建Web应用实例接口实现
 * @author 巫威
 * @date 2020/9/16 15:15
 */
public class FakeWebRetailerService implements IFakeWebRetailerService {

	public final static Boolean QUIT = false;

	/**限制会话数量*/
	public final static Long SESSION_LIMIT = 10000000L;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 使用Redis Hash来存储登陆cookie令牌和已登录用户之间的映射
	 * 检查用户是否已经登陆，根据给定的令牌来查找与之对应的用户，在
	 * 用户已经登陆的情况下，返回用户Id
	 * @param token
	 * @return java.lang.String
	 * @author 巫威
	 * @date 2020/9/16 15:43
	 */
	@Override
	public String checkToken(String token) {
		return (String) stringRedisTemplate.opsForHash().get("login:", token);
	}

	/**
	 * 更新用户令牌
	 * 用户每次浏览页面的时候，程序都会对用户存储在登陆Hash里面的信息进行更新，并将用户
	 * 的令牌和当前时间戳添加到记录最近登陆用户的有序集合里面；如果用户正在浏览的是一个商品页面
	 * 那么还会将这个商品添加到记录这个用户最近浏览过的商品的有序集合里面
	 * @param token
	 * @param user
	 * @param item
	 * @author 巫威
	 * @date 2020/9/16 15:53
	 */
	@Override
	public void updateToken(String token, String user, String item) {
		Long timestamp = System.currentTimeMillis();

		/**维持令牌和已登录用户之间的映射*/
		stringRedisTemplate.opsForHash().put("login:", token, user);

		/**记录令牌最后一次出现的时间*/
		stringRedisTemplate.opsForZSet().add("recent:", token, timestamp);

		if (!StringUtils.isEmpty(item)) {
			/**记录用户浏览过的商品*/
			stringRedisTemplate.opsForZSet().add("viewed:" + token, item, timestamp);

			/**移除旧的记录，只保留用户最近浏览过的25个商品*/
			stringRedisTemplate.opsForZSet().removeRange("viewed:" + token, 0, -26);
		}
	}

	/**
	 * 因为存储会话数据所需的内存会随着时间的推移而不断增加，所以我们需要定期清理旧的会话数据
	 * 为了限制会话数据的数量，只保存最新的1000万个会话，循环检查存储最近登陆令牌的有序集合的
	 * 大小，如果有序集合的大小超过了限制，那么就从有序集合里面移除最多100个最旧的令牌，并从记录
	 * 用户登陆信息的Hash里面，移除被删除令牌对应的用户信息，并对存储了这些用户最近浏览商品记录的
	 * 有序集合进行清理
	 * @author 巫威
	 * @date 2020/9/16 16:11
	 */
	@Override
	public void cleanSessions() {
		while (!QUIT) {
			/**找出目前已有令牌的数量*/
			Long size = stringRedisTemplate.opsForZSet().size("recent:");

			if (size < SESSION_LIMIT) {
				/**令牌数量未超过限制*/
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			/**获取需要移除的token*/
			Long endIndex = Math.min(size - SESSION_LIMIT, 100);
			Set<String> tokens = stringRedisTemplate.opsForZSet().range("recent:", 0, endIndex);

			Set<String> keyTokens = tokens.stream().map(token -> "viewed:" + token).collect(toSet());

			stringRedisTemplate.delete(keyTokens);
			stringRedisTemplate.opsForHash().delete("login:", tokens);
			stringRedisTemplate.opsForZSet().remove("recent:", tokens);
		}
	}
}
