package org.myboulderlog.client.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
