package org.myboulderlog.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.service.MessageService;
import org.myboulderlog.server.dao.MessageDAO;
import org.myboulderlog.server.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

@Singleton
public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private MessageDAO messageDAO;

    @Inject
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Collection<MessageDTO> getMessages() {

        if (log.isDebugEnabled()) {
            log.debug("getMessages");
        }

        Collection<Message> messages = this.messageDAO.getAll();
        ArrayList<MessageDTO> messagesDTOs = new ArrayList<MessageDTO>();
        for (Message message : messages) {
            messagesDTOs.add(createMessageDTOFromMessage(message));
        }

        if (log.isDebugEnabled()) {
            log.debug("messages: " + messages);
        }

        return messagesDTOs;
    }

    public MessageDTO createMessage(String text) {
        if (log.isDebugEnabled()) {
            log.debug("creating message with text: " + text);
        }

        Message message = new Message(text);
        message = this.messageDAO.create(message);
        return createMessageDTOFromMessage(message);

    }

    public Long deleteMessage(long messageId) {
        if (log.isDebugEnabled()) {
            log.debug("deleting message with id: " + messageId);
        }

        this.messageDAO.deleteById(messageId);

        return messageId;
    }

    private MessageDTO createMessageDTOFromMessage(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setMessage(message.getText());
        return messageDTO;
    }

}