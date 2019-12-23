package com.li.domain;

import com.li.domain.base.AbstractBeDeleteModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_picture_info")
public class PictureInfo extends AbstractBeDeleteModel {
    private int businessId;
    @Column(name = "picture_type", nullable = false)
    private String pictureType;
    private String address;
    private Integer size;//图片大小

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
