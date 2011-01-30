package org.myboulderlog.shared.request;

import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;
import org.myboulderlog.server.dao.RouteDAO;
import org.myboulderlog.server.locator.DaoServiceLocator;
import org.myboulderlog.shared.proxy.RouteProxy;

import java.util.List;

@Service(value = RouteDAO.class, locator = DaoServiceLocator.class)
public interface RouteRequest extends RequestContext {

    Request<RouteProxy> saveAndReturn(RouteProxy route);

    Request<List<RouteProxy>> listAll();

}
