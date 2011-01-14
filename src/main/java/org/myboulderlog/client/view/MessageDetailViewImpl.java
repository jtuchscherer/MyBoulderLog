package org.myboulderlog.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class MessageDetailViewImpl extends Composite implements MessageDetailView {
    SimplePanel viewPanel = new SimplePanel();
    Element messageDetailSpan = DOM.createSpan();

    public MessageDetailViewImpl() {
        viewPanel.getElement().appendChild(messageDetailSpan);
        initWidget(viewPanel);
    }

    public void setName(String name) {
        messageDetailSpan.setInnerText(name);
    }
}