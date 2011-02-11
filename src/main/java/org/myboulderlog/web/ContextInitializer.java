package org.myboulderlog.web;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.server.model.Route;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class ContextInitializer implements ServletContextListener {

    private ObjectifyFactory objectifyFactory;

    @Inject
    public ContextInitializer(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public void contextInitialized(final ServletContextEvent sce) {
        objectifyFactory.register(Route.class);
    }

    public void contextDestroyed(final ServletContextEvent sce) {
        // empty
    }
}
