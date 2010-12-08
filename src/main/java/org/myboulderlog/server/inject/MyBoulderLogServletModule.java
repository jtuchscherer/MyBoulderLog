package org.myboulderlog.server.inject;

import com.google.inject.servlet.ServletModule;
import org.myboulderlog.server.service.MessageServiceImpl;

public class MyBoulderLogServletModule extends ServletModule {

     protected void configureServlets() {
        serve("/org.myboulderlog.Application/MessageService").with(MessageServiceImpl.class);
     }
}
