package com.li.support.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ProducerMessage {
    @Resource
    private KafkaTemplate kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("testTopic", "message:" + message);

    }
}
