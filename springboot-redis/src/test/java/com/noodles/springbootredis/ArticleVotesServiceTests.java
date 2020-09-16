package com.noodles.springbootredis;

import java.util.ArrayList;
import java.util.Arrays;
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
	public void testPostArticle() {
		List<Long> articleIds = Arrays.asList(new Long[] { 92617L, 100408L, 83729L, 100635L, 100716L });

		/**用户u0001发不了一篇文章*/
		String user = "u0001";
		String title = "地球是平的";
		String link = "www.baidu.com";
		for (Long articleId1 : articleIds) {
			Long articleId = iArticleVoteService.postArticle(articleId1, user, title, link);
			System.out.println("文章Id为：" + articleId);
		}
	}

	/**
	 * 投票
	 * @author 巫威
	 * @date 2020/9/15 17:40
	 */
	@Test
	public void testArticleVote() {
		/**用户u0001发不了一篇文章*/
		String user = "u0002";
		String article = "article:1";
		iArticleVoteService.articleVote(user, article);
		System.out.println("投票完成");
	}

	@Test
	public void testGetArticles() {
		/**用户u0001发不了一篇文章*/
		String user = "u0002";
		String article = "article:1";
		List<Map<Object, Object>> list = iArticleVoteService.getArticles(1, "score:");
		System.out.println(list);
	}

	@Test
	public void testAddRemoveGroups() {
		List<Long> articleIds = Arrays.asList(new Long[] { 92617L, 100408L, 83729L });
		List<String> addGroups = Arrays.asList(new String[] { "programing" });
		List<String> removeGroups = new ArrayList<>();
		for (Long articleId : articleIds) {
			iArticleVoteService.addRemoveGroups(articleId, addGroups, removeGroups);
		}
		System.out.println("群组分配完成");
	}

	@Test
	public void testGetGroupArticles() {
		String group = "programing";
		List<Map<Object, Object>> list = iArticleVoteService.getGroupArticles(group, 1, "score:");
		System.out.println(list);
	}

}