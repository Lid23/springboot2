package com.noodles.nettypool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noodles.nettypool.netty.client.NettyPoolClient;

import io.netty.channel.Channel;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

/**
 * @filename HelloAction
 * @description
 * @author 巫威
 * @date 2019/12/10 10:28
 */
@RestController
@RequestMapping(value = "/")
public class HelloAction {

	@GetMapping(value = "hello")
	public String hello(String msg){
		send("hello " + msg);
		return "hello " + msg;
	}

	void send(String msg){
		final String ECHO_REQ = msg + ".$_";
		final SimpleChannelPool pool = NettyPoolClient.poolMap.get(NettyPoolClient.addr);
		Future<Channel> f = pool.acquire();
		f.addListener((FutureListener<Channel>) f1 -> {
			if (f1.isSuccess()) {
				Channel ch = f1.getNow();
				ch.writeAndFlush(ECHO_REQ);
				pool.release(ch);
			}
		});
	}
}
