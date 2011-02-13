package org.myboulderlog.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.server.model.Address;
import org.myboulderlog.server.model.Area;
import org.myboulderlog.server.model.Comment;
import org.myboulderlog.server.model.DifficultyRating;
import org.myboulderlog.server.model.Gym;
import org.myboulderlog.server.model.Route;
import org.myboulderlog.server.model.SubjectiveDifficultyRating;
import org.myboulderlog.server.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;

public class GuiceServletConfig extends GuiceServletContextListener {

    static final String INJECTOR_NAME = Injector.class.getName();
    private ObjectifyFactory objectifyFactory;
    private Logger logger = LoggerFactory.getLogger(GuiceServletConfig.class);

    protected Injector getInjector() {
        return Guice.createInjector(new RequestFactoryInjectingModule("/myboulderlog/gwtRequest"),
                                    new MyBoulderLogServletModule(),
                                    new MyBoulderLogModule()
        );
    }

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        super.contextInitialized(sce);
        logger.debug("Registering domain classes");
        ObjectifyService.register(Address.class);
        ObjectifyService.register(Area.class);
        ObjectifyService.register(Comment.class);
        ObjectifyService.register(DifficultyRating.class);
        ObjectifyService.register(Gym.class);
        ObjectifyService.register(SubjectiveDifficultyRating.class);
        ObjectifyService.register(Route.class);
        ObjectifyService.register(User.class);
    }


}
