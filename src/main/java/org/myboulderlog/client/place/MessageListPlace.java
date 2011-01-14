package org.myboulderlog.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MessageListPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<MessageListPlace> {
        public String getToken(MessageListPlace place) {
            return null;
        }

        public MessageListPlace getPlace(String token) {
            return new MessageListPlace();
        }
    }
}
