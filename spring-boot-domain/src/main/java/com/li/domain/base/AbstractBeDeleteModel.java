package com.li.domain.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractBeDeleteModel extends AbstractBaseModel {
    public static final Integer DELETE = 1;
    public static final Integer NO_DELETE = 0;
    private Integer beenDeleted;
    private Date deleteTime;

    public AbstractBeDeleteModel() {
        this.beenDeleted = NO_DELETE;
    }

    @Column(name = "been_deleted")
    public Integer getBeenDeleted() {
        return beenDeleted;
    }

    public void setBeenDeleted(Integer beenDeleted) {
        this.beenDeleted = beenDeleted;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleteTime")
    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
