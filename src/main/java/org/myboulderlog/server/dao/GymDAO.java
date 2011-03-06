package org.myboulderlog.server.dao;

import org.myboulderlog.server.model.Gym;

public interface GymDAO extends AbstractDAO<Gym> {

    public void save(Gym gym);

    public void remove(Gym gym);
}
