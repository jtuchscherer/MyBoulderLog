package org.myboulderlog.server.dao;

import org.myboulderlog.server.model.Message;

import java.util.Collection;

public interface MessageDAO {
    Collection<Message> getAll();

    void create(Message message);

    void deleteById(Long id);
}
