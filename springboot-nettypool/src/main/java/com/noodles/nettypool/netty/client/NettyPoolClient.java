package com.noodles.nettypool.netty.client;

/**
 * @filename NettyPoolClient
 * @description NettyPoolClient
 * @author 巫威
 * @date 2019/12/10 9:07
 */

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

public class NettyPoolClient {

	final int maxConnections = 10;
	final EventLoopGroup group = new NioEventLoopGroup();
	final Bootstrap strap = new Bootstrap();
	public static InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8000);
	public static ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap;
	public void build() throws Exception {
		strap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.SO_KEEPALIVE, true);

		poolMap = new AbstractChannelPoolMap<InetSocketAddress, SimpleChannelPool>() {
			@Override
			protected SimpleChannelPool newPool(InetSocketAddress key) {
				return new FixedChannelPool(strap.remoteAddress(key), new NettyChannelPoolHandler(), maxConnections);
			}
		};
	}

	public static void initClient() throws Exception{
		NettyPoolClient client = new NettyPoolClient();
		client.build();

		/**创建10个连接发送消息*/
		final String ECHO_REQ = "Hello Netty.$_";
		for (int i = 0; i < 100; i++) {
			final SimpleChannelPool pool = client.poolMap.get(client.addr);
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

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		NettyPoolClient client = new NettyPoolClient();
		client.build();
		final String ECHO_REQ = "Hello Netty.$_";
		for (int i = 0; i < 100; i++) {
			final SimpleChannelPool pool = client.poolMap.get(client.addr);
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
}

