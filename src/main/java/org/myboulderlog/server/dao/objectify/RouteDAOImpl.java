package org.myboulderlog.server.dao.objectify;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import org.myboulderlog.server.dao.RouteDAO;
import org.myboulderlog.server.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteDAOImpl extends AbstractObjectifyDAO<Route> implements RouteDAO {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Route saveAndReturn(Route route) {
        Key<Route> key = this.put(route);
        try {
            return this.get(key);
        } catch (Exception e) {
            logger.error("Was not able to save and return requested Object", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        try {
            Route route = get(id);
            delete(route);
        } catch (EntityNotFoundException e) {
            logger.error("Was not able to delete requested Object", e);
            throw new RuntimeException(e);
        }
    }
}
