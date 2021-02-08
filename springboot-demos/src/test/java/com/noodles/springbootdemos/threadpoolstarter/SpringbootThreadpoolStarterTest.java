package com.noodles.springbootdemos.threadpoolstarter;

import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.noodles.springbootdemos.SpringbootDemosApplication;

/**
 * @filename SpringbootThreadpoolStarterTest
 * @description 测试springboot自定义的starter
 * @author 巫威
 * @date 2021/2/8 9:59
 */
@SpringBootTest(classes = SpringbootDemosApplication.class)
@RunWith(SpringRunner.class)
public class SpringbootThreadpoolStarterTest {

	@Autowired
	private ThreadPoolExecutor myThreadPool;


	@Test
	public void testThreadpoolStarter(){
		System.out.println("CorePoolSize -> " + myThreadPool.getCorePoolSize());
	}
}
