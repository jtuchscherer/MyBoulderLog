package org.myboulderlog.client.admin.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;
import org.myboulderlog.client.admin.view.AdminOverviewView;

public class AdminOverviewActivity extends AbstractActivity implements AdminOverviewView.Presenter{

    private AdminOverviewView adminOverviewView;
    PlaceController placeController;

    @Inject
    public AdminOverviewActivity(PlaceController placeController,AdminOverviewView adminOverviewView) {
        this.placeController = placeController;
        this.adminOverviewView = adminOverviewView;
    }

    public AdminOverviewActivity withPlace(AdminOverviewPlace place) {
        return this;
    }

    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        adminOverviewView.setPresenter(this);

        containerWidget.setWidget(adminOverviewView.asWidget());
    }

    public void goTo(Place place) {
       placeController.goTo(place);
    }
}
