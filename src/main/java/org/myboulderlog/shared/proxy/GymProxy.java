package org.myboulderlog.shared.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import org.myboulderlog.server.locator.ObjectifyLocator;
import org.myboulderlog.server.model.Gym;

@ProxyFor(value = Gym.class, locator = ObjectifyLocator.class)
public interface GymProxy extends DatastoreObjectProxy {
    public String getName();

    public void setName(String value);

    public String getDescription();

    public void setDescription(String value);

    public Long getId();
}
