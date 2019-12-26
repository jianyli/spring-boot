package com.li.dao;

import com.li.domain.UserInfo;
import com.li.support.dto.UserInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao  {


    int update(UserInfoDTO userInfoDTO);

}
