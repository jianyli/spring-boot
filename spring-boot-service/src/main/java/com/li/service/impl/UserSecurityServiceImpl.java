package com.li.service.impl;

import com.li.domain.UserInfo;
import com.li.mapper.UserInfoMapper;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import com.li.support.util.TransformUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {
    @Resource
    private UserInfoMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserInfo userInfo = mapper.findByNameOrTelephone(name);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new ServiceException(ErrorCodeEnum.NO_USER);
        }
        return TransformUtil.transfer(userInfo);
    }
}
