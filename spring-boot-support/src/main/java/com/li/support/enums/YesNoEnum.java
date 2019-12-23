package com.li.support.enums;

public enum YesNoEnum {
    YES(1, "是"),NO(0, "否");
    private int key;
    private String value;

    private YesNoEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Integer getKeyByValue(String value) {
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (yesNoEnum.getValue().equals(value)) {
                return yesNoEnum.getKey();
            }
        }
        return null;
    }

    public static String getValueByKey(Integer key) {
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (yesNoEnum.getKey() == key) {
                return yesNoEnum.getValue();
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
