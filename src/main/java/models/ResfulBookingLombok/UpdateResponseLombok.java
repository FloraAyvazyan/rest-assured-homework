package models.ResfulBookingLombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
@JsonPropertyOrder({"firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds"})
public class UpdateResponseLombok{
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;
    @JsonProperty("bookingdates")
    private BookingDatesLombok bookingDates;
    @JsonProperty("totalprice")
    private int totalPrice;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    @JsonProperty("lastname")
    private String lastName;
}