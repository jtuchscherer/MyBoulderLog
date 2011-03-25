package org.myboulderlog.client.admin.view;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.RichTextArea;

public class RichTextAreaEditor extends RichTextArea implements LeafValueEditor<String> {


    /**
     * Returns an Editor that is backed by the ValueBoxBase. The default
     * implementation returns {@link com.google.gwt.editor.ui.client.adapters.ValueBoxEditor#of(com.google.gwt.user.client.ui.ValueBoxBase)}. Subclasses
     * may override this method to provide custom error-handling when using the
     * Editor framework.
     */
    public RichTextAreaEditor asEditor() {
        return this;
    }

    public String getValue() {
        return this.getHTML();
    }

    public void setValue(String text) {
        this.setHTML(text);
    }
}
