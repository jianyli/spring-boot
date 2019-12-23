package com.li.service.impl;

import com.li.dao.IUserDao;
import com.li.domain.UserInfo;
import com.li.service.IUserService;
import com.li.support.dto.UserInfoDTO;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.enums.UserRoleEnum;
import com.li.support.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
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
    public void update(UserInfoDTO userInfoDTO) {
        if (!userDao.existsById(userInfoDTO.getId())) {
            throw new ServiceException(ErrorCodeEnum.NO_USER);
        }
        int result = userDao.update(userInfoDTO);
        if (result == 0) {
            throw new ServiceException("更新用户信息失败");
        }
    }

    @Override
    public UserInfo findById(Integer id) {
        Optional<UserInfo> userInfo = userDao.findById(id);

        return userInfo.orElse(null);
    }
}
