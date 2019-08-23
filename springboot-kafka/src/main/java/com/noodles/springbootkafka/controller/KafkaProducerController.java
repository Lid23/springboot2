package com.noodles.springbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @filename KafkaProducerController
 * @description 生产者Controller
 * @author 巫威
 * @date 2019/8/21 10:18
 */
@RestController
@RequestMapping("kafka")
public class KafkaProducerController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping("send")
	public String send(String msg) {
		for(int i=0; i<10; i++){
			kafkaTemplate.send("testTopic2", msg);
		}
		return "success";
	}

	@RequestMapping("send1")
	public String send1(String msg) {
		kafkaTemplate.send("test_topic_error", msg);
		return "success";
	}

}
