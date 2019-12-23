package com.li.dao;

import com.li.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<UserInfo, String> {

}
