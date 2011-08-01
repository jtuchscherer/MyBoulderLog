package org.myboulderlog.server.locator;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.inject.Inject;
import com.googlecode.objectify.helper.DAOBase;
import org.myboulderlog.server.model.AbstractDomainObject;

public class ObjectifyLocator extends Locator<AbstractDomainObject, Long> {

    private DAOBase daoBase;

    @Inject
    public ObjectifyLocator(DAOBase daoBase) {
        this.daoBase = daoBase;
    }

    @Override
    public AbstractDomainObject create(Class<? extends AbstractDomainObject> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public AbstractDomainObject find(Class<? extends AbstractDomainObject> clazz, Long id) {
        return daoBase.ofy().find(clazz, id);
    }

    @Override
    public Class<AbstractDomainObject> getDomainType() {
        // Never called
        return null;
    }

    @Override
    public Long getId(AbstractDomainObject domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public Object getVersion(AbstractDomainObject domainObject) {
        return domainObject.getVersion();
    }

}
