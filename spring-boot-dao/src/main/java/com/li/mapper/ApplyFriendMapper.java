package com.li.mapper;

import com.li.domain.ApplyFriend;
import com.li.domain.GroupInfo;
import com.li.support.dto.ApplyFriendDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface ApplyFriendMapper {
    final static String BEEN_DELETE = " and been_delete=0";

    @Insert("insert into t_apply_friend (apply_id, target_id, apply_time, note_name, group_id, reason, create_time, been_delete)" +
            " VALUE (#{applyId},#{targetId},now(),#{noteName},#{groupId},#{reason},now(),0)")
    void save(ApplyFriend applyFriend);

    @Select("select id from t_apply_friend where apply_id=#{applyId} and target_id=#{targetId}" + BEEN_DELETE)
    Integer checkExist(Integer applyId, Integer targetId);

    @UpdateProvider(type = ApplyFriendSql.class, method = "update")
    void update(ApplyFriend applyFriend);

    @Select("select * from t_apply_friend where apply_id=#{applyId}" + BEEN_DELETE + " order by ${sortField} ${sortDirection} limit #{offset},#{size}")
    List<ApplyFriendDTO> findList(Integer applyId, String sortField, String sortDirection, int offset, int size);

    @Select("select count(id) from t_apply_friend where apply_id=#{applyId}" + BEEN_DELETE )
    Integer count(Integer applyId,int offset,int size);
}
