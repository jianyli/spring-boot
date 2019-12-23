package com.li.dao.impl;

import com.google.common.collect.Lists;
import com.li.dao.IUserDao;
import com.li.domain.UserInfo;
import com.li.support.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl extends SimpleJpaRepository<UserInfo, Integer> implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public UserDaoImpl(JpaEntityInformation<UserInfo, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public int update(UserInfoDTO userInfoDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append("update t_user_info ");
        List<String> list = Lists.newArrayList();
        if (!StringUtils.isBlank(userInfoDTO.getName())) {
            sb.append("set name = ? ");
            list.add(userInfoDTO.getName());
        }
        if (!StringUtils.isBlank(userInfoDTO.getPassword())) {
            sb.append("and set password = ? ");
            list.add(userInfoDTO.getPassword());
        }
        if (!StringUtils.isBlank(userInfoDTO.getNickName())) {
            sb.append("and set nick_name = ? ");
            list.add(userInfoDTO.getNickName());
        }
        if (StringUtils.isNumeric(userInfoDTO.getTelephone())) {
            sb.append("and set telephone = ? ");
            list.add(userInfoDTO.getTelephone());
        }
        if (StringUtils.isNumeric(userInfoDTO.getQqNumber())) {
            sb.append("and set qq_number = ?");
            list.add(userInfoDTO.getQqNumber());
        }
        String hql = sb.toString();

        return jdbcTemplate.update(hql, list.toArray());
    }
}
