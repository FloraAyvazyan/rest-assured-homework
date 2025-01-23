package steps.mappingHomework.EscuelajsApiTest;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthService {

    public String loginUser(String loginPayload) {
        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .accept(ContentType.JSON)
                .when()
                .post("/v1/auth/login")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        return loginResponse.jsonPath().getString("access_token");
    }
}