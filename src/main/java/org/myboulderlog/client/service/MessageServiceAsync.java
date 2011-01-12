package org.myboulderlog.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.myboulderlog.client.dto.MessageDTO;

import java.util.Collection;

public interface MessageServiceAsync {
    void getMessages(AsyncCallback<Collection<MessageDTO>> async);

    void createMessage(String message, AsyncCallback<MessageDTO> async);

    void deleteMessage(long messageId, AsyncCallback<Long> async);
}
