package org.myboulderlog.client.inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import org.myboulderlog.client.view.MainView;
import org.myboulderlog.client.view.MessageListView;
import org.myboulderlog.client.view.MessageListViewImpl;

@GinModules(ApplicationClientModule.class)
public interface ApplicationGinjector extends Ginjector {

    PlaceHistoryHandler getPlaceHistoryHandler();
    MainView getAppWidget();
    EventBus getEventBus();
}
