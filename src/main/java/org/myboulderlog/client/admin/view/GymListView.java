package org.myboulderlog.client.admin.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.request.AdminRequestFactory;

public interface GymListView extends IsWidget {

    void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);

        void createButtonClicked();

        void cancelButtonClicked();

        void removeGym(GymProxy gymProxy);

        String getHistoryToken(GymProxy gym);

        void openEditGymDialog(GymProxy gym);

        AdminRequestFactory getRequestFactory();

        void openNewGymDialog();
    }

    /**
     * Exposes CellTable to the presenter so the AsyncDataProvider can update the table.
     * Uses the {@link HasData} interface so as not to leak a Widget into the presenter.
     *
     * @return
     */
    HasData<GymProxy> getDataTable();

    /**
     * Exposes column model to the presenter so it can add a field updater
     *
     * @return List name column
     */
    Column<GymProxy, String> getNameColumn();
}
