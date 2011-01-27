package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.Key;
import org.myboulderlog.server.model.Route;

public class RouteDAOImpl extends AbstractObjectifyDAO<Route> {

    protected Class clazz = RouteDAOImpl.class;

    // Note: requires no args since it's an instance method
    public Route saveAndReturn(Route list) {
        Key<Route> key = this.put(list);
        try {
            return this.get(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
