package org.myboulderlog.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import org.myboulderlog.client.dto.MessageDTO;

public interface MessageListView extends IsWidget {
    void setPresenter(Presenter presenter);

    void onMessageDeleteSuccess(Long messageId);

    void onMessageDetailLinkClicked(MessageDTO message);

    public interface Presenter {
        void goTo(Place place);
    }
}
