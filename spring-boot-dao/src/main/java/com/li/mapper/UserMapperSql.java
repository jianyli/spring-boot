package com.li.mapper;

import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMapperSql {
    private static Logger logger = LoggerFactory.getLogger(UserMapperSql.class);

    public String getUserByName(String name) {
        String sql = new SQL() {{
            SELECT("*");
            FROM("t_user_info");
            if (StringUtils.isNotBlank(name)) {
                WHERE("name = #{name}");
            } else {
                throw new ServiceException(ErrorCodeEnum.NULL);
            }
        }}.toString();
        logger.info("findUserByName查询语句 :" + sql);
        return sql;
    }
}
