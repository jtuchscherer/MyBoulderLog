package org.myboulderlog.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.service.MessageServiceAsync;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainApplicationWidget extends Composite {

    private final Logger logger = Logger.getLogger("MainApplicationWidget");

    private ApplicationUtils applicationUtils;
    private MessageServiceAsync messageSeviceAsync;

    private HashMap<Long, Widget> widgetMap = new HashMap<Long, Widget>();

    final VerticalPanel messageListPanel = new VerticalPanel();
    // Add Panel with text field and button
    HorizontalPanel addMessagePanel;
    TextBox newMessageTextBox;
    Button addMessageButton;

    @Inject
    public MainApplicationWidget(MessageServiceAsync messageSeviceAsync, ApplicationUtils applicationUtils) {
        this.messageSeviceAsync = messageSeviceAsync;
        this.applicationUtils = applicationUtils;
        VerticalPanel panel = new VerticalPanel();
        addMessagePanel = new HorizontalPanel();
        newMessageTextBox = new TextBox();
        addMessageButton = new Button("Add");

        Label label = new Label("Welcome to Google App Engine for Java!");
        panel.add(label);

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
        panel.add(messageListPanel);
        addMessagePanel.add(newMessageTextBox);
        addMessagePanel.add(addMessageButton);
        panel.add(addMessagePanel);
        newMessageTextBox.setFocus(true);
        initWidget(panel);
    }

    public void loadMessages() {
        messageSeviceAsync.getMessages(new GetMessageAsyncCallback());
    }

    private void addMessage(String message) {
        messageSeviceAsync.createMessage(message, new CreateMessageAsyncCallback());
    }

    private void deleteMessage(long messageId) {
        messageSeviceAsync.deleteMessage(messageId, new DeleteMessageAsyncCallback());
    }

    private void createMessageWidget(final MessageDTO messageDTO) {
        ClickHandler deleteHandler = new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteMessage(messageDTO.getId());
            }
        };
        MessageWidget messageWidget = new MessageWidget("Message: " + messageDTO.getMessage(), deleteHandler);
        messageListPanel.add(messageWidget);
        widgetMap.put(messageDTO.getId(), messageWidget);
    }

    private class GetMessageAsyncCallback implements AsyncCallback<Collection<MessageDTO>> {

        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(Collection<MessageDTO> result) {
            for (final MessageDTO messageDTO : result) {
                createMessageWidget(messageDTO);
            }
        }

    }

    private class CreateMessageAsyncCallback implements AsyncCallback<MessageDTO> {

        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(final MessageDTO messageDTO) {
            newMessageTextBox.setValue("");
            createMessageWidget(messageDTO);
        }

    }

    private class DeleteMessageAsyncCallback implements AsyncCallback<Long> {
        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(Long messageId) {
            //delete message widget from widget list
            logger.log(Level.INFO, "Deleting widget: " + messageId);
            messageListPanel.remove(widgetMap.get(messageId));
        }
    }
}
