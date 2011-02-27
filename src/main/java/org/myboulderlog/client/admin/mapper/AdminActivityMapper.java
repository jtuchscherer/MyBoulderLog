package org.myboulderlog.client.admin.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.admin.activity.AdminOverviewActivity;
import org.myboulderlog.client.admin.activity.GymListActivity;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;
import org.myboulderlog.client.admin.place.GymListPlace;

public class AdminActivityMapper implements ActivityMapper {

    Provider<AdminOverviewActivity> adminOverviewActivityProvider;
    Provider<GymListActivity> gymListActivityProvider;

    @Inject
    public AdminActivityMapper(
            final Provider<AdminOverviewActivity> adminOverviewActivityProvider,
            final Provider<GymListActivity> gymListActivityProvider)
    {
        super();
        this.adminOverviewActivityProvider = adminOverviewActivityProvider;
        this.gymListActivityProvider = gymListActivityProvider;
    }

    public Activity getActivity(Place place) {
        // Beg and ye shall receive Gin.
        if (place instanceof AdminOverviewPlace) {
            return adminOverviewActivityProvider.get().withPlace((AdminOverviewPlace) place);
        } else if (place instanceof GymListPlace) {
            return gymListActivityProvider.get().withPlace((GymListPlace) place);
        }
        return null;
    }
}
