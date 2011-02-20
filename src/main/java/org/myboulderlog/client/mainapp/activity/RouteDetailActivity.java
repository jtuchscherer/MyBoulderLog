package org.myboulderlog.client.mainapp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.mainapp.place.RouteDetailPlace;
import org.myboulderlog.client.mainapp.view.RouteDetailView;

public class RouteDetailActivity extends AbstractActivity {

    RouteDetailView routeDetailView;

    private String routeDetail;

    @Inject
    public RouteDetailActivity(RouteDetailView routeDetailView) {
        this.routeDetailView = routeDetailView;
    }

    public RouteDetailActivity withPlace(RouteDetailPlace place) {
        this.routeDetail = place.getRouteName();
        return this;
    }


    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        routeDetailView.setName(routeDetail);
        containerWidget.setWidget(routeDetailView.asWidget());
    }
}