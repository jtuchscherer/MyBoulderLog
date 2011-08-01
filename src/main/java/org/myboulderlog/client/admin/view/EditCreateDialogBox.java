package org.myboulderlog.client.admin.view;

import com.google.gwt.editor.client.Editor;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import org.myboulderlog.shared.proxy.DatastoreObjectProxy;

public interface EditCreateDialogBox<P extends DatastoreObjectProxy, E extends Editor<? super P>> {
     void show();

    void hide();

    void setText(String text);

    void setPresenter(GenericListView.Presenter presenter);

    RequestFactoryEditorDriver<P, E> createEditorDriver();
}
