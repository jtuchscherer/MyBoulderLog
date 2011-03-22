package org.myboulderlog.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import org.myboulderlog.shared.proxy.GymProxy;
import org.myboulderlog.shared.request.GymListRequest;

import java.util.Set;


public class EditCreateGymDialogBoxImpl extends Widget implements EditCreateGymDialogBox {
    private DialogBox rootElement;
    private GymListView.Presenter presenter;
    private GymProxy gym;
    private RequestFactoryEditorDriver editorDriver;

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
        rootElement.hide();
    }

    @UiField
    Button okButton;

    @UiHandler("okButton")
    void onOkClick(ClickEvent event) {
//        presenter.createButtonClicked();
        // Flush the contents of the UI
        RequestContext context = editorDriver.flush();

        // Check for errors
        if (editorDriver.hasErrors()) {
            rootElement.setText("Errors detected locally");
            return;
        }

        // Send the request
        context.fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                // If everything went as planned, just dismiss the dialog box
                rootElement.hide();
            }

            @Override
            public void onViolation(Set<Violation> errors) {
                // Otherwise, show ConstraintViolations in the UI
                rootElement.setText("Errors detected on the server");
                editorDriver.setViolations(errors);
            }
        }
        );

    }

    public EditCreateGymDialogBoxImpl() {
        rootElement = ourUiBinderEdit.createAndBindUi(this);
        rootElement.setModal(true);
        rootElement.setAutoHideEnabled(true);
        rootElement.setAnimationEnabled(true);
        rootElement.setGlassEnabled(true);

    }

    public void show() {
        editorDriver = GWT.create(Driver.class);
        editorDriver.initialize(this.presenter.getRequestFactory(), gymEditor);

        GymListRequest requestContext = this.presenter.getRequestFactory().gymListRequest();
        requestContext.save(this.gym);
        editorDriver.edit(this.gym, requestContext);
        gymEditor.focus();
        rootElement.center();
        rootElement.show();
    }

    public void setPresenter(GymListView.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setGymProxy(GymProxy gym) {
        this.gym = gym;
    }

}