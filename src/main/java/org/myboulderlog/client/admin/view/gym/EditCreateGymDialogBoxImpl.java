package org.myboulderlog.client.admin.view.gym;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import org.myboulderlog.shared.proxy.GymProxy;


public class EditCreateGymDialogBoxImpl extends Widget implements EditCreateGymDialogBox {
    private DialogBox rootElement;
    private GymListView.Presenter presenter;

    interface Driver extends RequestFactoryEditorDriver<GymProxy, GymEditor> {
    }


    interface EditCreateGymDialogBoxUiBinder extends UiBinder<DialogBox, EditCreateGymDialogBoxImpl> {
    }

    private static EditCreateGymDialogBoxUiBinder ourUiBinderEdit = GWT.create(EditCreateGymDialogBoxUiBinder.class);


    @UiField
    GymEditor gymEditor;


    @UiField
    Button cancelButton;

    @UiHandler("cancelButton")
    void onCancelClick(ClickEvent event) {
        presenter.cancelButtonClicked();
        gymEditor.clearErrors();
        rootElement.hide();
    }

    @UiField
    Button okButton;

    @UiHandler("okButton")
    void onOkClick(ClickEvent event) {
        presenter.createButtonClicked();
    }

    public EditCreateGymDialogBoxImpl() {
        rootElement = ourUiBinderEdit.createAndBindUi(this);
        rootElement.setModal(true);
        rootElement.setAutoHideEnabled(true);
        rootElement.setAnimationEnabled(true);
        rootElement.setGlassEnabled(true);

    }

    public RequestFactoryEditorDriver<GymProxy, GymEditor> createEditorDriver() {
        RequestFactoryEditorDriver<GymProxy, GymEditor> editorDriver = GWT.create(Driver.class);
        editorDriver.initialize(this.presenter.getRequestFactory(), gymEditor);
        return editorDriver;
    }

    public void show() {
        gymEditor.focus();
        rootElement.center();
        rootElement.show();
    }

    public void hide() {
        rootElement.hide();
    }

    public void setText(String text) {
        rootElement.setText(text);
    }

    public void setPresenter(GymListView.Presenter presenter) {
        this.presenter = presenter;
    }

}