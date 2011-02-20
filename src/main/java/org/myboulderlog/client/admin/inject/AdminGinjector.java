package org.myboulderlog.client.admin.inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import org.myboulderlog.client.admin.view.MainAdminView;

@GinModules(AdminClientModule.class)
public interface AdminGinjector extends Ginjector {

    PlaceHistoryHandler getPlaceHistoryHandler();

    MainAdminView getAppWidget();

    EventBus getEventBus();
}

