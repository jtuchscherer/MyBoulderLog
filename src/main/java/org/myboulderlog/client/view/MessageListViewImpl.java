package org.myboulderlog.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.shared.dto.MessageDTO;
import org.myboulderlog.client.place.MessageDetailPlace;
import org.myboulderlog.client.service.MessageServiceAsync;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageListViewImpl extends Composite implements MessageListView {

    private final Logger logger = Logger.getLogger("MessageListViewImpl");

    private final Provider<MessageWidget> messageWidgetProvider;
    private MessageServiceAsync messageSeviceAsync;
    private ApplicationUtils applicationUtils;

    private HashMap<Long, Widget> widgetMap = new HashMap<Long, Widget>();
    private MessageListView.Presenter presenter;

    interface MainApplicationWidgetUiBinder extends UiBinder<Widget, MessageListViewImpl> {
    }

    private static MainApplicationWidgetUiBinder uiBinder = GWT.create(MainApplicationWidgetUiBinder.class);

    @UiField
    VerticalPanel messageListPanel = new VerticalPanel();

    @UiField
    TextBox newMessageTextBox;

    @UiHandler("newMessageTextBox")
    void onKeyPress(KeyPressEvent event) {
        if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
            addMessage(newMessageTextBox.getText());
        }
    }

    @UiField
    Button addMessageButton;

    @UiHandler("addMessageButton")
    void onClick(ClickEvent event) {
        addMessage(newMessageTextBox.getText());
    }

    @Inject
    public MessageListViewImpl(
            Provider<MessageWidget> messageWidgetProvider,
            MessageServiceAsync messageSeviceAsync,
            ApplicationUtils applicationUtils)
    {
        this.messageWidgetProvider = messageWidgetProvider;
        this.messageSeviceAsync = messageSeviceAsync;
        this.applicationUtils = applicationUtils;

        initWidget(uiBinder.createAndBindUi(this));

        loadMessages();

        //wire it up
        newMessageTextBox.setFocus(true);
    }

    public void loadMessages() {
        messageSeviceAsync.getMessages(new GetMessageAsyncCallback());
    }

    private void addMessage(String message) {
        messageSeviceAsync.createMessage(message, new CreateMessageAsyncCallback());
    }

    private void createMessageWidget(final MessageDTO messageDTO) {
        MessageWidget messageWidget = messageWidgetProvider.get();
        messageWidget.setMessage(messageDTO);
        messageWidget.setContainer(this);
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

    public void setPresenter(MessageListView.Presenter presenter) {
        this.presenter = presenter;
    }

    public void onMessageDeleteSuccess(Long messageId) {
        //delete message widget from widget list
        logger.log(Level.INFO, "Deleting widget: " + messageId);
        messageListPanel.remove(widgetMap.get(messageId));
    }

    public void onMessageDetailLinkClicked(MessageDTO message) {
        presenter.goTo(new MessageDetailPlace(message.getMessage()));
    }
}
