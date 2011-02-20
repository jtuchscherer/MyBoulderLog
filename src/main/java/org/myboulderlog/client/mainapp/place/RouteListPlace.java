package org.myboulderlog.client.mainapp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RouteListPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<RouteListPlace> {
        public String getToken(RouteListPlace place) {
            return null;
        }

        public RouteListPlace getPlace(String token) {
            return new RouteListPlace();
        }
    }
}
