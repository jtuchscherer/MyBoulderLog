package org.myboulderlog.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RouteDetailPlace extends Place {
    private String messageName;

    public RouteDetailPlace(String token) {
        this.messageName = token;
    }

    public String getMessageName() {
        return messageName;
    }

    public static class Tokenizer implements PlaceTokenizer<RouteDetailPlace> {
        public String getToken(RouteDetailPlace place) {
            return place.getMessageName();
        }

        public RouteDetailPlace getPlace(String token) {
            return new RouteDetailPlace(token);
        }
    }

}