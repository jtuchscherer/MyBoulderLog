package org.myboulderlog.shared.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import org.myboulderlog.shared.proxy.DatastoreObjectProxy;

import java.util.List;

public interface GenericListRequest<T extends DatastoreObjectProxy> extends RequestContext {
    Request<List<T>> listAll();

    Request<T> save(T newObjectProxy);

    Request<Void> remove(T objectProxy);
}
