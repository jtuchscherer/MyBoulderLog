package org.myboulderlog.client.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.service.MessageService;

import java.util.Collection;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Label label = new Label("gwt-maven-plugin Archetype :: Project org.codehaus.mojo.gwt-maven-plugin");
        RootPanel.get().add(label);
        MessageService.App.getInstance().getMessages(new GetMessageAsyncCallback());
    }

    private class GetMessageAsyncCallback implements AsyncCallback<Collection<MessageDTO>> {
        public void onFailure(Throwable caught) {
            final Label label = new Label("Failure with the server commnunication: " + caught.getMessage());
            RootPanel.get().add(label);
        }

        public void onSuccess(Collection<MessageDTO> result) {
            Integer counter = 1;
            for (MessageDTO messageDTO : result) {
                final Label label = new Label(counter++ + ". Message: " + messageDTO.getMessage());
                RootPanel.get().add(label);
            }

        }
    }
}
