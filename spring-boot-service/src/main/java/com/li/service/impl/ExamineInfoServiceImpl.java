package com.li.service.impl;

import com.li.domain.ExamineFlow;
import com.li.domain.ExamineInfo;
import com.li.mapper.ExamineFlowMapper;
import com.li.mapper.ExamineInfoMapper;
import com.li.service.IExamineInfoService;
import com.li.service.IUserService;
import com.li.support.dto.ExamineInfoDTO;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.enums.ExamineTypeEnum;
import com.li.support.exception.ServiceException;
import com.li.support.util.ExceptionUtil;
import com.li.support.util.TransformUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Objects;

@Service
@Transactional
public class ExamineInfoServiceImpl implements IExamineInfoService {
    @Resource
    private ExamineInfoMapper examineInfoMapper;
    @Resource
    private IUserService userService;
    @Resource
    private ExamineFlowMapper examineFlowMapper;

    @Override
    public void save(ExamineInfoDTO examineInfoDTO) {
        if (Objects.isNull(examineInfoDTO)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        if (examineInfoDTO.getBusinessId() == null || examineInfoDTO.getApplyUserId()==null || StringUtils.isBlank(examineInfoDTO.getExaminerIds()) ||
                StringUtils.isBlank(examineInfoDTO.getExamineType())) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        if (ExamineTypeEnum.getValueByKey(examineInfoDTO.getExamineType()) == null) {
            throw new ServiceException(ErrorCodeEnum.ILLEGAL);
        }

        userService.findById(examineInfoDTO.getApplyUserId());  //检查申请人是否存在
        String[] examinerIds = examineInfoDTO.getExaminerIds().split(",");
        Integer[] examineUserIds = new Integer[examinerIds.length];
        Integer examineUserId;
        try{
            for (int i = 0; i < examinerIds.length; i++) {
                examineUserId = Integer.parseInt(examinerIds[i]);
                userService.findById(examineUserId);
                examineUserIds[i] = examineUserId;
            }
        } catch (NumberFormatException e) {
            throw new ServiceException(ErrorCodeEnum.ILLEGAL);
        }

        ExamineInfo examineInfo = TransformUtil.transform(examineInfoDTO);
        examineInfo.setExaminerId(examineUserIds[0]);
        examineInfoMapper.save(examineInfo);

        Integer id = examineInfoMapper.findId(examineInfoDTO.getBusinessId(), examineInfoDTO.getExamineType());
        //保存到审核流程表中
        ExamineFlow examineFlow;
        for (int i = 0; i < examineUserIds.length; i++) {
            examineFlow = new ExamineFlow();
            examineFlow.setExamineId(id);
            examineFlow.setExamineOrder(i + 1);
            examineFlow.setExamineUserId(examineUserIds[i]);
            examineFlowMapper.save(examineFlow);
        }
    }

    @Override
    public void examine(ExamineInfoDTO examineInfoDTO) {

    }
}
