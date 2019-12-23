package com.li.service.impl;

import com.li.dao.IUserDao;
import com.li.domain.UserInfo;
import com.li.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public void save(UserInfo userInfo) {
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        Optional<UserInfo> userInfo = userDao.findById(id);

        return userInfo.orElse(null);
    }
}
