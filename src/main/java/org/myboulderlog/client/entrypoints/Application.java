package org.myboulderlog.client.entrypoints;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.myboulderlog.client.ApplicationUtils;
import org.myboulderlog.client.dto.MessageDTO;
import org.myboulderlog.client.inject.ApplicationGinjector;
import org.myboulderlog.client.service.MessageService;
import org.myboulderlog.client.widget.MainApplicationWidget;
import org.myboulderlog.client.widget.MessageWidget;

import java.util.Collection;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

    private final ApplicationGinjector injector = GWT.create(ApplicationGinjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        MainApplicationWidget mainApplicationWidget = injector.getMainApplicationPanel();
        mainApplicationWidget.loadMessages();
        RootPanel.get().add(mainApplicationWidget);
    }
}
