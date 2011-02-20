package org.myboulderlog.client.mainapp.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import org.myboulderlog.shared.proxy.RouteProxy;

public interface RouteListView extends IsWidget {
    void setPresenter(Presenter presenter);

    void removeRouteWidgetById(Long routeId);

    void onRouteDetailLinkClicked(RouteProxy routeProxy);

    public void createRouteWidget(final RouteProxy routeProxy);

    public void resetTextBox();

    public void clearRouteList();

    public void deleteRoute(Long id);

    public interface Presenter {
        void goTo(Place place);

        void addRoute(String text);

        void deleteRoute(Long id);
    }
}
