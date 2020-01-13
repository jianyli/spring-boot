package com.li.support.util;

import com.li.domain.ExamineInfo;
import com.li.domain.UserInfo;
import com.li.support.dto.ExamineInfoDTO;
import com.li.support.dto.UserInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

public class TransformUtil {
    public static UserInfo transform(UserInfoDTO userInfoDTO) {
        UserInfo target = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, target);
        return target;
    }

    public static UserInfoDTO transform(UserInfo userInfo) {
        UserInfoDTO target = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, target);
        return target;
    }

    public static ExamineInfo transform(ExamineInfoDTO examineInfoDTO) {
        ExamineInfo target = new ExamineInfo();
        BeanUtils.copyProperties(examineInfoDTO, target);
        return target;
    }

}
