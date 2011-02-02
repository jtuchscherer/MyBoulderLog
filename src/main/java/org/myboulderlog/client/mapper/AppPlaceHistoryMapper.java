package org.myboulderlog.client.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import org.myboulderlog.client.place.RouteListPlace;
import org.myboulderlog.client.place.RouteDetailPlace;

@WithTokenizers({RouteListPlace.Tokenizer.class, RouteDetailPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
