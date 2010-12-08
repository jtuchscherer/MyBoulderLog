package org.myboulderlog.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.myboulderlog.client.dto.MessageDTO;

import java.util.Collection;

@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use MessageService.App.getInstance() to access static instance of MessageServiceAsync
     */
    public static class App {
        private static final MessageServiceAsync ourInstance = (MessageServiceAsync) GWT.create(MessageService.class);

        public static MessageServiceAsync getInstance() {
            return ourInstance;
        }
    }

    public Collection<MessageDTO> getMessages();
}
