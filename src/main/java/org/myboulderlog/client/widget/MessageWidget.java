package org.myboulderlog.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.service.MessageService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageWidget extends Composite implements ClickHandler {

    private final Logger logger = Logger.getLogger("MessageWidget");

    private static final String DELETE = "Delete";

    final private long messageId;
    private ApplicationUtils applicationUtils = new ApplicationUtils();
    private HorizontalPanel messagePanel;

    public MessageWidget(String message, long messageId) {

        this.messageId = messageId;

        messagePanel = new HorizontalPanel();
        Label label = new Label(message);
        label.setStyleName("messageName");
        Anchor anchor = new Anchor(DELETE);
        anchor.setStyleName("deleteLink");
        anchor.addClickHandler(this);

        messagePanel.add(label);
        messagePanel.add(anchor);

        initWidget(messagePanel);
    }

    public void onClick(ClickEvent event) {
        deleteMessage();
    }

    private void deleteMessage() {
        MessageService.App.getInstance().deleteMessage(messageId, new DeleteMessageAsyncCallback());
    }

    private class DeleteMessageAsyncCallback implements AsyncCallback<Void> {
        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(Void result) {
            //delete message widget from widget list
            logger.log(Level.INFO, "Deleting widget " + messageId);
            messagePanel.getElement().removeFromParent();
        }
    }
}
