package steps.mappingHomework.RestfulBookerTestSteps;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class UpdateBookingSteps {

    public void partialUpdateBooking(int bookingId, String updatePayload, String token) {
        Response updateResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatePayload)
                .patch("/booking/" + bookingId);

        Assert.assertEquals(updateResponse.getStatusCode(), Constants.OK_STATUS_CODE, Constants.FAILED_TO_UPDATE_BOOKING);
    }


    public UpdateBookingSteps validateBookingStatusCode(int bookingId) {
        Response getBookingResponse = RestAssured.get("/booking/" + bookingId);
        Assert.assertEquals(getBookingResponse.getStatusCode(), Constants.OK_STATUS_CODE, Constants.FAILED_TO_GET_BOOK_AFTER_UPDATE_MESSAGE);
        return this;
    }

    public UpdateBookingSteps validateFirstname(int bookingId, String expectedFirstname) {
        Response getBookingResponse = RestAssured.get("/booking/" + bookingId);
        String actualFirstname = getBookingResponse.jsonPath().getString("firstname");
        Assert.assertEquals(actualFirstname, expectedFirstname, Constants.FIRSTNAME_NO_UPDATED_MESSAGE);
        return this;

    }

    public UpdateBookingSteps validateLastname(int bookingId, String expectedLastname) {
        Response getBookingResponse = RestAssured.get("/booking/" + bookingId);
        String actualLastname = getBookingResponse.jsonPath().getString("lastname");
        Assert.assertEquals(actualLastname, expectedLastname, Constants.LASTNAME_NO_UPDATED_MESSAGE);
        return this;

    }
}