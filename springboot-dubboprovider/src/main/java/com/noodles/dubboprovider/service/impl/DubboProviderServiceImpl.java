package com.noodles.dubboprovider.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.noodles.dubboapi.service.IDubboProviderService;

@Service
public class DubboProviderServiceImpl implements IDubboProviderService {

	private static final Logger log = LoggerFactory.getLogger(DubboProviderServiceImpl.class);

	@Override
	public String providerMethod(String msg) {
		log.info("[providerMethod msg] - [{}]", msg);
		return "providerMethod:" + msg;
	}
}
