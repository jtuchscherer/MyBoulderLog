package org.myboulderlog.server.model;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.Date;

public abstract class AbstractDomainObject {


    @Id
    private Long id;

    private Integer version = 0;

    private Date creationDate;

    private Date lastUpdateDate;

    @PrePersist
    void onPersist() {
        this.version++;
        if(this.creationDate == null) {
            this.creationDate = new Date();
        }
        this.lastUpdateDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
