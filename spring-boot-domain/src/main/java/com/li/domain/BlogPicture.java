package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_blog_picture")
public class BlogPicture extends AbstractBeDeleteModel {
    private int blogId;
    private int pictureId;
    private String address;//图片地址
    private int number;//图片编号

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
