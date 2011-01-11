package org.myboulderlog.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.place.HelloPlace;
import org.myboulderlog.client.view.HelloView;

public class HelloActivity extends AbstractActivity implements HelloView.Presenter {

    private HelloView helloView;
    PlaceController placeController;

    // Name that will be appended to "Hello,"
    private String name;

    @Inject
    public HelloActivity(HelloView helloView, PlaceController placeController) {
        this.helloView = helloView;
        this.placeController = placeController;
    }

    public HelloActivity withPlace(HelloPlace place) {
        this.name = place.getHelloName();
        return this;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        helloView.setName(name);
        helloView.setPresenter(this);
        containerWidget.setWidget(helloView.asWidget());
    }

    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
        return "Please hold on. This activity is stopping.";
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        placeController.goTo(place);
    }
}
