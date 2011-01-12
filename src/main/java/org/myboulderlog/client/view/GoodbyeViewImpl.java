package org.myboulderlog.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import org.myboulderlog.client.view.GoodbyeView;

public class GoodbyeViewImpl extends Composite implements GoodbyeView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    public GoodbyeViewImpl() {
        viewPanel.getElement().appendChild(nameSpan);
        initWidget(viewPanel);
    }

    public void setName(String name) {
        nameSpan.setInnerText("Good-bye, " + name);
    }
}