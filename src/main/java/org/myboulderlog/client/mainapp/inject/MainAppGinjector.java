package org.myboulderlog.client.mainapp.inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import org.myboulderlog.client.mainapp.view.MainAppView;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;

@GinModules(MainAppClientModule.class)
public interface MainAppGinjector extends Ginjector {

    PlaceHistoryHandler getPlaceHistoryHandler();

    MainAppView getAppWidget();

    EventBus getEventBus();

    BoulderLogRequestFactory getRequestFactory();
}
