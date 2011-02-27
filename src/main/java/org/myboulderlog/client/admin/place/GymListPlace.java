package org.myboulderlog.client.admin.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GymListPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<GymListPlace> {
        public String getToken(GymListPlace place) {
            return null;
        }

        public GymListPlace getPlace(String token) {
            return new GymListPlace();
        }
    }
}
