package org.myboulderlog.client.inject;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.activity.GoodbyeActivity;
import org.myboulderlog.client.activity.HelloActivity;
import org.myboulderlog.client.mapper.AppActivityMapper;
import org.myboulderlog.client.mapper.AppPlaceHistoryMapper;
import org.myboulderlog.client.place.HelloPlace;
import org.myboulderlog.client.view.GoodbyeView;
import org.myboulderlog.client.view.HelloViewImpl;
import org.myboulderlog.client.view.MainView;
import org.myboulderlog.client.view.GoodbyeViewImpl;
import org.myboulderlog.client.view.HelloView;
import org.myboulderlog.client.view.MainApplicationWidget;

public class ApplicationClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(MainApplicationWidget.class).in(Singleton.class);
        bind(ApplicationUtils.class).in(Singleton.class);

        bind(MainView.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
        bind(GoodbyeView.class).to(GoodbyeViewImpl.class).in(Singleton.class);
        bind(HelloView.class).to(HelloViewImpl.class).in(Singleton.class);
        bind(HelloActivity.class);
        bind(GoodbyeActivity.class);
    }

    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(
            PlaceController placeController,
            PlaceHistoryMapper historyMapper,
            EventBus eventBus)
    {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new HelloPlace("World!"));
        return historyHandler;
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }
}
