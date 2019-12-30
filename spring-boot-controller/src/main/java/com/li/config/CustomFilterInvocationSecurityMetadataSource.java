package com.li.config;

import com.li.service.IUserService;
import com.li.support.dto.UserInfoDTO;
import com.li.support.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private IUserService userService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        if (antPathMatcher.match("/login", requestUrl)) {
            String[] roles = new String[UserRoleEnum.size()];
            int i = 0;
            for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
                roles[i] = roleEnum.getKey();
                i++;
            }
            return SecurityConfig.createList(roles);
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
