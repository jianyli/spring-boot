package com.li.mapper;

import com.li.domain.ApplyFriend;
import org.apache.ibatis.annotations.Insert;

public interface ApplyFriendMapper {
    @Insert("insert into t_apply_friend (apply_id, target_id, apply_time, note_name, group_id, reason, result, create_time, been_delete)" +
            " VALUE (#{applyId},#{targetId},now(),#{noteName},#{groupId},#{reason},now(),0)")
    void save(ApplyFriend applyFriend);
}
