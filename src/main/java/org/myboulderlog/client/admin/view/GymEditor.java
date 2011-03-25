package org.myboulderlog.client.admin.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;
import org.myboulderlog.shared.proxy.GymProxy;

import java.util.ArrayList;

public class GymEditor extends Composite implements Editor<GymProxy> {

    public void clearErrors() {
        ArrayList<EditorError> emptyErrorList = new ArrayList<EditorError>();
        name.showErrors(emptyErrorList);
        description.showErrors(emptyErrorList);
    }

    interface Binder extends UiBinder<Widget, GymEditor> {
    }

    @UiField
    RichTextAreaEditorDecorator<String> description;

    @Ignore
    @UiField(provided = true)
    RichTextAreaEditor descriptionRichTextArea;


    @UiField
    ValueBoxEditorDecorator<String> name;

    @UiField
    Focusable nameBox;

    @UiField(provided = true)
    RichTextToolbar toolbar;

    public GymEditor() {
        descriptionRichTextArea = new RichTextAreaEditor();
        toolbar = new RichTextToolbar(descriptionRichTextArea);
        initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));
        toolbar.setWidth("100%");
    }

    public void focus() {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            public void execute() {
                nameBox.setFocus(true);
            }
        }
        );
    }
}
