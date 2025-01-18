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

    //6. Send a POST request to `https://petstore.swagger.io/v2/store/order` with a JSON body.
    //
    //   - Validate that the response contains all necessary information.
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


    //7. Send a (form) POST request to `https://petstore.swagger.io/v2/pet/{petId}` with `petId`, `name` & `status` parameters.
    //
    //   - Validate that the response JSON has `code`, `type`, and `message`.
    //
    //8. Send another request and try to cause a `404 error`.
    //
    //   - Validate that the response has "code": 404.
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

    //9. Send a GET request to petstore `/user/login` resource with query parameters `username` & `password`.
    //
    //   - Ensure that the response status code is `200` (OK).
    //   - Validate that the message contains `10 significant numbers`.
    //   - Extract that number and print it.
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