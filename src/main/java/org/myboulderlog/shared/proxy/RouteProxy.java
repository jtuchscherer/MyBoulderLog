package org.myboulderlog.shared.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import org.myboulderlog.server.locator.ObjectifyLocator;
import org.myboulderlog.server.model.Route;

@ProxyFor(value = Route.class, locator = ObjectifyLocator.class)
public interface RouteProxy extends DatastoreObjectProxy {

    public String getName();

    public void setName(String name);

    public Long getDifficultyRatingId();

    public void setDifficultyRatingId(Long rating);

    public String getDescription();

    public void setDescription(String description);

    public Long getAreaId();

    public void setAreaId(Long areaId);
}
