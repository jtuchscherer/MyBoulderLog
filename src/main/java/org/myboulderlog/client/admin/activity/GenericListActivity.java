package org.myboulderlog.client.admin.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import org.myboulderlog.client.admin.place.GymPreviewPlace;
import org.myboulderlog.client.admin.view.gym.EditCreateGymDialogBox;
import org.myboulderlog.client.admin.view.gym.GymEditor;
import org.myboulderlog.client.admin.view.gym.GymListView;
import org.myboulderlog.shared.proxy.DatastoreObjectProxy;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.request.AdminRequestFactory;
import org.myboulderlog.shared.request.GymListRequest;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public abstract class GenericListActivity<T extends DatastoreObjectProxy> extends AbstractActivity {

    protected PlaceController placeController;
    protected EditCreateGymDialogBox editCreateGymDialogBox;
    protected AdminRequestFactory adminRequestFactory;
    protected PlaceHistoryMapper adminPlaceHistoryMapper;
    protected GymListView gymListView;
    protected GymListDataProvider gymListDataProvider;
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    protected RequestFactoryEditorDriver<GymProxy, GymEditor> editorDriver;

    public GenericListActivity withPlace(Place place) {
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

    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        editCreateGymDialogBox.setPresenter(this);
        gymListView.setPresenter(this);
        containerWidget.setWidget(gymListView.asWidget());
        // Triggers listDataProvider#onRangeChanged() to call for data
        gymListDataProvider.addDataDisplay(gymListView.getDataTable());
    }

    public void goTo(Place place) {
        placeController.goTo(place);
    }

    public void cancelButtonClicked() {
        editorDriver = null;
    }

    public void createButtonClicked() {
        logger.fine("createButtonClicked");

        RequestContext context = editorDriver.flush();

        // Check for errors
        if (editorDriver.hasErrors()) {
            editCreateGymDialogBox.setText("Errors detected locally");
            return;
        }

        // Send the request
        context.fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                // If everything went as planned, just dismiss the dialog box
                editCreateGymDialogBox.hide();
                gymListDataProvider.onRangeChanged(gymListView.getDataTable());
                logger.fine("Gym created");
            }

            @Override
            public void onViolation(Set<Violation> errors) {
                // Otherwise, show ConstraintViolations in the UI
                editCreateGymDialogBox.setText("Errors detected on the server");
                editorDriver.setViolations(errors);
            }
        }
        );
    }

    public void removeGym(GymProxy gymProxy) {
        GymListRequest gymListRequest = adminRequestFactory.gymListRequest();
        gymListRequest.remove(gymProxy).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                gymListDataProvider.onRangeChanged(gymListView.getDataTable());
                logger.fine("Gym deleted");
            }
        }
        );
    }

    public void openEditGymDialog(GymProxy gym) {
        GymListRequest requestContext = adminRequestFactory.gymListRequest();
        prepareAndShowDialog(gym, requestContext);
    }

    private void prepareAndShowDialog(GymProxy gym, GymListRequest requestContext) {
        editorDriver = editCreateGymDialogBox.createEditorDriver();
        requestContext.save(gym);
        editorDriver.edit(gym, requestContext);
        editCreateGymDialogBox.show();
    }

    public void openNewGymDialog() {
        GymListRequest gymListRequest = adminRequestFactory.gymListRequest();
        GymProxy newGym = gymListRequest.create(GymProxy.class);
        newGym.setName("The spot");
        prepareAndShowDialog(newGym,gymListRequest);
    }

    public String getHistoryToken(T proxy) {
        String proxyToken = adminRequestFactory.getHistoryToken(proxy.stableId());
        return adminPlaceHistoryMapper.getToken(new GymPreviewPlace(proxyToken));
    }

    public AdminRequestFactory getRequestFactory() {
        return adminRequestFactory;
    }

}
