package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_info")
public class UserInfo extends AbstractBeDeleteModel {
    private String name;
    private String password;
    private String role; //角色
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "birth_time")
    private Date birthTime;//出生日期
    @Column(name = "qq_number")
    private String qqNumber;//QQ号码
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "avatar_id")
    private String avatarId; //头像id
    @Column(name = "is_group")
    private int isGroup;//是否还有组信息

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Date birthTime) {
        this.birthTime = birthTime;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }


    public int getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(int isGroup) {
        this.isGroup = isGroup;
    }
}
