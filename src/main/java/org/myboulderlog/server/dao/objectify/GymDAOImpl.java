package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.Key;
import org.myboulderlog.server.dao.GymDAO;
import org.myboulderlog.server.model.Gym;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GymDAOImpl extends AbstractObjectifyDAO<Gym> implements GymDAO {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Gym save(Gym gym) {
        Key gymKey = this.put(gym);
        try {
            return this.get(gymKey);
        } catch (Exception e) {
            logger.error("Was not able to save and return requested Object", e);
            throw new RuntimeException(e);
        }
    }

    public void remove(Gym gym) {
        this.delete(gym);
    }
}
