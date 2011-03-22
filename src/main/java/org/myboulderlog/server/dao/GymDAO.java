package org.myboulderlog.server.dao;

import org.myboulderlog.server.model.Gym;

public interface GymDAO extends AbstractDAO<Gym> {

    public Gym save(Gym gym);

    public void remove(Gym gym);
}
