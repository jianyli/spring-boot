package com.li.domain.base;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseModel implements Serializable {

    private String id;
    private Date createTime;
    private Date lastChangeTime;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
            name = "idGenerator",
            strategy = "uuid.hex"
    )
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "createTime", updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "lastChangeTime")
    public Date getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Date lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }
}
