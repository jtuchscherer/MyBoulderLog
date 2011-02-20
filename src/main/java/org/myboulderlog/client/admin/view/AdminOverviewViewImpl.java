package org.myboulderlog.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;

public class AdminOverviewViewImpl extends Composite implements AdminOverviewView {

    private AdminOverviewView.Presenter presenter;

    interface WidgetUiBinder extends UiBinder<Widget, AdminOverviewViewImpl> {
    }

    private static WidgetUiBinder uiBinder = GWT.create(WidgetUiBinder.class);

    @UiField
    Anchor users;

    @UiHandler("users")
    void onUsersClick(ClickEvent event) {
        presenter.goTo(new AdminOverviewPlace());
    }

    @UiField
    Anchor routes;

    @UiHandler("routes")
    void onRoutesClick(ClickEvent event) {
        presenter.goTo(new AdminOverviewPlace());
    }

    @UiField
    Anchor areas;

    @UiHandler("areas")
    void onAreasClick(ClickEvent event) {
        presenter.goTo(new AdminOverviewPlace());
    }

    @UiField
    Anchor gyms;

    @UiHandler("gyms")
    void onGymsClick(ClickEvent event) {
        presenter.goTo(new AdminOverviewPlace());
    }

    public AdminOverviewViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setPresenter(AdminOverviewView.Presenter presenter) {
        this.presenter = presenter;
    }
}
