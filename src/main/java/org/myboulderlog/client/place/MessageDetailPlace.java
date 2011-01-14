package org.myboulderlog.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MessageDetailPlace extends Place
{
	private String messageName;

	public MessageDetailPlace(String token)
	{
		this.messageName = token;
	}

	public String getMessageName()
	{
		return messageName;
	}

	public static class Tokenizer implements PlaceTokenizer<MessageDetailPlace>
	{
		public String getToken(MessageDetailPlace place)
		{
			return place.getMessageName();
		}

		public MessageDetailPlace getPlace(String token)
		{
			return new MessageDetailPlace(token);
		}
	}

}