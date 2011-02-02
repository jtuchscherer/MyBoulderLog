package org.myboulderlog.server.inject;

import com.google.inject.AbstractModule;
import org.myboulderlog.server.dao.RouteDAO;
import org.myboulderlog.server.dao.objectify.RouteDAOImpl;

public class MyBoulderLogModule extends AbstractModule {

    protected void configure() {
        bind(RouteDAO.class).to(RouteDAOImpl.class);
    }
}
