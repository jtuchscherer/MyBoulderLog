package org.myboulderlog.client.admin.activity;

import com.google.web.bindery.requestfactory.shared.Receiver;
import org.myboulderlog.client.mainapp.place.RouteDetailPlace;
import org.myboulderlog.shared.proxy.RouteProxy;
import org.myboulderlog.shared.request.RouteListRequest;

public class RouteListActivity extends GenericListActivity<RouteProxy> {




    public void removeProxy(RouteProxy routeProxy) {
        RouteListRequest routeListRequest = adminRequestFactory.routeListRequest();
        routeListRequest.remove(routeProxy).fire(new Receiver<Void>() {
            @Override
            public void onSuccess(Void response) {
                gymListDataProvider.onRangeChanged(listView.getDataTable());
                logger.fine("Route deleted");
            }
        }
        );
    }

    public String getHistoryToken(RouteProxy proxy) {
        String proxyToken = adminRequestFactory.getHistoryToken(proxy.stableId());
        return adminPlaceHistoryMapper.getToken(new RouteDetailPlace(proxyToken));
    }

    public void openEditGymDialog(RouteProxy proxy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
