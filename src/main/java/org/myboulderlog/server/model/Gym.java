package org.myboulderlog.server.model;

import com.google.appengine.api.blobstore.BlobKey;

import javax.validation.constraints.NotNull;

public class Gym extends AbstractDomainObject {

    @NotNull
    private String name;

    private String description;

    private String webAddress;

    private Address addressId;

    private BlobKey imageKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public BlobKey getImageKey() {
        return imageKey;
    }

    public void setImageKey(BlobKey imageKey) {
        this.imageKey = imageKey;
    }
}
