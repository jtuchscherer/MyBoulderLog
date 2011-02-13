package org.myboulderlog.server.model;

public class DifficultyRating extends AbstractDomainObject {

    private Long gymId;

    private Integer nominalScale;

    private String name;

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public Integer getNominalScale() {
        return nominalScale;
    }

    public void setNominalScale(Integer nominalScale) {
        this.nominalScale = nominalScale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
