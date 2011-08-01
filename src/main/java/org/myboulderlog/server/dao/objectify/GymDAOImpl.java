package org.myboulderlog.server.dao.objectify;

import org.myboulderlog.server.dao.GymDAO;
import org.myboulderlog.server.model.Gym;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GymDAOImpl extends AbstractObjectifyDAO<Gym> implements GymDAO {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    protected Logger getLogger() {
        return this.logger;
    }
}
