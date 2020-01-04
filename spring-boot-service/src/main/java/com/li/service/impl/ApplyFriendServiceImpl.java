package com.li.service.impl;

import com.li.domain.ApplyFriend;
import com.li.service.ApplyFriendService;
import com.li.service.IUserService;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Resource;
import java.util.Objects;

public class ApplyFriendServiceImpl implements ApplyFriendService {
    @Resource
    private IUserService userService;

    @Override
    public void save(ApplyFriend applyFriend) {
        if (Objects.isNull(applyFriend)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        Integer applyId = applyFriend.getApplyId();
        Integer targetId = applyFriend.getTargetId();
        Integer groupId = applyFriend.getGroupId();
        if (applyId == null || targetId == null || groupId == null) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        if (ObjectUtils.isEmpty(userService.findById(applyId))) {
            throw new ServiceException(ErrorCodeEnum.NO_USER);
        }
        if (ObjectUtils.isEmpty(userService.findById(targetId))) {
            throw new ServiceException(ErrorCodeEnum.NO_USER);
        }
        //TODO 好友组id核查
    }
}
