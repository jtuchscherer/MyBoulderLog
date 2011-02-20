package org.myboulderlog.client.admin.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.admin.activity.AdminOverviewActivity;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;

public class AdminActivityMapper implements ActivityMapper {

    Provider<AdminOverviewActivity> adminOverviewActivityProvider;

    @Inject
    public AdminActivityMapper(
            final Provider<AdminOverviewActivity> adminOverviewActivityProvider)
    {
        super();
        this.adminOverviewActivityProvider = adminOverviewActivityProvider;
    }

    public Activity getActivity(Place place) {
        // Beg and ye shall receive Gin.
        if (place instanceof AdminOverviewPlace) {
            return adminOverviewActivityProvider.get().withPlace((AdminOverviewPlace) place);
        }
        return null;
    }
}
