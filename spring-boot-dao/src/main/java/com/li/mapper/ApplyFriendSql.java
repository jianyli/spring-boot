package com.li.mapper;

import com.li.domain.ApplyFriend;
import com.li.domain.GroupInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class ApplyFriendSql {
    final static String BEEN_DELETE = "been_delete=0";

    public String update(ApplyFriend applyFriend) {
        String sql = new SQL() {{
            UPDATE("t_apply_friend");
            SET("group_id=#{groupId}","last_change_time=now()");
            if (StringUtils.isNotBlank(applyFriend.getReason())) {
                SET("reason=#{reason}");
            }
            WHERE("id=#{id}", BEEN_DELETE);
        }}.toString();
        System.out.println("申请好友修改信息的sql:" + sql);
        return sql;
    }
}
