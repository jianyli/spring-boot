package com.li.service.impl;

import com.google.common.collect.Maps;
import com.li.service.IKaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class KaptchaServiceImpl implements IKaptchaService {
    static final String REDIS_CODE = "verifyCode";
    @Value("${kaptcha.timeout}")
    private Integer timeout;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String, Object> createToken(String kaptcha) {
        HashOperations<String,String,String> operations = redisTemplate.opsForHash();
        String cToken = UUID.randomUUID().toString();
        operations.put(REDIS_CODE, cToken, kaptcha);
        redisTemplate.expire(REDIS_CODE,timeout, TimeUnit.SECONDS);
        Map<String,Object> map = Maps.newHashMap();
        map.put("cToken", cToken);
        map.put("expire",timeout);
        return map;
    }

    @Override
    public Boolean checkCode(String rightCode, String verityCode) {
        //TODO 按照参数核查验证码
        return null;
    }
}
