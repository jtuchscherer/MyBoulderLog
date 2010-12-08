package org.myboulderlog.server.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 */
public class Message {
    @Id
    private Long id;

    private String text;

    /**
     */
    public Message() {
        super();
    }

    /**
     * @param _text
     */
    public Message(final String _text) {
        this.text = _text;
    }

    /**
     * @return id of the message
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return text of the message
     */
    public String getText() {
        return this.text;
    }
}
