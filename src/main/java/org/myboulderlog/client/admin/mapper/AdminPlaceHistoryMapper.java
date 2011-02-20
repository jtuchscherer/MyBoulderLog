package org.myboulderlog.client.admin.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import org.myboulderlog.client.admin.place.AdminOverviewPlace;

@WithTokenizers({AdminOverviewPlace.Tokenizer.class})
public interface AdminPlaceHistoryMapper extends PlaceHistoryMapper {
}
