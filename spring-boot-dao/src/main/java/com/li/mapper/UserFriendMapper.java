package com.li.mapper;

import com.li.domain.UserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserFriendMapper {
    final static String BEEN_DELETE = " and been_delete=0";

    @Select("select * from t_user_friend where user_id=#{userId} and friend_id=#{friendId}" + BEEN_DELETE)
    UserFriend findByUserIdAndFriendId(Integer userId, Integer friendId);
}
