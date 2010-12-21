package org.myboulderlog.client.inject;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.widget.MainApplicationWidget;
import org.myboulderlog.client.widget.MessageWidget;

public class ApplicationClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainApplicationWidget.class).in(Singleton.class);
        bind(ApplicationUtils.class).in(Singleton.class);
    }
}
