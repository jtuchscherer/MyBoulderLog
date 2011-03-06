package org.myboulderlog.client.admin.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GymPreviewPlace extends Place {
    private String gymId;

    public GymPreviewPlace(String token) {
        this.gymId = token;
    }

    public String getGymId() {
        return gymId;
    }

    public static class Tokenizer implements PlaceTokenizer<GymPreviewPlace> {
        public String getToken(GymPreviewPlace place) {
            return place.getGymId();
        }

        public GymPreviewPlace getPlace(String token) {
            return new GymPreviewPlace(token);
        }
    }
}
