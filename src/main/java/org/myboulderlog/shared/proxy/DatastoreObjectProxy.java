package org.myboulderlog.shared.proxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;

public interface DatastoreObjectProxy extends EntityProxy {

    Long getId();

    Integer getVersion();
}
