package org.myboulderlog.client.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.service.MessageService;
import org.myboulderlog.client.widget.MessageWidget;

import java.util.Collection;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

    final VerticalPanel messageListPanel = new VerticalPanel();

    // Add Panel with text field and button
    final HorizontalPanel addMessagePanel = new HorizontalPanel();
    final TextBox newMessageTextBox = new TextBox();
    final Button addMessageButton = new Button("Add");
    Integer counter = 1;
    private final ApplicationUtils applicationUtils = new ApplicationUtils();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Label label = new Label("Welcome to Google App Engine for Java!");
        RootPanel.get().add(label);
        MessageService.App.getInstance().getMessages(new GetMessageAsyncCallback());
    }

    private class GetMessageAsyncCallback implements AsyncCallback<Collection<MessageDTO>> {
        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(Collection<MessageDTO> result) {
            for (MessageDTO messageDTO : result) {
                MessageWidget messageWidget = new MessageWidget(counter++ + ". Message: " + messageDTO.getMessage(), messageDTO.getId());
                messageListPanel.add(messageWidget);
            }
            // Listen for keyboard events in the input box.
            newMessageTextBox.addKeyPressHandler(new KeyPressHandler() {
                public void onKeyPress(KeyPressEvent event) {
                    if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                        addMessage(newMessageTextBox.getText());
                    }
                }
            }
            );
            // Listen for mouse events on the Add button.
            addMessageButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    addMessage(newMessageTextBox.getText());
                }
            }
            );
            //wire it up
            RootPanel.get().add(messageListPanel);
            addMessagePanel.add(newMessageTextBox);
            addMessagePanel.add(addMessageButton);
            RootPanel.get().add(addMessagePanel);
            newMessageTextBox.setFocus(true);

        }
    }

    private void addMessage(String message) {
        MessageService.App.getInstance().createMessage(message, new CreateMessageAsyncCallback());
    }

    private class CreateMessageAsyncCallback implements AsyncCallback<MessageDTO> {
        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(MessageDTO messageDTO) {
            newMessageTextBox.setValue("");
            MessageWidget messageWidget = new MessageWidget(counter++ + ". Message: " + messageDTO.getMessage(), messageDTO.getId());
            messageListPanel.add(messageWidget);
        }
    }
}
