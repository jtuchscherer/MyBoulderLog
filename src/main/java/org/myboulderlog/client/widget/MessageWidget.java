package org.myboulderlog.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.service.MessageService;

public class MessageWidget extends Anchor {

    final private long messageId;
    private ApplicationUtils applicationUtils;

    public MessageWidget(String message, long messageId) {
        super(message);

        this.messageId = messageId;

        this.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteMessage();
            }
        });

    }

    private void deleteMessage() {
        MessageService.App.getInstance().deleteMessage(messageId, new DeleteMessageAsyncCallback());
    }

    private class DeleteMessageAsyncCallback implements AsyncCallback<Void> {
        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(Void result) {
            //reload message list
            Window.Location.reload();

        }
    }
}
