package steps.mappingHomework.RestfulBookerTestSteps;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class TokenSteps {
    private String token;

    public String createToken() {
        Response tokenResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"admin\", \"password\": \"password123\"}")
                .post("/auth");

        Assert.assertEquals(tokenResponse.getStatusCode(), Constants.OK_STATUS_CODE, Constants.FAILED_TO_CREATE_TOKEN);

        token = tokenResponse.jsonPath().getString("token");

        Assert.assertNotNull(token, Constants.TOKEN_IS_NULL_MESSAGE);

        return token;
    }
}