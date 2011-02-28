package org.myboulderlog.client.admin.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.inject.Inject;
import org.myboulderlog.client.admin.place.GymListPlace;
import org.myboulderlog.client.admin.view.CreateGymDialogBox;
import org.myboulderlog.client.admin.view.GymListView;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.request.AdminRequestFactory;

import java.util.List;
import java.util.logging.Logger;

public class GymListActivity extends AbstractActivity implements GymListView.Presenter {

    private PlaceController placeController;
    private CreateGymDialogBox createGymDialogBox;
    private GymListView gymListView;
    private GymListDataProvider gymListDataProvider;
    private Logger logger = Logger.getLogger(this.getClass().getName()) ;

    public GymListActivity withPlace(GymListPlace place) {
        return this;
    }

    public static class GymListDataProvider extends AsyncDataProvider<GymProxy> {
        private Logger providerLogger = Logger.getLogger(this.getClass().getName());
        private AdminRequestFactory rf;

        public GymListDataProvider(AdminRequestFactory requestFactory) {
            this.rf = requestFactory;
        }

        @Override
        protected void onRangeChanged(HasData<GymProxy> display) {
            getData();
        }

        private void getData() {
            providerLogger.warning("getting data");
            // To retrieve relations and value types, use .with()
            Request<List<GymProxy>> findAllReq = rf.gymListRequest().listAll().with("owner");
            // Receiver specifies return type
            findAllReq.fire(new Receiver<List<GymProxy>>() {
                @Override
                public void onSuccess(List<GymProxy> response) {
                    updateRowCount(response.size(), true);
                    updateRowData(0, response);
                }
            }
            );
        }
    }

    @Inject
    public GymListActivity(
            PlaceController placeController,
            GymListView gymListView,
            CreateGymDialogBox createGymDialogBox,
            AdminRequestFactory adminRequestFactory)
    {
        this.gymListView = gymListView;
        this.placeController = placeController;
        this.createGymDialogBox = createGymDialogBox;
        this.gymListDataProvider = new GymListDataProvider(adminRequestFactory);
    }

    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        createGymDialogBox.setPresenter(this);
        gymListView.setPresenter(this);
        containerWidget.setWidget(gymListView.asWidget());
        // Triggers listDataProvider#onRangeChanged() to call for data
        gymListDataProvider.addDataDisplay(gymListView.getDataTable());
    }

    public void goTo(Place place) {
        placeController.goTo(place);
    }

    public void createButtonClicked() {
        logger.fine("createButtonclicked");
    }
}
