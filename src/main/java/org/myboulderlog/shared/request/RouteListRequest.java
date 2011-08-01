package org.myboulderlog.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import org.myboulderlog.server.dao.RouteDAO;
import org.myboulderlog.server.locator.DaoServiceLocator;
import org.myboulderlog.shared.proxy.RouteProxy;

import java.util.List;

@Service(value = RouteDAO.class, locator = DaoServiceLocator.class)
public interface RouteListRequest extends RequestContext {
    Request<List<RouteProxy>> listAll();

    Request<RouteProxy> save(RouteProxy newRoute);

    Request<Void> remove(RouteProxy routeProxy);
}
