package com.noodles.dubboconsumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.noodles.dubboapi.service.IDubboProviderService;
import com.noodles.dubboprovider.DubboConsumerApplication;

@SpringBootTest(classes = DubboConsumerApplication.class)
@RunWith(SpringRunner.class)
public class DubboConsumerTest {

	@Reference
	private IDubboProviderService providerService;

	@Test
	public void dubboConsumerTest() {
		System.out.println("dubboConsumerTest");
		providerService.providerMethod("Hello World");
	}

}
