package org.myboulderlog.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

    protected Injector getInjector() {
        return Guice.createInjector(new RequestFactoryInjectingModule("/myboulderlog/gwtRequest"),
                                    new MyBoulderLogServletModule(),
                                    new MyBoulderLogModule()
        );
    }


}
