package org.myboulderlog.client.admin.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AdminOverviewPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<AdminOverviewPlace> {
        public String getToken(AdminOverviewPlace place) {
            return null;
        }

        public AdminOverviewPlace getPlace(String token) {
            return new AdminOverviewPlace();
        }
    }
}
