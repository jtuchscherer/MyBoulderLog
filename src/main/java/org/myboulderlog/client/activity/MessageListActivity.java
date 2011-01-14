package org.myboulderlog.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.place.MessageListPlace;
import org.myboulderlog.client.view.MessageListView;

public class MessageListActivity extends AbstractActivity implements MessageListView.Presenter {

    private MessageListView messageListView;
    PlaceController placeController;

   @Inject
    public MessageListActivity(MessageListView messageListView, PlaceController placeController) {
        this.messageListView = messageListView;
        this.placeController = placeController;
    }

    public MessageListActivity withPlace(MessageListPlace place) {
        return this;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        messageListView.setPresenter(this);
        containerWidget.setWidget(messageListView.asWidget());
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        placeController.goTo(place);
    }
}
