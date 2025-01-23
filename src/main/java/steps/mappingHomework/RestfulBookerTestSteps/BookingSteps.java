package steps.mappingHomework.RestfulBookerTestSteps;


import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookingSteps {

    private int bookingId;

    public int createBooking(String bookingPayload, String token) {
        Response createBookingResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingPayload)
                .post("/booking");

        Assert.assertEquals(createBookingResponse.getStatusCode(), Constants.OK_STATUS_CODE, Constants.FAILED_TO_CREATE_BOOKING);
        bookingId = createBookingResponse.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, Constants.INVALID_BOOKING_ID_MESSAGE);
        return bookingId;
    }

    public void getBooking(int bookingId) {
        Response getBookingResponse = RestAssured
                .get("/booking/" + bookingId);

        Assert.assertEquals(getBookingResponse.getStatusCode(), Constants.OK_STATUS_CODE, Constants.FAILED_GETTING_BOOKS_BEFORE_CREATING_MESSAGE);
    }
}