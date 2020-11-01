package com.li.support.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

public class StudentDto {
    private String name;
    private Long studentNo;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
