package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_friend")
public class UserFriend extends AbstractBeDeleteModel {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "friend_id")
    private int friendId;
    private String relationship;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "group_id")
    private int groupId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
