package steps.objectMappingHomework.petStpreSteps;

import com.github.javafaker.Faker;
import data.Constants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.PetStore.PetResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostPetOrderSteps {
    Response petResponse;
    PetResponse pet;
    PetResponse petD;

    Faker faker = new Faker();
    int id = faker.number().numberBetween(1000, 10000);
    int petId = faker.number().numberBetween(1, 100);
    int quantity = faker.number().numberBetween(1, 1000);
    String status = faker.options().option(Constants.AVAILABLE_STATUS, Constants.PENDING_STATUS, Constants.SOLD_STATUS);
    boolean complete = faker.bool().bool();


    @Step("Create a new Pet object with specified details")
    public PostPetOrderSteps createPet() {
        pet = new PetResponse()
                .setId(id)
                .setPetId(petId)
                .setQuantity(quantity)
                .setShipDate(Constants.SHIP_DATE) // Generate a future date
                .setStatus(status)
                .setComplete(complete);

        return this;
    }

    @Step("Post the Pet order to the API")
    public PostPetOrderSteps postPet(RequestSpecification requestSpec){
        petResponse = given(requestSpec)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .when()
                .post("/api/v3/store/order");
        return this;
    }

    @Step("Validate the API response using the specified response specification")
    public PostPetOrderSteps validateResponse(ResponseSpecification responseSpec){
        petResponse.then().assertThat().spec(responseSpec);
        return this;

    }

    @Step("Deserialize the API response into a PetResponse object")
    public PostPetOrderSteps deserialize(){
        petD = petResponse
                .then()
                .extract()
                .as(PetResponse.class);
        return this;
    }


    @Step("Validate the Pet ID in the response")
    public PostPetOrderSteps validatePetId() {
        assertThat(Constants.INVALID_PET_ID_MESSAGE,
                pet.getPetId(), equalTo(petId));
        return this;
    }


    @Step("Validate the Quantity in the response")
    public PostPetOrderSteps validateQuantity() {
        assertThat(Constants.INVALID_QUANTITY_MESSAGE,
                pet.getQuantity(), equalTo(quantity));
        return this;
    }

    @Step("Validate the ID in the response")
    public PostPetOrderSteps validateId() {
        assertThat(Constants.INVALID_ID_MESSAGE,
                pet.getId(), equalTo(id));
        return this;
    }

    @Step("Validate the Ship Date in the response")
    public PostPetOrderSteps validateShipDate() {
        assertThat(Constants.SHIP_DATA_SHOULD_NOT_BE_NULL_MESSAGE,
                pet.getShipDate(), notNullValue());
        assertThat(Constants.INVALID_SHIP_DATA_MESSAGE,
                pet.getShipDate(), matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
        return this;
    }


    @Step("Validate the Complete status in the response")
    public PostPetOrderSteps validateCompleteStatus() {
        assertThat(Constants.INVALID_COMPLETE_STATUS_MESSAGE,
                pet.isComplete(), equalTo(complete));
        return this;
    }

    @Step("Validate the Status in the response")
    public PostPetOrderSteps validateStatus() {
        assertThat(Constants.INVALID_STATUS_MESSAGE,
                pet.getStatus(), equalTo(status));
        return this;
    }

}
