package org.myboulderlog.client.admin.view;

import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import org.myboulderlog.shared.proxy.GymProxy;

public interface EditCreateGymDialogBox {
    void show();

    void hide();

    void setText(String text);

    void setPresenter(GymListView.Presenter presenter);

    RequestFactoryEditorDriver<GymProxy, GymEditor> createEditorDriver();
}
