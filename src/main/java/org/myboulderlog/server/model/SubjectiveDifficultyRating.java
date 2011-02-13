package org.myboulderlog.server.model;

public class SubjectiveDifficultyRating extends AbstractDomainObject {

    private Long userId;

    private Long routeId;

    private Long difficultyRatingId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getDifficultyRatingId() {
        return difficultyRatingId;
    }

    public void setDifficultyRatingId(Long difficultyRatingId) {
        this.difficultyRatingId = difficultyRatingId;
    }
}
