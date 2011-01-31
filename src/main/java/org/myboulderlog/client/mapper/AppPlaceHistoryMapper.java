package org.myboulderlog.client.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import org.myboulderlog.client.place.MessageDetailPlace;
import org.myboulderlog.client.place.MessageListPlace;

@WithTokenizers({MessageListPlace.Tokenizer.class, MessageDetailPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
