package org.myboulderlog.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.myboulderlog.shared.dto.MessageDTO;

import java.util.Collection;

@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService {

    public Collection<MessageDTO> getMessages();

    public MessageDTO createMessage(String message);

    public Long deleteMessage(long messageId);
}
