package org.myboulderlog.client.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import org.myboulderlog.client.inject.ApplicationGinjector;

public class HelloMVP implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // Create ClientFactory using deferred binding so we can replace with different
        // impls in gwt.xml
        ApplicationGinjector ginjector = GWT.create(ApplicationGinjector.class);
        RootPanel.get().add(ginjector.getAppWidget());
        // Goes to place represented on URL or default place
        ginjector.getPlaceHistoryHandler().handleCurrentHistory();
    }
}