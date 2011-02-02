package org.myboulderlog.client.inject;

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
import org.myboulderlog.client.activity.RouteDetailActivity;
import org.myboulderlog.client.activity.RouteListActivity;
import org.myboulderlog.client.mapper.AppActivityMapper;
import org.myboulderlog.client.mapper.AppPlaceHistoryMapper;
import org.myboulderlog.client.place.RouteListPlace;
import org.myboulderlog.client.view.MainView;
import org.myboulderlog.client.view.RouteDetailView;
import org.myboulderlog.client.view.RouteDetailViewImpl;
import org.myboulderlog.client.view.RouteListView;
import org.myboulderlog.client.view.RouteListViewImpl;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;

public class ApplicationClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainView.class);
        bind(RouteListView.class).to(RouteListViewImpl.class).in(Singleton.class);
        bind(RouteDetailView.class).to(RouteDetailViewImpl.class).in(Singleton.class);
        bind(RouteListActivity.class);
        bind(RouteDetailActivity.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(
            PlaceController placeController, PlaceHistoryMapper historyMapper, EventBus eventBus)
    {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new RouteListPlace());
        return historyHandler;
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    public BoulderLogRequestFactory getRequestFactory(EventBus eventBus) {
        BoulderLogRequestFactory boulderLogRequestFactory = GWT.create(BoulderLogRequestFactory.class);
        boulderLogRequestFactory.initialize(eventBus);
        return boulderLogRequestFactory;
    }
}
