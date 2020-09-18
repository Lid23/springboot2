package com.noodles.springbootredis.service.impl;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.noodles.springbootredis.service.IFakeWebRetailerService;

/**
 * Redis实战 : Redis重新实现登陆cookie功能，采用token令牌的方式
 * @filename FakeWebRetailerServiceImpl
 * @description Redis构建Web应用实例接口实现
 * @author 巫威
 * @date 2020/9/16 15:15
 */
public class FakeWebRetailerServiceImpl implements IFakeWebRetailerService {

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

			/**获取需要移除的token，清理token的最近浏览记录和购物车记录*/
			Long endIndex = Math.min(size - SESSION_LIMIT, 100);
			Set<String> tokens = stringRedisTemplate.opsForZSet().range("recent:", 0, endIndex);

			Set<String> removeTokens = new HashSet<>();
			for(String token : tokens){
				removeTokens.add("viewed:" + token);
				removeTokens.add("cart:" + token);
			}

			stringRedisTemplate.delete(removeTokens);

			/**移除出登陆session*/
			stringRedisTemplate.opsForHash().delete("login:", tokens);
			/**删除最后一次访问时间的记录*/
			stringRedisTemplate.opsForZSet().remove("recent:", tokens);
		}
	}

	/**
	 * 定义购物车，每个用户的购物车都是一个散列，存储了商品ID月商品订购数量之间的映射
	 * @param sessionToken 用户token
	 * @param item 商品ID
	 * @param count 商品数量
	 * @author 巫威
	 * @date 2020/9/18 14:02
	 */
	@Override
	public void addToCart(String sessionToken, String item, int count) {
		if (count <= 0) {
			/**从购物车里移除指定的商品*/
			stringRedisTemplate.opsForHash().delete("cart:" + sessionToken, item);
		} else {
			/**将指定的商品添加到购物车*/
			stringRedisTemplate.opsForHash().put("cart:" + sessionToken, item, count);
		}
	}

	/**
	 * 网页缓存：
	 * 网站上的多数页面实际上并不会经常发生大的变化，一般情况下，网站只有账号设置，以往订单，购物车（结账信息）
	 * 以及其他少数几个页面才包含需要每次载入都要动态生成的内容。
	 * 缓存函数：对于一个不能被缓存的请求，函数将直接生成并返回页面，而对于可以被缓存的请求，函数首先会先尝试从
	 * 缓存里面取出并返回被缓存的页面，如果页面不存在，那么函数会生成页面并将其缓存在Redis里面5分钟，最后再将
	 * 页面返回给函数调用者
	 * @param request
	 * @return java.lang.String
	 * @author 巫威
	 * @date 2020/9/18 14:12
	 */
	@Override
	public String cacheRequest(HttpServletRequest request) {
		if(!canCache(request)){
			/**对于不能被缓存的请求，直接调用回调函数*/
			return callback(request);
		}

		/**将请求转换成一个简单的字符串键，方便以后进行查找*/
		String pageKey = "cache:" + hashRequest(request);

		/**尝试查找被缓存的页面*/
		String content = stringRedisTemplate.opsForValue().get(pageKey);
		if(StringUtils.isEmpty(content)){
			/**如果页面还没有被缓存，那么生成页面，并将新生成的页面放到缓存里面*/
			content = callback(request);
			stringRedisTemplate.opsForValue().set(pageKey, content, 300, TimeUnit.SECONDS);
		}
		return content;
	}

	/**
	 * 判断页面是否需要被缓存
	 * @param request
	 * @return java.lang.String
	 * @author 巫威
	 * @date 2020/9/18 14:18
	 */
	private boolean canCache(HttpServletRequest request) {
		// TODO
		return true;
	}

	private String callback(HttpServletRequest request) {
		// TODO
		return "callback";
	}

	private String hashRequest(HttpServletRequest request) {
		// TODO
		return request.getMethod() + request.hashCode();
	}
}
