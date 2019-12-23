package com.li.support.enums;

public enum RelationshipEnum {
    LOVE("love","恋人"),FRIEND("friend","好友");

    private String key;
    private String value;
    private RelationshipEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getKeyByValue(String value) {
        for (RelationshipEnum relationshipEnum : RelationshipEnum.values()) {
            if (relationshipEnum.getValue().equals(value)) {
                return relationshipEnum.getKey();
            }
        }
        return null;
    }

    public static String getValueByKey(String key) {
        for (RelationshipEnum relationshipEnum : RelationshipEnum.values()) {
            if (relationshipEnum.getKey().equals(key)) {
                return relationshipEnum.getValue();
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
