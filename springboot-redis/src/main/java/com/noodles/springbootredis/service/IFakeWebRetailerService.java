package com.noodles.springbootredis.service;

/**
 * @filename IFakeWebRetailerService
 * @description Redis构建web应用实例接口
 * @author 巫威
 * @date 2020/9/16 15:15
 */
public interface IFakeWebRetailerService {

	/**
	 * 使用Redis Hash来存储登陆cookie令牌和已登录用户之间的映射
	 * 检查用户是否已经登陆，根据给定的令牌来查找与之对应的用户，在
	 * 用户已经登陆的情况下，返回用户Id
	 * @param token
	 * @return java.lang.String
	 * @author 巫威
	 * @date 2020/9/16 15:42
	 */
	public String checkToken(String token);

	/**
	 * 更新用户令牌
	 * 用户每次浏览页面的时候，程序都会对用户存储在登陆Hash里面的信息进行更新，并将用户
	 * 的令牌和当前时间戳添加到记录最近登陆用户的有序集合里面；如果用户正在浏览的是一个商品页面
	 * 那么还会将这个商品添加到记录这个用户最近浏览过的商品的有序集合里面
	 * @param token
	 * @param user
	 * @param item
	 * @return void
	 * @author 巫威
	 * @date 2020/9/16 15:48
	 */
	public void updateToken(String token, String user, String item);

	/**
	 * 因为存储会话数据所需的内存会随着时间的推移而不断增加，所以我们需要定期清理旧的会话数据
	 * 为了限制会话数据的数量，只保存最新的1000万个会话，循环检查存储最近登陆令牌的有序集合的
	 * 大小，如果有序集合的大小超过了限制，那么就从有序集合里面移除最多100个最旧的令牌，并从记录
	 * 用户登陆信息的Hash里面，移除被删除令牌对应的用户信息，并对存储了这些用户最近浏览商品记录的
	 * 有序集合进行清理
	 * @author 巫威
	 * @date 2020/9/16 16:11
	 */
	public void cleanSessions();
}
