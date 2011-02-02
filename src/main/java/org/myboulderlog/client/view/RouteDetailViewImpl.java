package org.myboulderlog.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class RouteDetailViewImpl extends Composite implements RouteDetailView {
    SimplePanel viewPanel = new SimplePanel();
    Element routeDetailSpan = DOM.createSpan();

    public RouteDetailViewImpl() {
        viewPanel.getElement().appendChild(routeDetailSpan);
        initWidget(viewPanel);
    }

    public void setName(String name) {
        routeDetailSpan.setInnerText(name);
    }
}