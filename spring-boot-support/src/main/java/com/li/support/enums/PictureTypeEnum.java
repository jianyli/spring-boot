package com.li.support.enums;

public enum PictureTypeEnum {
    BLOG_PICTURE("blogType","博客图片"),AVATAR_PICTURE("avatarType","头像图片");

    private String key;
    private String value;
    private PictureTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getKeyByValue(String value) {
        for (PictureTypeEnum pictureTypeEnum : PictureTypeEnum.values()) {
            if (pictureTypeEnum.getValue().equals(value)) {
                return pictureTypeEnum.getKey();
            }
        }
        return null;
    }

    public static String getValueByKey(String key) {
        for (PictureTypeEnum pictureTypeEnum : PictureTypeEnum.values()) {
            if (pictureTypeEnum.getKey().equals(key)) {
                return pictureTypeEnum.getValue();
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
