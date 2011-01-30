package org.myboulderlog.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.place.MessageListPlace;
import org.myboulderlog.client.view.MessageListView;
import org.myboulderlog.shared.dto.MessageDTO;
import org.myboulderlog.shared.proxy.RouteProxy;
import org.myboulderlog.shared.request.BoulderLogRequestFactory;
import org.myboulderlog.shared.request.RouteRequest;

import java.util.Collection;
import java.util.List;

public class MessageListActivity extends AbstractActivity implements MessageListView.Presenter {

    private MessageListView messageListView;
    PlaceController placeController;
    private BoulderLogRequestFactory boulderLogRequestFactory;

    @Inject
    public MessageListActivity(
            MessageListView messageListView,
            PlaceController placeController,
            BoulderLogRequestFactory boulderLogRequestFactory)
    {
        this.messageListView = messageListView;
        this.placeController = placeController;
        this.boulderLogRequestFactory = boulderLogRequestFactory;
    }

    public MessageListActivity withPlace(MessageListPlace place) {
        return this;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        messageListView.setPresenter(this);
        RouteRequest routeRequest = boulderLogRequestFactory.routeRequest();
        routeRequest.listAll().fire(new Receiver<List<RouteProxy>>() {
            @Override
            public void onSuccess(List<RouteProxy> response) {
                for (final RouteProxy r : response) {
                    MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setMessage(r.getName());
                    messageDTO.setId(r.getId());

                    messageListView.createMessageWidget(messageDTO);
                }
            }
        }
        );

        containerWidget.setWidget(messageListView.asWidget());
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        placeController.goTo(place);
    }

}
