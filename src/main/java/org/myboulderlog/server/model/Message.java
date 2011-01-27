package org.myboulderlog.server.model;

import javax.persistence.Id;

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

    public Long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }
}
