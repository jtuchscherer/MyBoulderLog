package org.myboulderlog.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import org.myboulderlog.shared.dto.MessageDTO;

public interface MessageListView extends IsWidget {
    void setPresenter(Presenter presenter);

    void onMessageDeleteSuccess(Long messageId);

    void onMessageDetailLinkClicked(MessageDTO message);

    public void createMessageWidget(final MessageDTO messageDTO);

    public void resetTestBox();

    public interface Presenter {
        void goTo(Place place);

        void addMessage(String text);
    }
}
