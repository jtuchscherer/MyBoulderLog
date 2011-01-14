package org.myboulderlog.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.place.MessageDetailPlace;
import org.myboulderlog.client.view.MessageDetailView;

public class MessageDetailActivity extends AbstractActivity {

    MessageDetailView messageDetailView;

    private String messageDetail;

    @Inject
    public MessageDetailActivity(MessageDetailView messageDetailView) {
        this.messageDetailView = messageDetailView;
    }

    public MessageDetailActivity withPlace(MessageDetailPlace place) {
        this.messageDetail = place.getMessageName();
        return this;
    }


    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        messageDetailView.setName(messageDetail);
        containerWidget.setWidget(messageDetailView.asWidget());
    }
}