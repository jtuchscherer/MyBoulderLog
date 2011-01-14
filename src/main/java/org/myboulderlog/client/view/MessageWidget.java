package org.myboulderlog.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.place.MessageDetailPlace;

public class MessageWidget extends Composite {

    private static final String DELETE = "Delete";
    private MessageDTO message;
    private MessageListView.Presenter presenter;

    interface MessageWidgetUiBinder extends UiBinder<Widget, MessageWidget> {}
    private static MessageWidgetUiBinder uiBinder = GWT.create(MessageWidgetUiBinder.class);

    @UiField
    Anchor label;

    @UiHandler("label")
    void onIdClick(ClickEvent e) {
        System.out.println("message = " + message.getMessage());
        getPresenter().goTo(new MessageDetailPlace(message.getMessage()));
    }

    @UiField
    Anchor anchor;

    public MessageWidget(MessageDTO message, ClickHandler deleteHandler, MessageListView.Presenter presenter) {
        this.message = message;
        this.presenter = presenter;

        initWidget(uiBinder.createAndBindUi(this));

        label.setText("Message: " + message.getId());
        label.setStyleName("messageName");
        anchor.setText(DELETE);
        anchor.setStyleName("deleteLink");
        anchor.addClickHandler(deleteHandler);

    }

    public MessageListView.Presenter getPresenter() {
        return presenter;
    }
}
