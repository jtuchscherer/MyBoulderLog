package org.myboulderlog.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.entrypoints.Application;
import org.myboulderlog.client.widget.MessageWidget;

import java.util.Collection;

public interface MessageServiceAsync {
    void getMessages(AsyncCallback<Collection<MessageDTO>> async);

    void createMessage(String message, AsyncCallback<MessageDTO> async);

    void deleteMessage(long messageId, AsyncCallback<Long> async);
}
