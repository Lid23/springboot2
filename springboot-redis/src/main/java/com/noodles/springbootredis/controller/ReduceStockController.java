package com.noodles.springbootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @filename ReduceStockController
 * @description 库存超卖问题示例Controller
 * @author 巫威
 * @date 2020/1/13 12:35
 */
@RestController
public class ReduceStockController {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	/**
	 * 非线程安全，jmeter模拟5*20个线程请求，100的库存不会清零
	 * @author 巫威
	 * @date 2020/1/13 12:42
	 */
	@RequestMapping("/reduce_stock")
	public void reduceStock(){
		/**查库存*/
		int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
		/**判断库存*/
		if(stock > 0){
			stringRedisTemplate.opsForValue().set("stock", (stock - 1) + "");
			System.out.println("扣减成功，库存stock:" + (stock - 1));
		} else {
			System.out.println("扣减失败，库存不足");	
		}

	}


	/**
	 * 加锁
	 * @author 巫威
	 * @date 2020/1/13 13:32
	 */
	@RequestMapping("/reduce_stock1")
	public void reduceStock1(){
		synchronized (stringRedisTemplate){
			/**查库存*/
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
			/**判断库存*/
			if(stock > 0){
				stringRedisTemplate.opsForValue().set("stock", (stock - 1) + "");
				System.out.println("扣减成功，库存stock:" + (stock - 1));
			} else {
				System.out.println("扣减失败，库存不足");
			}
		}
	}
	
}
