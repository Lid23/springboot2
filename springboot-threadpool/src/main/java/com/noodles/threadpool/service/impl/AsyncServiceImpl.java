package com.noodles.threadpool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.noodles.threadpool.service.AsyncService;

/**
 * @filename AsyncServiceImpl
 * @description AsyncServiceImpl
 * @author 巫威
 * @date 2019/12/5 10:58
 */
@Service
public class AsyncServiceImpl implements AsyncService {

	private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

	@Override
	@Async("asyncServiceExecutor")
	public void executeAsync() {
		logger.info("start executeAsync");
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("end executeAsync");
	}
}
