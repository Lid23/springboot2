package com.noodles.springbootredis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.noodles.springbootredis.service.IArticleVoteService;

/**
 * @filename ArticleVoteServiceImpl
 * @description 文章投票实例redis实现
 * @author 巫威
 * @date 2020/9/15 10:39
 */
@Service
public class ArticleVoteServiceImpl implements IArticleVoteService {

	/**每周秒数*/
	public final static int ONE_WEEK_SECONDS = 86400 * 7;
	/**投票得分数*/
	public final static int VOTE_SCORE = 432;
	/**每页数据条数*/
	public final static int ARTICLE_PER_PAGE = 10;

	private static final Logger log = LoggerFactory.getLogger(ArticleVoteServiceImpl.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * @说明：
	 * 1.使用一个散列来存储文章信息（标题，网址，作者，发布时间，投票数量等信息）
	 * 2.使用两个有序集合来有序地存储文章：文章ID-文章的发布时间，文章ID-文章的评分；这样既可以根据
	 * 文章发布的先后顺序来展示文章，又可以根据文章评分的高低来展示文章
	 * 3.为了防止用户对同一篇文章进行多次投票，网站需要为每篇文章记录一个已投票用户名单，为此，将为每篇文章创建一个集合
	 * */

	/**
	 * @实例 hash 记录文章信息
	 * article:92617
	 * title : Go to statement considered harmful
	 * link : http://goo.gl/kZUSu
	 * poster : user:83721
	 * time : 1331382699.33
	 * votes : 58
	 * **/

	/**
	 * 为文章投票
	 * @param user 投票用户
	 * @param article 投票的文章
	 * @return void
	 * @author 巫威
	 * @date 2020/9/15 14:10
	 */
	@Override
	public void articleVote(String user, String article) {
		if (StringUtils.isEmpty(user) || StringUtils.isEmpty(article)) {
			throw new IllegalArgumentException("参数不对");
		}

		long cutoff = System.currentTimeMillis() / 1000 - ONE_WEEK_SECONDS;

		if (stringRedisTemplate.opsForZSet().score("time:", article) < cutoff) {
			/**检查是否还可以对文章进行投票*/
			return;
		}
		String articleId = article.split(":")[1];
		if (stringRedisTemplate.opsForSet().add("voted:".concat(articleId), user) > 0) {
			/**如果用户是第一次为这篇文章投票，那么增加这篇文章的投票数量和评分*/
			stringRedisTemplate.opsForZSet().incrementScore("score:", article, VOTE_SCORE);
			stringRedisTemplate.opsForHash().increment(article, "votes", 1);
		}
	}

	/**
	 * 发布新文章
	 * @param user 用户
	 * @param title 文章标题
	 * @param link 文章链接
	 * @return 文章Id
	 * @author 巫威
	 * @date 2020/9/15 14:15
	 */
	@Override
	public Long postArticle(String user, String title, String link) {
		/**生成一个新的文章Id*/
		Long articleId = stringRedisTemplate.opsForValue().increment("article:");

		String voted = "voted:" + articleId;
		/**将发布文章的用户添加到文章的已投票用户名单里面，然后将这个名单的过期时间设置为一周*/
		stringRedisTemplate.opsForSet().add(voted, user);
		stringRedisTemplate.expire(voted, ONE_WEEK_SECONDS, TimeUnit.SECONDS);

		Long now = System.currentTimeMillis() / 1000;

		/**将文章信息存储到一个散列里面*/
		String article = "article:" + articleId;
		redisTemplate.opsForHash().putAll(article, new HashMap<String, Object>() {
			{
				put("title", title);
				put("link", link);
				put("poster", user);
				put("time", now);
				put("votes", 1);
			}
		});

		/**将文章添加到根据发布时间排序的有序集合和根据评分排序的有序集合里面*/
		stringRedisTemplate.opsForZSet().add("time:", article, now);
		stringRedisTemplate.opsForZSet().add("score:", article, now + VOTE_SCORE);
		return articleId;
	}

	/**
	 * 查询文章列表
	 * @param page 页数
	 * @param order 排序规则
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @author 巫威
	 * @date 2020/9/15 15:01
	 */
	@Override
	public List<Map<Object, Object>> getArticles(int page, String order) {
		int start = (page - 1) * ARTICLE_PER_PAGE;
		int end = start + ARTICLE_PER_PAGE - 1;

		Set<String> articles = stringRedisTemplate.opsForZSet().reverseRange(order, start, end);
		List<Map<Object, Object>> articleList = new ArrayList<>();
		for (String article : articles) {
			Map<Object, Object> articleInfo = stringRedisTemplate.opsForHash().entries(article);
			articleList.add(articleInfo);

		}
		return articleList;
	}
}
