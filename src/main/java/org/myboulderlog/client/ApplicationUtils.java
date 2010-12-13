package org.myboulderlog.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class ApplicationUtils {
    public ApplicationUtils() { }

    public void handleFailure(Throwable caught) {
        final Label label = new Label("Failure with the server commnunication: " + caught.getMessage());
        RootPanel.get().add(label);
    }
}