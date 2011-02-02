package org.myboulderlog.web;

import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.server.model.Route;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class ContextInitializer implements ServletContextListener {
    public void contextInitialized(final ServletContextEvent sce) {
        ObjectifyService.register(Route.class);
    }

    public void contextDestroyed(final ServletContextEvent sce) {
        // empty
    }
}
