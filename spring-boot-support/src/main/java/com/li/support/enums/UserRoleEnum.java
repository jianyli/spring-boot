package com.li.support.enums;


public enum  UserRoleEnum {
    SUPER_ADMIN("superAdmin","超级管理员"), ADMIN("admin","管理员"), USER("user","普通用户");
    private String key;
    private String value;

    private UserRoleEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getKeyByValue(String value) {
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.getValue().equals(value)) {
                return userRoleEnum.getKey();
            }
        }
        return null;
    }

    public static String getValueByKey(String key) {
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.getKey().equals(key)) {
                return userRoleEnum.getValue();
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
