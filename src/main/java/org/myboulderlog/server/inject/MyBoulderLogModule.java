package org.myboulderlog.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.helper.DAOBase;
import org.myboulderlog.server.dao.RouteDAO;
import org.myboulderlog.server.dao.objectify.RouteDAOImpl;

public class MyBoulderLogModule extends AbstractModule {

    protected void configure() {
        bind(RouteDAO.class).to(RouteDAOImpl.class);
        bind(ObjectifyFactory.class).in(Singleton.class);
        bind(DAOBase.class).in(Singleton.class);
    }
}
