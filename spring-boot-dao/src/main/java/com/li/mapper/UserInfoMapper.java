package com.li.mapper;

import com.li.domain.UserInfo;
import org.apache.ibatis.annotations.*;

public interface UserInfoMapper {
    final static String BEEN_DELETE = " and been_delete=0";
    @Select("SELECT * FROM t_user_info WHERE id = #{userId}" + BEEN_DELETE)
    UserInfo findById(@Param("userId") Integer userId);

    @Select("SELECT * FROM t_user_info WHERE name = #{userName} AND password = #{password}" + BEEN_DELETE)
    UserInfo checkUser(@Param("userName") String userName, @Param("password") String password);

    @SelectProvider(type = UserMapperSql.class, method = "getUserByName")
    UserInfo findByName(String name);

    @Insert("insert into t_user_info (name, password, role, nick_name, birth_time, telephone," +
            " avatar_id, is_group, create_time, been_delete, delete_time, deleter_id, last_change_time, qq_number)" +
            "VALUE (#{name},#{password},#{role},#{nickName},#{birthTime},#{telephone},null,0,now(),0,null,null,now(),#{qqNumber})")
    void save(UserInfo userInfo);

    @Update("update t_user_info set nick_name = #{nickName} where id = #{id}")
    void update(UserInfo userInfo);

    @Update("update t_user_info set been_delete = #{beenDelete} where id = #{id}")
    void delete(UserInfo userInfo);
}
