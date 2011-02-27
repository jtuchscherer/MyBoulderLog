package org.myboulderlog.client.admin.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;
import org.myboulderlog.client.admin.place.GymListPlace;

@WithTokenizers({AdminOverviewPlace.Tokenizer.class, GymListPlace.Tokenizer.class})
public interface AdminPlaceHistoryMapper extends PlaceHistoryMapper {
}
