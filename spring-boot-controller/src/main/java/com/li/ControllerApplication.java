package com.li;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ControllerApplication {
    private static Logger logger = LoggerFactory.getLogger(ControllerApplication.class);
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ControllerApplication.class, args);

        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            logger.info("Spring Boot 使用profile为:{}", profile);
        }
    }

}
