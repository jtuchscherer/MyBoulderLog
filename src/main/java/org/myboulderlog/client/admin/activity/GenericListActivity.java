package org.myboulderlog.client.admin.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Violation;
import org.myboulderlog.client.admin.view.EditCreateDialogBox;
import org.myboulderlog.client.admin.view.GenericListView;
import org.myboulderlog.client.admin.view.gym.GymEditor;
import org.myboulderlog.shared.proxy.DatastoreObjectProxy;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.request.AdminRequestFactory;
import org.myboulderlog.shared.request.GymListRequest;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public abstract class GenericListActivity<T extends DatastoreObjectProxy> extends AbstractActivity implements GenericListView.Presenter<T>{

    protected PlaceController placeController;
    protected EditCreateDialogBox editCreateDialogBox;
    protected AdminRequestFactory adminRequestFactory;
    protected PlaceHistoryMapper adminPlaceHistoryMapper;
    protected GenericListView listView;
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
        editCreateDialogBox.setPresenter(this);
        listView.setPresenter(this);
        containerWidget.setWidget(listView.asWidget());
        // Triggers listDataProvider#onRangeChanged() to call for data
        gymListDataProvider.addDataDisplay(listView.getDataTable());
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
            editCreateDialogBox.setText("Errors detected locally");
            return;
        }

        // Send the request
        context.fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                // If everything went as planned, just dismiss the dialog box
                editCreateDialogBox.hide();
                gymListDataProvider.onRangeChanged(listView.getDataTable());
                logger.fine("Gym created");
            }

            @Override
            public void onViolation(Set<Violation> errors) {
                // Otherwise, show ConstraintViolations in the UI
                editCreateDialogBox.setText("Errors detected on the server");
                editorDriver.setViolations(errors);
            }
        }
        );
    }

    public abstract void removeProxy(T proxy);

    public void openEditGymDialog(GymProxy gym) {
        GymListRequest requestContext = adminRequestFactory.gymListRequest();
        prepareAndShowDialog(gym, requestContext);
    }

    private void prepareAndShowDialog(GymProxy gym, GymListRequest requestContext) {
        editorDriver = editCreateDialogBox.createEditorDriver();
        requestContext.save(gym);
        editorDriver.edit(gym, requestContext);
        editCreateDialogBox.show();
    }

    public void openNewGymDialog() {
        GymListRequest gymListRequest = adminRequestFactory.gymListRequest();
        GymProxy newGym = gymListRequest.create(GymProxy.class);
        newGym.setName("The spot");
        prepareAndShowDialog(newGym,gymListRequest);
    }

    public abstract String getHistoryToken(T proxy);

    public AdminRequestFactory getRequestFactory() {
        return adminRequestFactory;
    }

}
