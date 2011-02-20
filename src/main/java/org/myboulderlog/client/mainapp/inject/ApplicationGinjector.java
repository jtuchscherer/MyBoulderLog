package org.myboulderlog.client.mainapp.inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import org.myboulderlog.client.mainapp.view.MainView;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;

@GinModules(ApplicationClientModule.class)
public interface ApplicationGinjector extends Ginjector {

    PlaceHistoryHandler getPlaceHistoryHandler();

    MainView getAppWidget();

    EventBus getEventBus();

    BoulderLogRequestFactory getRequestFactory();
}
