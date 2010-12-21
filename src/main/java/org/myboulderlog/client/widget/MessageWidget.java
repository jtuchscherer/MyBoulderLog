package org.myboulderlog.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import org.myboulderlog.client.ApplicationUtils;

import java.util.logging.Logger;

public class MessageWidget extends Composite {

    private static final String DELETE = "Delete";

    private ApplicationUtils applicationUtils = new ApplicationUtils();
    private HorizontalPanel messagePanel;

    public MessageWidget(String message, ClickHandler deleteHandler) {

        messagePanel = new HorizontalPanel();
        Label label = new Label(message);
        label.setStyleName("messageName");
        Anchor anchor = new Anchor(DELETE);
        anchor.setStyleName("deleteLink");
        anchor.addClickHandler(deleteHandler);

        messagePanel.add(label);
        messagePanel.add(anchor);

        initWidget(messagePanel);
    }

}
