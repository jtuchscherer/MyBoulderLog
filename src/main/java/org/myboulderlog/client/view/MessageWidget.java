package org.myboulderlog.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.place.MessageDetailPlace;
import org.myboulderlog.client.service.MessageServiceAsync;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageWidget extends Composite {

    private final Logger logger = Logger.getLogger("MessageWidget");

    private MessageListView container;
    private MessageDTO message;

    private MessageServiceAsync messageSeviceAsync;
    private ApplicationUtils applicationUtils;

    interface MessageWidgetUiBinder extends UiBinder<Widget, MessageWidget> {
    }
    private static MessageWidgetUiBinder uiBinder = GWT.create(MessageWidgetUiBinder.class);

    @UiField
    Anchor label;

    @UiHandler("label")
    void onIdClick(ClickEvent e) {
        container.onMessageDetailLinkClicked(message);
    }

    @UiField
    Anchor anchor;

    @UiHandler("anchor")
    public void onClick(ClickEvent event) {
        deleteMessage(message.getId());
    }

    @Inject
    public MessageWidget(MessageServiceAsync messageSeviceAsync, ApplicationUtils applicationUtils) {
        this.messageSeviceAsync = messageSeviceAsync;
        this.applicationUtils = applicationUtils;

        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setMessage(MessageDTO messageDTO) {
        this.message = messageDTO;
        label.setText("Message: " + message.getId());
    }

    public void setContainer(MessageListView messageListView) {
        this.container = messageListView;
    }

    private void deleteMessage(long messageId) {
        messageSeviceAsync.deleteMessage(messageId, new DeleteMessageAsyncCallback());
    }


    private class DeleteMessageAsyncCallback implements AsyncCallback<Long> {
        public void onFailure(Throwable caught) {
            applicationUtils.handleFailure(caught);
        }

        public void onSuccess(Long messageId) {
            container.onMessageDeleteSuccess(messageId);
        }
    }
}
