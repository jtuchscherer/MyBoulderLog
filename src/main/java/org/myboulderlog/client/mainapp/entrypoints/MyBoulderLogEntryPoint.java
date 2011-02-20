package org.myboulderlog.client.mainapp.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import org.myboulderlog.client.mainapp.inject.ApplicationGinjector;

public class MyBoulderLogEntryPoint implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // Create ClientFactory using deferred binding so we can replace with different
        // impls in gwt.xml
        ApplicationGinjector injector = GWT.create(ApplicationGinjector.class);
        RootPanel.get().add(injector.getAppWidget());

        // Goes to place represented on URL or default place
        injector.getPlaceHistoryHandler().handleCurrentHistory();
    }
}