package org.myboulderlog.server.locator;

import com.google.gwt.requestfactory.shared.Locator;
import com.googlecode.objectify.helper.DAOBase;
import org.myboulderlog.server.model.AbstractDomainObject;

public class ObjectifyLocator extends Locator<AbstractDomainObject, Long> {

    @Override
    public AbstractDomainObject create(Class<? extends AbstractDomainObject> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbstractDomainObject find(Class<? extends AbstractDomainObject> clazz, Long id) {
        DAOBase daoBase = new DAOBase();
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
