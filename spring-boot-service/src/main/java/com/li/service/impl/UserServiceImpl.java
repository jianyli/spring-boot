package com.li.service.impl;

import com.li.dao.IUserDao;
import com.li.domain.GroupInfo;
import com.li.domain.UserInfo;
import com.li.mapper.GroupInfoMapper;
import com.li.mapper.UserInfoMapper;
import com.li.service.IUserService;
import com.li.support.dto.UserInfoDTO;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.enums.UserRoleEnum;
import com.li.support.exception.ServiceException;
import com.li.support.util.JwtUtil;
import com.li.support.util.TransformUtil;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserInfoMapper mapper;

    @Resource
    private GroupInfoMapper groupMapper;

    @Override
    public void save(UserInfo userInfo) {
        String name = userInfo.getName();
        if (StringUtils.isBlank(name)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        if (ObjectUtils.isNotEmpty(mapper.findByName(name))) {
            throw new ServiceException("名称已存在");
        }
        mapper.save(userInfo);
        UserInfo savedInfo = mapper.findByName(name);
        if (ObjectUtils.isEmpty(savedInfo)) {  //没有找到则系统异常
            throw new ServiceException(ErrorCodeEnum.ERROR);
        }
        //默认初始化一个好友组
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setName("我的好友");
        groupInfo.setUserId(savedInfo.getId());
        groupMapper.save(groupInfo);
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

    @Override
    public String checkUser(String userName, String password) {
        UserInfo userInfo = mapper.checkUser(userName, password);
        if (ObjectUtils.isEmpty(userInfo)) {
            return null;
        }
        String token = JwtUtil.getToken(userInfo.getName());
        logger.info("用户：" + userName + " 登录token：" + token);
        return token;
    }
}
