package objectMappingHomework;

import DataProvider.DataProviderClass;
import data.Constants;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.objectMappingHomework.Booking.RestfulBookerTestSteps;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@Epic("Restful Booker API Tests")
public class BookingTestLombok {
    RestfulBookerTestSteps restfulBookerTestSteps = new RestfulBookerTestSteps();
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.HEROKU_APP_BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(Constants.OK_STATUS_CODE)
                .expectBody(not(empty()))
                .build();
    }

    @Test(dataProvider = "bookingData", dataProviderClass = DataProviderClass.class,
            description = "Update booking test with different input combinations")
    @Story("Update Booking with Valid Data")
    @Description("Verify that booking can be updated successfully with valid input combinations.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Update Booking Feature")
    public void updatingTest(String firstname, String lastname, int totalPrice,
                             boolean depositPaid, String checkin,
                             String checkout, String additionalNeeds,
                             int salesPrice, String passportNo) {
        restfulBookerTestSteps
                .generateUpdateRequest(firstname, lastname, totalPrice, depositPaid,
                        checkin, checkout, additionalNeeds, salesPrice, passportNo)
                .updateRequest(requestSpec)
                .validateResponse(responseSpec)
                .deserialize()
                .validateFirstName()
                .validateLastName()
                .validateTotalPrice()
                .validateDepositPaid()
                .validateCheckInDate()
                .validateCheckOutDate()
                .validateAdditionalNeeds();

    }
}
