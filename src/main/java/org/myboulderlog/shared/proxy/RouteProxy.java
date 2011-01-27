package org.myboulderlog.shared.proxy;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;
import org.myboulderlog.server.locator.ObjectifyLocator;
import org.myboulderlog.server.model.Route;

@ProxyFor(value = Route.class, locator = ObjectifyLocator.class)
public interface RouteProxy extends DatastoreObjectProxy {

    public String getName();

    public void setName(String name);

    public Long getRating();

    public void setRating(Long rating);

    public String getDescription();

    public void setDescription(String description);

    public Long getArea();

    public void setArea(Long area);
}
