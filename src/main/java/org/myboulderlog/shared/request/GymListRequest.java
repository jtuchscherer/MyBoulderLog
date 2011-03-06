package org.myboulderlog.shared.request;

import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;
import org.myboulderlog.server.dao.GymDAO;
import org.myboulderlog.server.locator.DaoServiceLocator;
import org.myboulderlog.shared.proxy.GymProxy;

import java.util.List;

@Service(value = GymDAO.class, locator = DaoServiceLocator.class)
public interface GymListRequest extends RequestContext {
    Request<List<GymProxy>> listAll();

    Request<Void> save(GymProxy newGym);

    Request<Void> remove(GymProxy gymProxy);
}
