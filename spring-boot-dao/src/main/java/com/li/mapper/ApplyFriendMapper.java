package com.li.mapper;

import com.li.domain.ApplyFriend;
import com.li.domain.GroupInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

public interface ApplyFriendMapper {
    final static String BEEN_DELETE = " and been_delete=0";

    @Insert("insert into t_apply_friend (apply_id, target_id, apply_time, note_name, group_id, reason, create_time, been_delete)" +
            " VALUE (#{applyId},#{targetId},now(),#{noteName},#{groupId},#{reason},now(),0)")
    void save(ApplyFriend applyFriend);

    @Select("select id from t_apply_friend where apply_id=#{applyId} and target_id=#{targetId}" + BEEN_DELETE)
    Integer checkExist(Integer applyId, Integer targetId);

    @UpdateProvider(type = ApplyFriendSql.class, method = "update")
    void update(ApplyFriend applyFriend);
}
