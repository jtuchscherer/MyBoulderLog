package org.myboulderlog.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.place.MessageDetailPlace;
import org.myboulderlog.shared.dto.MessageDTO;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageListViewImpl extends Composite implements MessageListView {

    private final Logger logger = Logger.getLogger("MessageListViewImpl");

    private final Provider<MessageWidget> messageWidgetProvider;

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
            presenter.addMessage(newMessageTextBox.getText());
        }
    }

    @UiField
    Button addMessageButton;

    @UiHandler("addMessageButton")
    void onClick(ClickEvent event) {
        presenter.addMessage(newMessageTextBox.getText());
    }

    @Inject
    public MessageListViewImpl(
            Provider<MessageWidget> messageWidgetProvider)
    {
        this.messageWidgetProvider = messageWidgetProvider;
        initWidget(uiBinder.createAndBindUi(this));

        //wire it up
        newMessageTextBox.setFocus(true);
    }

    public void resetTestBox() {
        newMessageTextBox.setValue("");
    }

    public void createMessageWidget(final MessageDTO messageDTO) {
        MessageWidget messageWidget = messageWidgetProvider.get();
        messageWidget.setMessage(messageDTO);
        messageWidget.setContainer(this);
        messageListPanel.add(messageWidget);
        widgetMap.put(messageDTO.getId(), messageWidget);
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
