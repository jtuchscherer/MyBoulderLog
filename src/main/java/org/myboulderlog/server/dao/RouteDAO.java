package org.myboulderlog.server.dao;

import org.myboulderlog.server.model.Route;

public interface RouteDAO extends AbstractDAO<Route> {

    public Route saveAndReturn(Route list);
}
