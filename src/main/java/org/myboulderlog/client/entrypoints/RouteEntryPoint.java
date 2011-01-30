package org.myboulderlog.client.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.user.client.ui.RootPanel;
import org.myboulderlog.client.inject.ApplicationGinjector;
import org.myboulderlog.shared.proxy.RouteProxy;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;
import org.myboulderlog.shared.request.RouteRequest;

public class RouteEntryPoint implements EntryPoint {

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