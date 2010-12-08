package org.myboulderlog.server.inject;

import com.google.inject.AbstractModule;
import org.myboulderlog.server.dao.MessageDAO;
import org.myboulderlog.server.dao.objectify.MessageDAOImpl;

public class MyBoulderLogModule extends AbstractModule {

    protected void configure() {
        bind(MessageDAO.class).to(MessageDAOImpl.class);
    }
}
