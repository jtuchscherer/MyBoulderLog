package org.myboulderlog.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.activity.GoodbyeActivity;
import org.myboulderlog.client.activity.HelloActivity;
import org.myboulderlog.client.place.GoodbyePlace;
import org.myboulderlog.client.place.HelloPlace;

public class AppActivityMapper implements ActivityMapper {

    Provider<HelloActivity> helloActivityProvider;
    Provider<GoodbyeActivity> goodbyeActivityProvider;

    @Inject
    public AppActivityMapper(
            final Provider<HelloActivity> helloActivityProvider, final Provider<GoodbyeActivity> goodbyeActivityProvider)
    {
        super();
        this.helloActivityProvider = helloActivityProvider;
        this.goodbyeActivityProvider = goodbyeActivityProvider;
    }

    public Activity getActivity(Place place) {
        // Beg and ye shall receive Gin.
        if (place instanceof HelloPlace) {
            return helloActivityProvider.get().withPlace((HelloPlace) place);
        } else if (place instanceof GoodbyePlace) return goodbyeActivityProvider.get().withPlace((GoodbyePlace) place);

        return null;
    }
}