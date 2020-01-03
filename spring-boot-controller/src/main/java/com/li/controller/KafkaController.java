package com.li.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "kafka接口")
@RestController
@RequestMapping(value = "cloud/kafka")
public class KafkaController {
    @Resource
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "send", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean send(@ApiParam(required = true) @RequestParam String message) {
        kafkaTemplate.send("testTopic", "message");
        return true;
    }
}
