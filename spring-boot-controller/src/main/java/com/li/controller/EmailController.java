package com.li.controller;

import com.li.support.dto.RestResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "email controller")
@RestController
@RequestMapping("api/email/")
public class EmailController {

    private static Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @ApiOperation(value = "发送Email")
    @RequestMapping(value = "sendMail", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResultDto sendMail(String senderMailAddress) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(senderMailAddress);
            message.setSubject("测试邮件");
            message.setText("发送一份简单的邮件");
            jms.send(message);
            logger.info("邮件发送{}成功", senderMailAddress);
            return RestResultDto.newSuccess();
        } catch (Exception e) {
            logger.info("邮件发送失败");
            return RestResultDto.newFail(e.getMessage());
        }
    }
}
