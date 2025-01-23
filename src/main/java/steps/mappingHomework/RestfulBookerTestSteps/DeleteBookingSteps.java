package steps.mappingHomework.RestfulBookerTestSteps;


import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class DeleteBookingSteps {

    public void deleteBooking(int bookingId, String token) {
        Response deleteResponse = RestAssured
                .given()
                .header("Cookie", "token=" + token)
                .delete("/booking/" + bookingId);

        Assert.assertEquals(deleteResponse.getStatusCode(), 201, Constants.FAILED_TO_DELETE_BOOKS_MESSAGE);
    }

    public void validateBookingDeletion(int bookingId) {
        Response getDeletedBookingResponse = RestAssured
                .get("/booking/" + bookingId);

        Assert.assertEquals(getDeletedBookingResponse.getStatusCode(), Constants.ERROR_STATUS_CODE, Constants.BOOKING_NOT_DELETED_MESSAGE);
    }
}