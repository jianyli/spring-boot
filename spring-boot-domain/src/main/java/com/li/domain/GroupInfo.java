package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_group_info")
public class GroupInfo extends AbstractBeDeleteModel {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "name")
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
