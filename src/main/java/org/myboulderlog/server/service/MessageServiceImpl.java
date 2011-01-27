package org.myboulderlog.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.myboulderlog.shared.dto.MessageDTO;
import org.myboulderlog.client.service.MessageService;
import org.myboulderlog.server.dao.MessageDAO;
import org.myboulderlog.server.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        log.debug("getMessages");

        Collection<Message> messages = this.messageDAO.getAll();
        ArrayList<MessageDTO> messagesDTOs = new ArrayList<MessageDTO>();
        for (Message message : messages) {
            messagesDTOs.add(createMessageDTOFromMessage(message));
        }

        log.debug("messages: " + messages);

        return messagesDTOs;
    }

    public MessageDTO createMessage(String text) {

        log.debug("creating message with text: " + text);

        Message message = new Message(text);
        message = this.messageDAO.create(message);
        return createMessageDTOFromMessage(message);

    }

    public Long deleteMessage(long messageId) {
        log.debug("deleting message with id: " + messageId);

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