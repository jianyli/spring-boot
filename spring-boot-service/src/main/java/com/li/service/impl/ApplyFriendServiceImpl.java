package com.li.service.impl;

import com.li.domain.ApplyFriend;
import com.li.mapper.ApplyFriendMapper;
import com.li.mapper.GroupInfoMapper;
import com.li.mapper.UserFriendMapper;
import com.li.service.IApplyFriendService;
import com.li.service.IUserService;
import com.li.support.dto.ApplyFriendDTO;
import com.li.support.dto.PageDto;
import com.li.support.dto.UserInfoDTO;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplyFriendServiceImpl implements IApplyFriendService {
    private Logger logger = LoggerFactory.getLogger(ApplyFriendServiceImpl.class);

    @Resource
    private ApplyFriendMapper applyMapper;
    @Resource
    private GroupInfoMapper groupMapper;
    @Resource
    private UserFriendMapper userFriendMapper;
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
        UserInfoDTO userInfoDTO = userService.findById(targetId);
        if (ObjectUtils.isEmpty(userInfoDTO)) {
            throw new ServiceException(ErrorCodeEnum.NO_USER);
        }
        if (ObjectUtils.isEmpty(userFriendMapper.findByUserIdAndFriendId(applyId, targetId))) {
            throw new ServiceException("好友已存在");
        }
        if (ObjectUtils.isEmpty(groupMapper.findById(groupId, applyId))) {
            throw new ServiceException(ErrorCodeEnum.ILLEGAL);
        }
        if (StringUtils.isBlank(applyFriend.getNoteName())) {
            applyFriend.setNoteName(userInfoDTO.getNickName());
        }
        Integer id = applyMapper.checkExist(applyId, targetId);
        if (!Objects.isNull(id)) {  //id不为空，则已存在申请，改修改操作
            logger.info("申请好友记录已存在，修改id:" + id);
            applyFriend.setId(id);
            applyMapper.update(applyFriend);
        } else {
            //保存操作
            applyMapper.save(applyFriend);
        }
        //TODO 审核通知
    }

    @Override
    public PageDto pageList(Integer applyId, Pageable pageable) {
        String sortField = pageable.getSort().iterator().next().getProperty();
        String sortDirection = pageable.getSort().iterator().next().getDirection().toString();
        int size = pageable.getPageSize();
        int offset = pageable.getPageNumber() * size;
        int total = applyMapper.count(applyId, offset, size);
        List<ApplyFriendDTO> data = applyMapper.findList(applyId, sortField, sortDirection, offset, size);

        return new PageDto(total, data);
    }
}
