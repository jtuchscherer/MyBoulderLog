package org.myboulderlog.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.myboulderlog.client.activity.MessageDetailActivity;
import org.myboulderlog.client.activity.MessageListActivity;
import org.myboulderlog.client.place.MessageListPlace;
import org.myboulderlog.client.place.RouteDetailPlace;

public class AppActivityMapper implements ActivityMapper {

    Provider<MessageListActivity> messageListActivityProvider;
    Provider<MessageDetailActivity> messageDetailActivityProvider;

    @Inject
    public AppActivityMapper(
            final Provider<MessageListActivity> messageListActivityProvider,
            final Provider<MessageDetailActivity> messageDetailActivityProvider)
    {
        super();
        this.messageListActivityProvider = messageListActivityProvider;
        this.messageDetailActivityProvider = messageDetailActivityProvider;
    }

    public Activity getActivity(Place place) {
        // Beg and ye shall receive Gin.
        if (place instanceof MessageListPlace) {
            return messageListActivityProvider.get().withPlace((MessageListPlace) place);
        } else if (place instanceof RouteDetailPlace) {
            return messageDetailActivityProvider.get().withPlace((RouteDetailPlace) place);
        }
        return null;
    }
}