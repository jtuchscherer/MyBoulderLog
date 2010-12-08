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

        // delete
//        if (request.getParameter("id") != null) {
//            deleteMessage(request);
//
//            response.sendRedirect("index");
//
//            return;
//        }

        createMessage("test1");

        createMessage("test2");

        // get
        Collection<Message> messages = this.messageDAO.getAll();
        ArrayList<MessageDTO> messagesDTOs = new ArrayList<MessageDTO>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage(message.getText());
            messagesDTOs.add(messageDTO);
        }

        if (log.isDebugEnabled()) {
            log.debug("messages: " + messages);
        }

        return messagesDTOs;
    }

    protected void createMessage(
            final HttpServletRequest request, final HttpServletResponse response)
    {

        if (log.isDebugEnabled()) {
            log.debug("doPost");
        }

        // create
        createMessage("test");
//        response.sendRedirect("index");
    }

    protected void createMessage(String text) {
        if (log.isDebugEnabled()) {
            log.debug("creating message with text: " + text);
        }

        final Message message = new Message(text);
        this.messageDAO.create(message);
    }

    protected void deleteMessage(final HttpServletRequest request) {
        final Long id = Long.valueOf(request.getParameter("id"));

        if (log.isDebugEnabled()) {
            log.debug("deleting message with id: " + id);
        }

        this.messageDAO.deleteById(id);
    }


}