package com.noodles.nettypool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.noodles.nettypool.netty.client.NettyPoolClient;

@SpringBootApplication
public class NettyPoolApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NettyPoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		NettyPoolClient.initClient();
	}
}
