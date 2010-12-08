package org.myboulderlog.server;


import com.googlecode.objectify.ObjectifyService;
import org.junit.Before;
import org.junit.Test;
import org.myboulderlog.server.dao.objectify.MessageDAOImpl;
import org.myboulderlog.server.model.Message;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link org.myboulderlog.server.dao.objectify.MessageDAOImpl} class.
 *
 * @author androns
 */
public class MessageDaoTest extends LocalDatastoreTest {

    private MessageDAOImpl messageDAOImpl;

    /**
     * @see LocalDatastoreTest#setUp()
     */
    @Before
    @Override
    public void setUp() {
        super.setUp();
        this.messageDAOImpl = new MessageDAOImpl();
        ObjectifyService.register(Message.class);
    }

    /**
     *
     */
    @Test
    public void smokeTest() {
        assertNotNull(this.messageDAOImpl);

        // create
        final Message message = new Message("Test message");

        this.messageDAOImpl.create(message);

        // read
        Collection<Message> messages = this.messageDAOImpl.getAll();

        assertNotNull(messages);
        assertEquals(1, messages.size());
        final Message storedMessage = messages.iterator().next();

        assertNotNull(storedMessage.getId());
        assertEquals(message.getText(), storedMessage.getText());

        // delete
        this.messageDAOImpl.deleteById(storedMessage.getId());

        messages = this.messageDAOImpl.getAll();
        assertEquals(0, messages.size());
    }
}
