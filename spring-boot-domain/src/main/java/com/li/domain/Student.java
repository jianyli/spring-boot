package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student extends AbstractBeDeleteModel {
    @Column(name = "name", columnDefinition = " varchar(50) null comment '学生姓名'")
    private String name;
    @Column(name = "student_no", columnDefinition = " varchar(15) null comment '学生编号'")
    private Long studentNo;
    @Column(name = "sex")
    private String sex;

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

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}