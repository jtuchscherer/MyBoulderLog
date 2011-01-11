package org.myboulderlog.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.place.GoodbyePlace;
import org.myboulderlog.client.view.GoodbyeView;

public class GoodbyeActivity extends AbstractActivity {

    GoodbyeView goodbyeView;

    // Name that will be appended to "Good-bye, "
    private String name;

    @Inject
    public GoodbyeActivity(GoodbyeView goodbyeView) {
        this.goodbyeView = goodbyeView;
    }

    public GoodbyeActivity withPlace(GoodbyePlace place) {
        this.name = place.getGoodbyeName();
        return this;
    }


    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        goodbyeView.setName(name);
        containerWidget.setWidget(goodbyeView.asWidget());
    }
}