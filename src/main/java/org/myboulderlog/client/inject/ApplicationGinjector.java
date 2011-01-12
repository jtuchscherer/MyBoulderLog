package org.myboulderlog.client.inject;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import org.myboulderlog.client.view.MainView;
import org.myboulderlog.client.view.MainApplicationWidget;

@GinModules(ApplicationClientModule.class)
public interface ApplicationGinjector extends Ginjector {
    MainApplicationWidget getMainApplicationPanel();
    PlaceHistoryHandler getPlaceHistoryHandler();
    MainView getAppWidget();

}
