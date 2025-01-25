package objectMappingHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class CreditCardApiValidation {
    @BeforeMethod
    public void setup() {
        //აქ რა url ოცემული იყო პირობაში ვერ ვიპოვე და ამ ურლით დავწერე ტესტი (❁´◡`❁)
        RestAssured.baseURI = "https://fakerapi.it/api/v2/creditCards?_quantity=1";
    }

    @Test
    public void validateCreditCardApiResponse() {
        File schema = new File("src/main/resources/card.json");
        given()
                .when()
                .get()
                .then()
                .statusCode(Constants.OK_STATUS_CODE)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}
