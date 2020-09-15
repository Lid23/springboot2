package com.noodles.springbootredis.service;

import java.util.List;
import java.util.Map;

/**
 * 《Redis实战》示例：
 * 网站根据文章的发布时间和文章获得的投票数量计算出一个评分，然后按照
 * 这个评分来决定如何排序和展示文章
 * @filename IArticleVoteService
 * @description 文章投票实例service
 * @author 巫威
 * @date 2020/9/15 10:39
 */
public interface IArticleVoteService {

	/**
	 * 投票函数
	 * @author 巫威
	 * @date 2020/9/15 10:57
	 */
	public void articleVote(String user, String article);

	/**
	 * 发布新文章
	 * @param user
	 * @param title
	 * @param link
	 * @return 文章Id
	 * @author 巫威
	 * @date 2020/9/15 14:15
	 */
	public Long postArticle(String user, String title, String link);

	/**
	 * 查询文章列表
	 * @param page
	 * @param order
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @author 巫威
	 * @date 2020/9/15 15:00
	 */
	public List<Map<Object, Object>> getArticles(int page, String order);

}
