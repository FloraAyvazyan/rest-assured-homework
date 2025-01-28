package steps.mappingHomework.EscuelajsApiTest;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class UserService {
    public void createUser(String userPayload) {
        given()
                .contentType(ContentType.JSON)
                .body(userPayload)
                .accept(ContentType.JSON)
                .when()
                .post("/v1/users")
                .then()
                .statusCode(201)
                .log().all();
    }
}