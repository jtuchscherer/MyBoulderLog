package org.myboulderlog.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class CreateGymDialogBoxImpl extends Widget implements CreateGymDialogBox {
    private DialogBox rootElement;
    private GymListView.Presenter presenter;

    interface CreateGymDialogBoxUiBinder extends UiBinder<DialogBox, CreateGymDialogBoxImpl> {
    }

    private static CreateGymDialogBoxUiBinder ourUiBinder = GWT.create(CreateGymDialogBoxUiBinder.class);

    @UiField
    Button cancelButton;

    @UiHandler("cancelButton")
    void onCancelClick(ClickEvent event) {
        rootElement.hide();
    }

    @UiField
    Button okButton;

    @UiHandler("okButton")
    void onOkClick(ClickEvent event) {
        presenter.createButtonClicked();
    }

    public CreateGymDialogBoxImpl() {
        rootElement = ourUiBinder.createAndBindUi(this);
        rootElement.setModal(true);
        rootElement.setAutoHideEnabled(true);
        rootElement.setAnimationEnabled(true);
        rootElement.setGlassEnabled(true);
        rootElement.setPopupPosition(100, 100);

    }

    public void show() {
        rootElement.show();
    }

    public void setPresenter(GymListView.Presenter presenter) {
        this.presenter = presenter;
    }
}