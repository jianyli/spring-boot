package com.li.service;

import com.li.domain.UserInfo;
import com.li.support.dto.UserInfoDTO;

public interface IUserService {
    void save(UserInfo userInfo);
    void update(UserInfoDTO userInfoDTO);
    UserInfoDTO findById(Integer id);
}
