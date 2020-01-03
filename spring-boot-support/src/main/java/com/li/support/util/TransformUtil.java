package com.li.support.util;

import com.li.domain.UserInfo;
import com.li.support.dto.UserInfoDTO;
import org.springframework.beans.BeanUtils;

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

}
