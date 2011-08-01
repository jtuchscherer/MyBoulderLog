package org.myboulderlog.shared.request;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface AdminRequestFactory extends RequestFactory {

    GymListRequest gymListRequest();
    RouteListRequest routeListRequest();
}
