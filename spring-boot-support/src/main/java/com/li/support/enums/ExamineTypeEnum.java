package com.li.support.enums;

import org.apache.commons.lang3.StringUtils;

public enum  ExamineTypeEnum {
    FRIEND("applyFriend","申请好友");
    private final String key;
    private final String value;

    private ExamineTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据key获取value
     */
    public static String getValueByKey(String key) {
        if( StringUtils.isNotBlank(key) ) {
            for( ExamineTypeEnum e : ExamineTypeEnum.values() ) {
                if( e.getKey().equals(key) ) {
                    return e.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 根据value获取key
     */
    public static String getKeyByValue(String value) {
        if( StringUtils.isNotBlank(value) ) {
            for( ExamineTypeEnum e : ExamineTypeEnum.values() ) {
                if( e.getValue().equals(value) ) {
                    return e.getKey();
                }
            }
        }
        return null;
    }
}
