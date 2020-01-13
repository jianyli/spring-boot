package com.li.mapper;

import com.li.domain.GroupInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface GroupInfoMapper {
    final static String BEEN_DELETE = " and been_delete=0";

    @Insert("insert into t_group_info (user_id, name, create_time, been_delete,last_change_time)" +
            " VALUE (#{userId}, #{name}, now(),0,now());")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id",before = false, resultType = int.class)
    int save(GroupInfo groupInfo);

    @Select("select * from t_group_info where id=#{id} and user_id=#{applyId}" + BEEN_DELETE)
    GroupInfo findById(Integer id, Integer applyId);


}
