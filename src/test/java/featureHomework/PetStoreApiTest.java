package featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.featureHomework.PetStoreApiTest2Steps;

import static org.hamcrest.Matchers.*;

public class PetStoreApiTest {
    PetStoreApiTest2Steps petStoreApiTest2Steps = new PetStoreApiTest2Steps();

    public static  RequestSpecification requestSpec;
    public static   ResponseSpecification responseSpec;

    @BeforeClass
    public static void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.PET_STORE_BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(Constants.OK_STATUS_CODE)
                .expectBody(not(empty()))
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    @Test
    public void petStoreTest() {
        JSONObject newPet =  petStoreApiTest2Steps.createPetRequest();
        Response response = petStoreApiTest2Steps.sendPetRequest(requestSpec, newPet);
        petStoreApiTest2Steps
                .validateStatusCode(response)
                .validatePetId(response, newPet)
                .validatePetName(response, newPet)
                .validatePetStatus(response, newPet);
        Response findPetByStatus = petStoreApiTest2Steps.findPets(requestSpec);
        petStoreApiTest2Steps
                .validateStatusCode(findPetByStatus)
                .responseArrayContainsID(findPetByStatus);
        JSONObject extractPet = petStoreApiTest2Steps.extractPetObject(findPetByStatus);
        petStoreApiTest2Steps
                .extractedPetIdValidation(extractPet, newPet)
                .extractedPetNameValidation(extractPet, newPet)
                .extractedPetStatusValidation(extractPet, newPet);
       Response updatedPet =  petStoreApiTest2Steps.updatePet(Constants.UPDATED_PET_ID);
       petStoreApiTest2Steps
                .validatePetName(updatedPet)
                .validatePetStatus(updatedPet);

    }

}

