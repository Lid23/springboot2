package com.noodles.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @filename TestConsumer
 * @description 消费者
 * @author 巫威
 * @date 2019/8/21 10:21
 */
@Component
public class TestConsumer {

	@KafkaListener(topics = "test_topic")
	public void listen(ConsumerRecord<?, ?> record) {
		System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
	}
}
