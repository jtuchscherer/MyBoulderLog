package org.myboulderlog.server.dao.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import org.myboulderlog.server.dao.MessageDAO;
import org.myboulderlog.server.model.Message;

import java.util.Collection;


/**
 */
public class MessageDAOImpl implements MessageDAO {
    /**
     * @return Collection of Message
     */
    public Collection<Message> getAll() {
        final Objectify service = getService();

        return (service.query(Message.class).list());
    }

    /**
     * @param message
     */
    public Message create(final Message message) {
        final Objectify service = getService();

        Key<Message> key = service.put(message);
        return service.get(key);
    }

    /**
     * @param id
     */
    public void deleteById(final Long id) {
        final Objectify service = getService();

        service.delete(Message.class, id.longValue());
    }

    private Objectify getService() {
        return (ObjectifyService.begin());
    }
}
