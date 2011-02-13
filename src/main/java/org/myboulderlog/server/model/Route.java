package org.myboulderlog.server.model;

public class Route extends AbstractDomainObject {

    private String name;

    private Long difficultyRatingId;

    private String description;

    private Long areaId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDifficultyRatingId() {
        return difficultyRatingId;
    }

    public void setDifficultyRatingId(Long difficultyRatingId) {
        this.difficultyRatingId = difficultyRatingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}