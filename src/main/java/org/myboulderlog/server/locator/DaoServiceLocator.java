package org.myboulderlog.server.locator;

import com.google.gwt.requestfactory.shared.ServiceLocator;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class DaoServiceLocator implements ServiceLocator {

    private final Injector injector;

    @Inject
    public DaoServiceLocator(Injector injector) {
        this.injector = injector;
    }

    public Object getInstance(Class<?> clazz) {
        return injector.getInstance(clazz);
    }

}