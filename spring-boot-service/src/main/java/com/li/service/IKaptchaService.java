package com.li.service;

import java.util.Map;

public interface IKaptchaService {
    Map<String, Object> createToken(String kaptcha);
    Boolean checkCode(String rightCode, String verityCode); //核查验证码是否正确
}
