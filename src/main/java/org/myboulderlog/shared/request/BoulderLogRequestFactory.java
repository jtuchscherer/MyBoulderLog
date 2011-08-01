package org.myboulderlog.shared.request;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface BoulderLogRequestFactory extends RequestFactory {

    RouteRequest routeRequest();

}