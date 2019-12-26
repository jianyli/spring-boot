package com.li.support.mapper;

import com.google.common.collect.Lists;
import org.apache.ibatis.jdbc.SQL;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BaseMapper<T extends Serializable> {

    private String tableName;


    public void save(T t) {

        String sql = new SQL(){{
            INSERT_INTO(tableName);

        }}.toString();
    }

    private static List<String> getFieldName(Object o) {
        List<String> list = Lists.newArrayList();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            list.add(field.getName());
        }
        return list;
    }

    private static Object getValueByName(String name, Object o) {
        try {
            String firstName = name.substring(0,1).toUpperCase();
            String methodName = "get" + firstName + name.substring(1);
            Method method = o.getClass().getMethod(methodName, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
