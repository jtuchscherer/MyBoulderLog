package org.myboulderlog.server.model;

import javax.persistence.Id;
import javax.persistence.PrePersist;

public abstract class AbstractDomainObject {


    @Id
    private Long id;
    private Integer version = 0;

    @PrePersist
    void onPersist() {
        this.version++;
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
}
