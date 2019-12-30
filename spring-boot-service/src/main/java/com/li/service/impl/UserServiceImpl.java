package com.li.service.impl;

import com.li.dao.IUserDao;
import com.li.domain.UserInfo;
import com.li.mapper.UserInfoMapper;
import com.li.service.IUserService;
import com.li.support.dto.UserInfoDTO;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.enums.UserRoleEnum;
import com.li.support.exception.ServiceException;
import com.li.support.util.TransformUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserInfoMapper mapper;

    @Override
    public void save(UserInfo userInfo) {
        mapper.save(userInfo);
    }

    @Override
    public void update(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = TransformUtil.transform(userInfoDTO);

        if (ObjectUtils.isEmpty(this.findById( userInfo.getId() ))) {
            throw new ServiceException(ErrorCodeEnum.NO_RECORD);
        }
        mapper.update(userInfo);
    }

    @Override
    public UserInfoDTO findById(Integer id) {
        UserInfo userInfo = mapper.findById(id);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new ServiceException(ErrorCodeEnum.NO_USER);
        }

        return TransformUtil.transform(userInfo);
    }

}
