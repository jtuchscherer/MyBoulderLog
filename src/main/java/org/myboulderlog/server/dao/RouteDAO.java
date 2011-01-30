package org.myboulderlog.server.dao;

import org.myboulderlog.server.model.Route;

import java.util.List;

public interface RouteDAO extends AbstractDAO<Route> {

    public Route saveAndReturn(Route list);

//    public List<Route> listAll();
}
