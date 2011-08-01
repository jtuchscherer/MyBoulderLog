package org.myboulderlog.client.admin.activity;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.myboulderlog.client.admin.place.GymPreviewPlace;
import org.myboulderlog.client.admin.view.gym.EditCreateGymDialogBox;
import org.myboulderlog.client.admin.view.gym.GymListView;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.request.AdminRequestFactory;
import org.myboulderlog.shared.request.GymListRequest;

public class GymListActivity extends GenericListActivity<GymProxy> {

    @Inject
    public GymListActivity(
            PlaceController placeController,
            GymListView gymListView,
            EditCreateGymDialogBox editCreateGymDialogBox,
            AdminRequestFactory adminRequestFactory,
            PlaceHistoryMapper adminPlaceHistoryMapper) {
        this.listView = gymListView;
        this.placeController = placeController;
        this.editCreateDialogBox = editCreateGymDialogBox;
        this.adminRequestFactory = adminRequestFactory;
        this.adminPlaceHistoryMapper = adminPlaceHistoryMapper;
        this.gymListDataProvider = new GymListDataProvider(adminRequestFactory);
    }

    public void removeProxy(GymProxy gymProxy) {
        GymListRequest gymListRequest = adminRequestFactory.gymListRequest();
        gymListRequest.remove(gymProxy).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                gymListDataProvider.onRangeChanged(listView.getDataTable());
                logger.fine("Gym deleted");
            }
        }
        );
    }

    @Override
    public String getHistoryToken(GymProxy proxy) {
        String proxyToken = adminRequestFactory.getHistoryToken(proxy.stableId());
        return adminPlaceHistoryMapper.getToken(new GymPreviewPlace(proxyToken));
    }
}
