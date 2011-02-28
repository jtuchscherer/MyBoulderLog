package org.myboulderlog.client.admin.inject;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.myboulderlog.client.admin.mapper.AdminActivityMapper;
import org.myboulderlog.client.admin.mapper.AdminPlaceHistoryMapper;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;
import org.myboulderlog.client.admin.view.AdminOverviewViewImpl;
import org.myboulderlog.client.admin.view.AdminOverviewView;
import org.myboulderlog.client.admin.view.CreateGymDialogBox;
import org.myboulderlog.client.admin.view.CreateGymDialogBoxImpl;
import org.myboulderlog.client.admin.view.GymListView;
import org.myboulderlog.client.admin.view.GymListViewImpl;
import org.myboulderlog.client.admin.view.MainAdminView;
import org.myboulderlog.shared.proxy.GymProxyKeyProvider;
import org.myboulderlog.shared.request.AdminRequestFactory;

public class AdminClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainAdminView.class);
        bind(GymProxyKeyProvider.class).in(Singleton.class);
        bind(AdminOverviewView.class).to(AdminOverviewViewImpl.class).in(Singleton.class);
        bind(GymListView.class).to(GymListViewImpl.class).in(Singleton.class);
        bind(CreateGymDialogBox.class).to(CreateGymDialogBoxImpl.class).in(Singleton.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceHistoryMapper.class).to(AdminPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(AdminActivityMapper.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(
            PlaceController placeController, PlaceHistoryMapper historyMapper, EventBus eventBus)
    {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new AdminOverviewPlace());
        return historyHandler;
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    public AdminRequestFactory getRequestFactory(EventBus eventBus) {
        AdminRequestFactory adminRequestFactory = GWT.create(AdminRequestFactory.class);
        adminRequestFactory.initialize(eventBus);
        return adminRequestFactory;
    }
}
