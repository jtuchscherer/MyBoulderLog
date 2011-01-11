package org.myboulderlog.client.inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import org.myboulderlog.client.view.GoodbyeView;
import org.myboulderlog.client.view.HelloView;
import org.myboulderlog.client.view.MainView;
import org.myboulderlog.client.widget.MainApplicationWidget;

@GinModules(ApplicationClientModule.class)
public interface ApplicationGinjector extends Ginjector {
    MainApplicationWidget getMainApplicationPanel();
    PlaceHistoryHandler getPlaceHistoryHandler();
    MainView getAppWidget();

}
