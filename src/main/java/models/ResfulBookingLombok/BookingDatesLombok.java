package models.ResfulBookingLombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonPropertyOrder({"checkin", "checkout"})
public class BookingDatesLombok {
    @JsonProperty("checkin")
    private String checkIn;

    @JsonProperty("checkout")
    private String checkOut;
}