package org.myboulderlog.client.admin.view;

import org.myboulderlog.shared.proxy.GymProxy;

public interface EditCreateGymDialogBox {
    void show();

    void setPresenter(GymListView.Presenter presenter);

    void setGymProxy(GymProxy gym);
}
