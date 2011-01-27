package org.myboulderlog.server.locator;

import com.google.gwt.requestfactory.shared.ServiceLocator;

public class DaoServiceLocator implements ServiceLocator {

    public Object getInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}