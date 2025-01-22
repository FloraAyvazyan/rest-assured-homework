package featureHomework;

import data.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.featureHomework.BookingHerokuAppSteps;
import static org.hamcrest.Matchers.*;

public class BookerHerokuAppApiTest {
    BookingHerokuAppSteps bookingHerokuappSteps = new BookingHerokuAppSteps();

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.HEROKU_APP_BASE_URL)
                .setContentType(ContentType.JSON)
                .build().log().all();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(Constants.OK_STATUS_CODE)
                .expectBody(not(empty()))
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void bookingUpdate(){
        JSONObject obj = bookingHerokuappSteps.JSONObjectParameterization();
        String token = bookingHerokuappSteps.getToken();
        Response response = bookingHerokuappSteps
                 .BookingUpdate(requestSpec, obj, token);
        bookingHerokuappSteps.validateLogAndStatusCode(response);
    }
}

