package org.myboulderlog.server.dao.objectify;

import org.myboulderlog.server.dao.GymDAO;
import org.myboulderlog.server.model.Gym;

public class GymDAOImpl extends AbstractObjectifyDAO<Gym> implements GymDAO {

    public void save(Gym route) {
        this.put(route);
    }
}
