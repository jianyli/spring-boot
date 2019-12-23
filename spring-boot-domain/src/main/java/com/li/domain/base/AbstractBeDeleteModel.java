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
    private Integer beenDelete;
    private Date deleteTime;

    public AbstractBeDeleteModel() {
        this.beenDelete = NO_DELETE;
    }

    @Column(name = "beenDelete")
    public Integer getBeenDelete() {
        return beenDelete;
    }

    public void setBeenDelete(Integer beenDelete) {
        this.beenDelete = beenDelete;
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
