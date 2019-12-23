package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_blog_info")
public class BlogInfo extends AbstractBeDeleteModel {
    private int UserId;
    private String blogTitle;
    private String blogAddress;//博客文章保存地址
    private Integer viewNumber;
    private Integer starNumber;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }
}
