package org.myboulderlog.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.activity.RouteDetailActivity;
import org.myboulderlog.client.activity.RouteListActivity;
import org.myboulderlog.client.place.RouteListPlace;
import org.myboulderlog.client.place.RouteDetailPlace;

public class AppActivityMapper implements ActivityMapper {

    Provider<RouteListActivity> routeListActivityProvider;
    Provider<RouteDetailActivity> routeDetailActivityProvider;

    @Inject
    public AppActivityMapper(
            final Provider<RouteListActivity> routeListActivityProvider,
            final Provider<RouteDetailActivity> routeDetailActivityProvider)
    {
        super();
        this.routeListActivityProvider = routeListActivityProvider;
        this.routeDetailActivityProvider = routeDetailActivityProvider;
    }

    public Activity getActivity(Place place) {
        // Beg and ye shall receive Gin.
        if (place instanceof RouteListPlace) {
            return routeListActivityProvider.get().withPlace((RouteListPlace) place);
        } else if (place instanceof RouteDetailPlace) {
            return routeDetailActivityProvider.get().withPlace((RouteDetailPlace) place);
        }
        return null;
    }
}