package org.myboulderlog.client.mainapp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.mainapp.place.RouteListPlace;
import org.myboulderlog.client.mainapp.view.RouteListView;
import org.myboulderlog.shared.proxy.RouteProxy;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;
import org.myboulderlog.shared.request.RouteRequest;

import java.util.List;

public class RouteListActivity extends AbstractActivity implements RouteListView.Presenter {

    private RouteListView routeListView;
    PlaceController placeController;
    private BoulderLogRequestFactory boulderLogRequestFactory;

    @Inject
    public RouteListActivity(
            RouteListView routeListView,
            PlaceController placeController,
            BoulderLogRequestFactory boulderLogRequestFactory)
    {
        this.routeListView = routeListView;
        this.placeController = placeController;
        this.boulderLogRequestFactory = boulderLogRequestFactory;
    }

    public RouteListActivity withPlace(RouteListPlace place) {
        return this;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        routeListView.setPresenter(this);
        routeListView.clearRouteList();
        RouteRequest routeRequest = boulderLogRequestFactory.routeRequest();
        routeRequest.listAll().fire(new Receiver<List<RouteProxy>>() {
            @Override
            public void onSuccess(List<RouteProxy> response) {
                for (final RouteProxy r : response) {
                    routeListView.createRouteWidget(r);
                }
            }
        }
        );

        containerWidget.setWidget(routeListView.asWidget());
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        placeController.goTo(place);
    }

    public void addRoute(String text) {
        RouteRequest routeRequest = boulderLogRequestFactory.routeRequest();
        RouteProxy newRoute = routeRequest.create(RouteProxy.class);
        newRoute.setName(text);
        routeRequest.saveAndReturn(newRoute).fire(new Receiver<RouteProxy>() {
            @Override
            public void onSuccess(RouteProxy response) {
                routeListView.createRouteWidget(response);
                routeListView.resetTextBox();
            }
        }
        );
    }

    public void deleteRoute(final Long id) {
        RouteRequest routeRequest = boulderLogRequestFactory.routeRequest();
        routeRequest.deleteById(id).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                routeListView.removeRouteWidgetById(id);
            }
        }
        );
    }

}
