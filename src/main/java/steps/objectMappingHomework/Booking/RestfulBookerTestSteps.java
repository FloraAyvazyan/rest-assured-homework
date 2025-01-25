package steps.objectMappingHomework.Booking;

import data.Constants;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.ResfulBookingLombok.BookingDatesLombok;
import models.ResfulBookingLombok.UpdateRequestLombok;
import models.ResfulBookingLombok.UpdateResponseLombok;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RestfulBookerTestSteps {
    Response updateResponse;
    UpdateRequestLombok updateRequestLombok;
    UpdateResponseLombok updateResponseLombok;

    @Step("Generate Update Request with provided details")
    public RestfulBookerTestSteps generateUpdateRequest(String firstname, String lastname, int totalPrice,
                                             boolean depositPaid, String checkin,
                                             String checkout, String additionalNeeds,
                                             int salesPrice, String passportNo){
        updateRequestLombok = new UpdateRequestLombok()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setTotalPrice(totalPrice)
                .setDepositPaid(depositPaid)
                .setBookingDates(new BookingDatesLombok()
                        .setCheckIn(checkin)
                        .setCheckOut(checkout))
                .setAdditionalNeeds(additionalNeeds)
                .setSalesprice(salesPrice)
                .setPassportNo(passportNo);
        return this;
    }

    @Step("Send Update Request to API")
    public RestfulBookerTestSteps updateRequest(RequestSpecification requestSpec){
        updateResponse = given(requestSpec)
                .body(updateRequestLombok)
                .auth()
                .preemptive()
                .basic(Constants.TOKEN_USERNAME, Constants.TOKEN_PASSWORD)
                .when()
                .put("/booking/1");
        return this;
    }

    @Step("Validate API Response against specification")
    public RestfulBookerTestSteps validateResponse(ResponseSpecification responseSpec){
        updateResponse.then().assertThat().spec(responseSpec);
        return this;

    }

    @Step("Deserialize the API Response to UpdateResponseLombok")
    public RestfulBookerTestSteps deserialize(){
        updateResponseLombok = updateResponse
                .then()
                .statusCode(Constants.OK_STATUS_CODE)
                .extract()
                .as(UpdateResponseLombok.class);
        return this;
    }

    @Step("Validate First Name")
    public RestfulBookerTestSteps validateFirstName() {
        assertThat(updateResponseLombok.getFirstName(), equalTo(updateRequestLombok.getFirstName()));
        return this;
    }

    @Step("Validate Last Name")
    public RestfulBookerTestSteps validateLastName() {
        assertThat(updateResponseLombok.getLastName(), equalTo(updateRequestLombok.getLastName()));
        return this;
    }

    @Step("Validate Total Price")
    public RestfulBookerTestSteps validateTotalPrice() {
        assertThat(updateResponseLombok.getTotalPrice(), equalTo(updateRequestLombok.getTotalPrice()));
        return this;
    }

    @Step("Validate Deposit Paid")
    public RestfulBookerTestSteps validateDepositPaid() {
        assertThat(updateResponseLombok.isDepositPaid(), equalTo(updateRequestLombok.isDepositPaid()));
        return this;
    }

    @Step("Validate CheckIn Date")
    public RestfulBookerTestSteps validateCheckInDate() {
        assertThat(updateResponseLombok.getBookingDates().getCheckIn(),
                equalTo(updateRequestLombok.getBookingDates().getCheckIn()));
        return this;
    }

    @Step("Validate CheckOut Date")
    public RestfulBookerTestSteps validateCheckOutDate() {
        assertThat(updateResponseLombok.getBookingDates().getCheckOut(),
                equalTo(updateRequestLombok.getBookingDates().getCheckOut()));
        return this;
    }

    @Step("Validate Additional Needs")
    public RestfulBookerTestSteps validateAdditionalNeeds() {
        assertThat(updateResponseLombok.getAdditionalNeeds(),
                equalTo(updateRequestLombok.getAdditionalNeeds()));
        return this;
    }

}
