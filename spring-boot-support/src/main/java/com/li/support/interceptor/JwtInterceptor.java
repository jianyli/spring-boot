package com.li.support.interceptor;

import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import com.li.support.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            throw new ServiceException(ErrorCodeEnum.INVALID_CODE);
        }
        Claims claims = JwtUtil.checkToken(token);
        request.setAttribute("userName", claims.getSubject());
        System.out.println("登录token解析值：" + claims.getSubject());
        return super.preHandle(request, response, handler);
    }
}
