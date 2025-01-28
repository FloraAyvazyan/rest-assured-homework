package mappingHomework;

import data.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.mappingHomework.RestfulBookerTestSteps.BookingSteps;
import steps.mappingHomework.RestfulBookerTestSteps.DeleteBookingSteps;
import steps.mappingHomework.RestfulBookerTestSteps.TokenSteps;
import steps.mappingHomework.RestfulBookerTestSteps.UpdateBookingSteps;

public class RestfulBookerTest {

    private String token;
    private int bookingId;
    private TokenSteps tokenSteps = new TokenSteps();
    private BookingSteps bookingSteps = new BookingSteps();
    private UpdateBookingSteps updateBookingSteps  = new UpdateBookingSteps();
    private DeleteBookingSteps deleteBookingSteps   = new DeleteBookingSteps();

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Constants.HEROKU_APP_BASE_URL;
        //3. Auth - Create Token https://restful-booker.herokuapp.com/auth
        token = tokenSteps.createToken();
    }

    @Test
    public void createUpdateDeleteBooking() {
        //   - Create Booking
        String bookingPayload = """
                {
                    "firstname": "Flora",
                    "lastname": "Ayvazyan",
                    "totalprice": 123,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2023-01-01",
                        "checkout": "2023-01-10"
                    },
                    "additionalneeds": "Breakfast"
                }
                """;

        bookingId = bookingSteps.createBooking(bookingPayload, token);
        bookingSteps.getBooking(bookingId);

//   - Partial Update Booking
        String updatePayload = "{\"firstname\": \"Jane\", \"lastname\": \"Smith\"}";
        updateBookingSteps.partialUpdateBooking(bookingId, updatePayload, token);
        updateBookingSteps
                .validateBookingStatusCode(bookingId)
                .validateFirstname(bookingId, Constants.EXCEPTED_FIRST_NAME)
                .validateLastname(bookingId, Constants.EXCEPTED_LAST_NAME);

//- Delete Booking

        deleteBookingSteps.deleteBooking(bookingId, token);
        deleteBookingSteps.validateBookingDeletion(bookingId);
    }
}
