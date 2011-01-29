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
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.activity.MessageDetailActivity;
import org.myboulderlog.client.activity.MessageListActivity;
import org.myboulderlog.client.mapper.AppActivityMapper;
import org.myboulderlog.client.mapper.AppPlaceHistoryMapper;
import org.myboulderlog.client.place.MessageListPlace;
import org.myboulderlog.client.view.MessageDetailView;
import org.myboulderlog.client.view.MessageDetailViewImpl;
import org.myboulderlog.client.view.MessageListView;
import org.myboulderlog.client.view.MessageListViewImpl;
import org.myboulderlog.client.view.MainView;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;

public class ApplicationClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(ApplicationUtils.class).in(Singleton.class);

        bind(MainView.class);
        bind(MessageListView.class).to(MessageListViewImpl.class).in(Singleton.class);
        bind(MessageDetailView.class).to(MessageDetailViewImpl.class).in(Singleton.class);
        bind(MessageListActivity.class);
        bind(MessageDetailActivity.class);
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
        bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(
            PlaceController placeController,
            PlaceHistoryMapper historyMapper,
            EventBus eventBus)
    {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new MessageListPlace());
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
