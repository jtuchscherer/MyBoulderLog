package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.helper.DAOBase;
import org.myboulderlog.server.dao.MessageDAO;
import org.myboulderlog.server.model.Message;

import java.util.Collection;


/**
 */
public class MessageDAOImpl extends DAOBase implements MessageDAO {
    /**
     * @return Collection of Message
     */
    public Collection<Message> getAll() {
        return (ofy().query(Message.class).list());
    }

    /**
     * @param message
     */
    public Message create(final Message message) {
        Key<Message> key = ofy().put(message);
        return ofy().get(key);
    }

    /**
     * @param id
     */
    public void deleteById(final Long id) {
        ofy().delete(Message.class, id.longValue());
    }
}
