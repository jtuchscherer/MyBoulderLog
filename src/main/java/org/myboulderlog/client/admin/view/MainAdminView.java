package org.myboulderlog.client.admin.view;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import org.myboulderlog.client.admin.mapper.AdminActivityMapper;

public class MainAdminView extends SimplePanel {

      /**
     * This is a very simple example. In case you have multiple display area,
     * you would inject the ActivityMapper for each display area here in order
     * to register it and the display area widget. In this example, the MainAdminView IS the
     * display area. In a more complex example, this widget would be the container
     * for the other display areas and therefore would know about them.
     *
     * @param mapper
     * @param eventBus
     */
    @Inject
    public MainAdminView(AdminActivityMapper mapper, EventBus eventBus) {
        ActivityManager activityManager = new ActivityManager(mapper, eventBus);
        activityManager.setDisplay(this);
    }
}
