package com.noodles.nettypool.netty.server;

/**
 * @filename NettyServerHandler
 * @description NettyServerHandler
 * @author 巫威
 * @date 2019/12/10 9:06
 */

import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Object> {
	static AtomicInteger count = new AtomicInteger(1);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActived");
		super.channelActive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println(count.getAndIncrement() + ":" + body);
		ctx.writeAndFlush("Welcome to Netty.$_");
	}
}

