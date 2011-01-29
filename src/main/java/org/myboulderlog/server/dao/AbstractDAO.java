package org.myboulderlog.server.dao;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import org.myboulderlog.shared.exception.TooManyResultsException;

import java.util.List;
import java.util.Map;

public interface AbstractDAO<T> {
    Key<T> put(T entity);

    Map<Key<T>, T> putAll(Iterable<T> entities);

    void delete(T entity);

    void deleteKey(Key<T> entityKey);

    void deleteAll(Iterable<T> entities);

    void deleteKeys(Iterable<Key<T>> keys);

    T get(Long id) throws EntityNotFoundException;

    T get(Key<T> key) throws EntityNotFoundException;

    Map<Key<T>, T> get(Iterable<Key<T>> keys);

    List<T> listAll();

    T getByProperty(String propName, Object propValue) throws TooManyResultsException;

    List<T> listByProperty(String propName, Object propValue);

    List<Key<T>> listKeysByProperty(String propName, Object propValue);

    T getByExample(T exampleObj) throws TooManyResultsException;

    List<T> listByExample(T exampleObj);

    Key<T> getKey(Long id);

    Key<T> key(T obj);

    List<T> listChildren(Object parent);

    List<Key<T>> listChildKeys(Object parent);
}
