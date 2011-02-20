package org.myboulderlog.client.mainapp.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import org.myboulderlog.client.mainapp.place.RouteListPlace;
import org.myboulderlog.client.mainapp.place.RouteDetailPlace;

@WithTokenizers({RouteListPlace.Tokenizer.class, RouteDetailPlace.Tokenizer.class})
public interface MainAppPlaceHistoryMapper extends PlaceHistoryMapper {
}
