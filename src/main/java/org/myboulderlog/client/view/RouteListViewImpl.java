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
import org.myboulderlog.client.place.RouteDetailPlace;
import org.myboulderlog.shared.proxy.RouteProxy;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RouteListViewImpl extends Composite implements RouteListView {

    private final Logger logger = Logger.getLogger("RouteListViewImpl");

    private final Provider<RouteWidget> routeWidgetProvider;

    private HashMap<Long, Widget> widgetMap = new HashMap<Long, Widget>();
    private RouteListView.Presenter presenter;

    interface MainApplicationWidgetUiBinder extends UiBinder<Widget, RouteListViewImpl> {
    }

    private static MainApplicationWidgetUiBinder uiBinder = GWT.create(MainApplicationWidgetUiBinder.class);

    @UiField
    VerticalPanel routeListPanel = new VerticalPanel();

    @UiField
    TextBox newRouteTextBox;

    @UiHandler("newRouteTextBox")
    void onKeyPress(KeyPressEvent event) {
        if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.addRoute(newRouteTextBox.getText());
        }
    }

    @UiField
    Button addRouteButton;

    @UiHandler("addRouteButton")
    void onClick(ClickEvent event) {
        presenter.addRoute(newRouteTextBox.getText());
    }

    @Inject
    public RouteListViewImpl(
            Provider<RouteWidget> routeWidgetProvider)
    {
        this.routeWidgetProvider = routeWidgetProvider;
        initWidget(uiBinder.createAndBindUi(this));

        //wire it up
        newRouteTextBox.setFocus(true);
    }

    public void resetTextBox() {
        newRouteTextBox.setValue("");
    }

    public void createRouteWidget(final RouteProxy route) {
        RouteWidget routeWidget = routeWidgetProvider.get();
        routeWidget.setRouteProxy(route);
        routeWidget.setContainer(this);
        routeListPanel.add(routeWidget);
        widgetMap.put(route.getId(), routeWidget);
    }

    public void clearRouteList() {
        for (Long widgetId : widgetMap.keySet()) {
            removeRouteWidgetById(widgetId);
        }
    }

    public void deleteRoute(Long id) {
        presenter.deleteRoute(id);
    }

    public void removeRouteWidgetById(Long routeId) {
        //delete route widget from widget list
        logger.log(Level.INFO, "Deleting widget: " + routeId);
        routeListPanel.remove(widgetMap.get(routeId));
    }

    public void onRouteDetailLinkClicked(RouteProxy routeProxy) {
        presenter.goTo(new RouteDetailPlace(routeProxy.getName()));
    }

    public void setPresenter(RouteListView.Presenter presenter) {
        this.presenter = presenter;
    }
}
