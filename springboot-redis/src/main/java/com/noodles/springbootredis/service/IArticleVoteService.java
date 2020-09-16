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
	 * @param articleId
	 * @param user
	 * @param title
	 * @param link
	 * @return 文章Id
	 * @author 巫威
	 * @date 2020/9/15 14:15
	 */
	public Long postArticle(Long articleId, String user, String title, String link);

	/**
	 * 查询文章列表
	 * @param page
	 * @param order
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @author 巫威
	 * @date 2020/9/15 15:00
	 */
	public List<Map<Object, Object>> getArticles(int page, String order);

	/***
	 * 群组功能，把文章添加到群组或者从群组中移除文章
	 * @param articleId 文章id
	 * @param addGroups 添加的群组list
	 * @param removeGroup  移除文章的群组list
	 * @author 巫威 
	 * @date 2020-09-15 21:22 
	 */
	public void addRemoveGroups(Long articleId, List<String> addGroups, List<String> removeGroup);

	/**
	 * 查询群组文章列表
	 * @param group
	 * @param page
	 * @param order
	 * @return java.util.List<java.util.Map<java.lang.Object,java.lang.Object>>
	 * @author 巫威
	 * @date 2020/9/16 9:45
	 */
	public List<Map<Object, Object>> getGroupArticles(String group, int page, String order);

}
