package org.myboulderlog.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RouteDetailPlace extends Place {
    private String routeName;

    public RouteDetailPlace(String token) {
        this.routeName = token;
    }

    public String getRouteName() {
        return routeName;
    }

    public static class Tokenizer implements PlaceTokenizer<RouteDetailPlace> {
        public String getToken(RouteDetailPlace place) {
            return place.getRouteName();
        }

        public RouteDetailPlace getPlace(String token) {
            return new RouteDetailPlace(token);
        }
    }

}