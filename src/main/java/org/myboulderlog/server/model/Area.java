package org.myboulderlog.server.model;

import com.google.appengine.api.blobstore.BlobKey;

public class Area extends AbstractDomainObject {

    private String name;

    private String description;

    private BlobKey imageKey;

    private Long gymId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlobKey getImageKey() {
        return imageKey;
    }

    public void setImageKey(BlobKey imageKey) {
        this.imageKey = imageKey;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
