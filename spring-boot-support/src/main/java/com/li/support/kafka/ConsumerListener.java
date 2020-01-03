package com.li.support.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsumerListener {
    private Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(topics = {"testTopic"})
    public void onMessage(ConsumerRecord<?,?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record);
        if (kafkaMessage.isPresent()) {
            Object message = record.value();
            logger.info("kafka监听到的消息：" + message);
            logger.info("record :" + record);
        }
    }
}
