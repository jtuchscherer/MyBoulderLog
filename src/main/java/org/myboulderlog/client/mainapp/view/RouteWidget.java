package org.myboulderlog.client.mainapp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import org.myboulderlog.shared.proxy.RouteProxy;

import java.util.logging.Logger;

public class RouteWidget extends Composite {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private RouteListView container;

    private RouteProxy routeProxy;

    interface RouteWidgetUiBinder extends UiBinder<Widget, RouteWidget> {
    }

    private static RouteWidgetUiBinder uiBinder = GWT.create(RouteWidgetUiBinder.class);

    @UiField
    Anchor label;

    @UiHandler("label")
    void onIdClick(ClickEvent e) {
        logger.fine("Clicked on Detail link for route " + routeProxy.getId());
        container.onRouteDetailLinkClicked(routeProxy);
    }

    @UiField
    Anchor anchor;

    @UiHandler("anchor")
    public void onClick(ClickEvent event) {
        logger.fine("Clicked on Delete link for route " + routeProxy.getId());
        container.deleteRoute(routeProxy.getId());
    }

    @Inject
    public RouteWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setRouteProxy(RouteProxy route) {
        this.routeProxy = route;
        label.setText("Route: " + routeProxy.getId());
    }

    public void setContainer(RouteListView routeListView) {
        this.container = routeListView;
    }
}
