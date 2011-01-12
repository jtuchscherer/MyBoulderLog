package org.myboulderlog.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.myboulderlog.client.ApplicationUtils;

import java.util.logging.Logger;

public class MessageWidget extends Composite {

    private static final String DELETE = "Delete";

    interface MessageWidgetUiBinder extends UiBinder<Widget, MessageWidget> {}
    private static MessageWidgetUiBinder uiBinder = GWT.create(MessageWidgetUiBinder.class);

    @UiField
    Label label;

    @UiField
    Anchor anchor;

    public MessageWidget(String message, ClickHandler deleteHandler) {
        initWidget(uiBinder.createAndBindUi(this));

        label.setText(message);
        label.setStyleName("messageName");
        anchor.setText(DELETE);
        anchor.setStyleName("deleteLink");
        anchor.addClickHandler(deleteHandler);

    }

}
