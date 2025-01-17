import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.PetStoreSteps;

import static org.hamcrest.Matchers.notNullValue;

public class PetStoreApiTest {
    PetStoreSteps petStoreSteps = new PetStoreSteps();

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = Constants.PET_STORE_BASE_URL;
    }

    @Test
    public void postRequest() {
        String requestBody = petStoreSteps.createRequestBody();

        Response response = petStoreSteps.createOrder(requestBody);

        System.out.println("Response Body:");
        response.prettyPrint();

        petStoreSteps
                .validateOrderResponseStatusCode(response)
                .validateOrderResponseId(response)
                .validateOrderResponsePetId(response)
                .validateOrderResponseQuantity(response)
                .validateOrderResponseShipDate(response)
                .validateOrderResponseStatus(response)
                .validateOrderResponseComplete(response);
    }


    //აქაც გაერთიანებული მაქვს 7-8 ტესტები
    @Test
    public void test7() {
        Response response = petStoreSteps.update(202);
        response
                .then()
                .statusCode(200)
                .body("code", notNullValue(),
                        "type", notNullValue(),
                        "message", notNullValue());

        Response response2 = petStoreSteps.update(9999);
        response2
                .then()
                .statusCode(404)
                .body("code", Matchers.is(404),
                        "message", Matchers.is(Constants.NOT_FOUND_MESSAGE));

        response.prettyPrint();
        response2.prettyPrint();


    }

    @Test
    public void loginTest(){
        Response response = petStoreSteps.login(Constants.USERNAME, Constants.PASSWORD);
        petStoreSteps.validateLoginStatusCode(response);

        response.prettyPrint();

        String responseMessage = petStoreSteps.extractMessage(response);
        String significantNumber = petStoreSteps.extractSignificantDigits(responseMessage);

        petStoreSteps.validateSignificantDigitsLength(significantNumber);

        System.out.println("Extracted 10 significant digits: " + significantNumber);

    }



}