package com.noodles.springbootredis;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.noodles.springbootredis.service.IArticleVoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleVotesServiceTests {

	@Autowired
	private IArticleVoteService iArticleVoteService;

	/**
	 * 发布文章测试
	 * @author 巫威
	 * @date 2020/9/15 15:15
	 */
	@Test
	public void testPostArticle(){
		/**用户u0001发不了一篇文章*/
		String user = "u0001";
		String title = "地球是平的";
		String link = "www.baidu.com";
		Long articleId = iArticleVoteService.postArticle(user, title, link);
		System.out.println("返回文章Id为：" + articleId);
	}


	/**
	 * 投票
	 * @author 巫威
	 * @date 2020/9/15 17:40
	 */
	@Test
	public void testArticleVote(){
		/**用户u0001发不了一篇文章*/
		String user = "u0002";
		String article = "article:1";
		iArticleVoteService.articleVote(user, article);
		System.out.println("投票完成");
	}

	@Test
	public void testGetArticles(){
		/**用户u0001发不了一篇文章*/
		String user = "u0002";
		String article = "article:1";
		List<Map<Object, Object>> list = iArticleVoteService.getArticles(1, "score:");
		System.out.println(list);
	}








}