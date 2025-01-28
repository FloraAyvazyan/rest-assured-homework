package featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.PetStore.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.featureHomework.PetStoreApiTest2Steps;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class PetStoreApiTest {
    private final PetStoreApiTest2Steps petStoreApiTest2Steps = new PetStoreApiTest2Steps();
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.PET_STORE_BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(Constants.OK_STATUS_CODE)
                .expectBody(not(empty()))
                .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    @Test
    public void petStoreTest() {
        Pet newPet = petStoreApiTest2Steps
                .createPetRequest();
        Response response = petStoreApiTest2Steps
                .sendPetRequest(requestSpec, newPet);

        petStoreApiTest2Steps
                .validateStatusCode(response)
                .validatePetId(response, newPet)
                .validatePetName(response, newPet)
                .validatePetStatus(response, newPet);

        Response findPetsResponse = petStoreApiTest2Steps.findPets(requestSpec);
        petStoreApiTest2Steps.validateStatusCode(findPetsResponse);

        Pet extractedPet = petStoreApiTest2Steps
                .extractPet(findPetsResponse);
        petStoreApiTest2Steps
                .validateExtractedPetId(extractedPet, newPet)
                .validateExtractedPetName(extractedPet, newPet)
                .validateExtractedPetStatus(extractedPet, newPet);
    }
}

