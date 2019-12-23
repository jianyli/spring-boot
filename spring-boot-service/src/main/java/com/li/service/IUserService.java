package com.li.service;

import com.li.domain.UserInfo;

public interface IUserService {
    void save(UserInfo userInfo);
    UserInfo findById(String id);
}
