package models.BookerHerokuApp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates {

	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;
}