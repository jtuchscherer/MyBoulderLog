package org.myboulderlog.server.dao.objectify;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.helper.DAOBase;
import org.myboulderlog.server.dao.AbstractDAO;
import org.myboulderlog.server.model.AbstractDomainObject;
import org.myboulderlog.shared.exception.TooManyResultsException;
import org.slf4j.Logger;

import javax.persistence.Embedded;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractObjectifyDAO<T extends AbstractDomainObject> extends DAOBase implements AbstractDAO<T> {

    static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;

    protected Class<T> clazz =
            (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public Class<T> getClazz() {
        return clazz;
    }

     public T save(T entity) {
        Key key = this.put(entity);
        try {
            return this.get(key);
        } catch (Exception e) {
            getLogger().error("Was not able to save and return requested Object", e);
            throw new RuntimeException(e);
        }
    }

    protected abstract Logger getLogger();

    public void remove(T entity) {
        this.delete(entity);
    }

    public Key<T> put(T entity)

    {
        return ofy().put(entity);
    }

    public Map<Key<T>, T> putAll(Iterable<T> entities) {
        return ofy().put(entities);
    }

    public void delete(T entity) {
        ofy().delete(entity);
    }

    public void deleteKey(Key<T> entityKey) {
        ofy().delete(entityKey);
    }

    public void deleteAll(Iterable<T> entities) {
        ofy().delete(entities);
    }

    public void deleteKeys(Iterable<Key<T>> keys) {
        ofy().delete(keys);
    }

    public T get(Long id) throws EntityNotFoundException {
        return ofy().get(this.getClazz(), id);
    }

    public T get(Key<T> key) throws EntityNotFoundException {
        return ofy().get(key);
    }

    public Map<Key<T>, T> get(Iterable<Key<T>> keys) {
        return ofy().get(keys);
    }

    public List<T> listAll() {
        Query<T> q = ofy().query(this.getClazz());
        return q.list();
    }

    /**
     * Convenience method to get all objects matching a single property
     *
     * @param propName
     * @param propValue
     * @return T matching Object
     * @throws TooManyResultsException
     */
    public T getByProperty(String propName, Object propValue) throws TooManyResultsException {
        Query<T> q = ofy().query(this.getClazz());
        q.filter(propName, propValue);
        Iterator<T> fetch = q.limit(2).list().iterator();
        if (!fetch.hasNext()) {
            return null;
        }
        T obj = fetch.next();
        if (fetch.hasNext()) {
            throw new TooManyResultsException(q.toString() + " returned too many results");
        }
        return obj;
    }

    public List<T> listByProperty(String propName, Object propValue) {
        Query<T> q = ofy().query(this.getClazz());
        q.filter(propName, propValue);
        return q.list();
    }

    public List<Key<T>> listKeysByProperty(String propName, Object propValue) {
        Query<T> q = ofy().query(this.getClazz());
        q.filter(propName, propValue);
        return q.listKeys();
    }

    public T getByExample(T exampleObj) throws TooManyResultsException {
        Query<T> q = buildQueryByExample(exampleObj);
        Iterator<T> fetch = q.limit(2).list().iterator();
        if (!fetch.hasNext()) {
            return null;
        }
        T obj = fetch.next();
        if (fetch.hasNext()) {
            throw new TooManyResultsException(q.toString() + " returned too many results");
        }
        return obj;
    }

    public List<T> listByExample(T exampleObj) {
        Query<T> queryByExample = buildQueryByExample(exampleObj);
        return queryByExample.list();
    }

    public Key<T> getKey(Long id) {
        return new Key<T>(this.getClazz(), id);
    }

    public Key<T> key(T obj) {
        return fact().getKey(obj);
    }

//    public List<T> listChildren(Object parent) {
//        return ofy().query(this.getClazz()).ancestor(parent).list();
//    }
//
//    public List<Key<T>> listChildKeys(Object parent) {
//        return ofy().query(this.getClazz()).ancestor(parent).listKeys();
//    }

    protected Query<T> buildQueryByExample(T exampleObj) {
        Query<T> q = ofy().query(this.getClazz());

        // Add all non-null properties to query filter
        for (Field field : this.getClazz().getDeclaredFields()) {
            // Ignore transient, embedded, array, and collection properties
            if (field.isAnnotationPresent(Transient.class) ||
                    (field.isAnnotationPresent(Embedded.class)) ||
                    (field.getType().isArray()) ||
                    (field.getType().isArray()) ||
                    (Collection.class.isAssignableFrom(field.getType())) ||
                    ((field.getModifiers() & BAD_MODIFIERS) != 0))
            { continue; }

            field.setAccessible(true);

            Object value;
            try {
                value = field.get(exampleObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (value != null) {
                q.filter(field.getName(), value);
            }
        }

        return q;
    }

}