package org.myboulderlog.client.admin.view;

import org.myboulderlog.client.admin.activity.GymListActivity;

public interface CreateGymDialogBox {
    void show();

    void setPresenter(GymListView.Presenter presenter);
}
