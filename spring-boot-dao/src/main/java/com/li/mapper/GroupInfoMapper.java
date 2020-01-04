package com.li.mapper;

import com.li.domain.GroupInfo;
import org.apache.ibatis.annotations.Insert;

public interface GroupInfoMapper {
    @Insert("insert into t_group_info (user_id, name, create_time, been_delete,last_change_time)" +
            " VALUE (#{userId}, #{name}, now(),0,now()});")
    void save(GroupInfo groupInfo);
}
