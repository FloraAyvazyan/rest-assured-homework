package steps.featureHomework;

import com.github.javafaker.Faker;
import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import java.util.LinkedHashMap;
import static io.restassured.RestAssured.given;

public class PetStoreApiTest2Steps {
    Faker faker = new Faker();

    public JSONObject createPetRequest() {
        JSONObject category = new JSONObject();
        category.put("id", Constants.ID);
        category.put("name", faker.animal().name());

        JSONArray photoUrls = new JSONArray();
        photoUrls.put("string");

        JSONObject tag = new JSONObject();
        tag.put("id", Constants.ID);
        tag.put("name", faker.animal().name());

        JSONArray tags = new JSONArray();
        tags.put(tag);

        JSONObject pet = new JSONObject();
        pet.put("id", Constants.ID);
        pet.put("category", category);
        pet.put("name", faker.animal().name());
        pet.put("photoUrls", photoUrls);
        pet.put("tags", tags);
        pet.put("status", Constants.AVAILABLE_STATUS);

        return pet;
    }


    public Response sendPetRequest(RequestSpecification requestSpec, JSONObject jsonObject) {
        return given(requestSpec)
                .body(jsonObject.toString())
                .when()
                .post("/pet");
    }

    public PetStoreApiTest2Steps validateStatusCode(Response response) {
        response
                .then()
                .assertThat()
                .statusCode(Constants.OK_STATUS_CODE);
        return this;
    }

    public PetStoreApiTest2Steps validatePetId(Response response, JSONObject petJson) {
        response
                .then()
                .assertThat()
                .body("id", Matchers.is(petJson.get("id")));
        return this;
    }

    public PetStoreApiTest2Steps validatePetName(Response response, JSONObject petJson) {
        response
                .then()
                .assertThat()
                .body("name", Matchers.is(petJson.get("name")));
        return this;
    }

    public PetStoreApiTest2Steps validatePetStatus(Response response, JSONObject petJson) {
        response
                .then()
                .assertThat()
                .body("status", Matchers.is(petJson.get("status")));
        return this;
    }

    public Response findPets(RequestSpecification requestSpec) {
        return given(requestSpec)
                .queryParam("status", Constants.AVAILABLE_STATUS)
                .when()
                .get("/pet/findByStatus");
    }

    public PetStoreApiTest2Steps responseArrayContainsID(Response response) {
        response
                .then()
                .body("id", Matchers.hasItem(Constants.ID));
        return this;
    }

    public JSONObject extractPetObject(Response response) {
        LinkedHashMap<String, Object> petMap = response
                .jsonPath()
                .getJsonObject("find { it.id == 4000 }");
        return new JSONObject(petMap);
    }


    public PetStoreApiTest2Steps extractedPetIdValidation(JSONObject petObject, JSONObject requestBody) {
        Assert.assertEquals(petObject.getLong("id"), requestBody.getLong("id"));
        return this;
    }

    public PetStoreApiTest2Steps extractedPetNameValidation(JSONObject petObject, JSONObject requestBody) {
        Assert.assertEquals(petObject.getString("name"), requestBody.getString("name"));
        return this;
    }

    public PetStoreApiTest2Steps extractedPetStatusValidation(JSONObject petObject, JSONObject requestBody) {
        Assert.assertEquals(petObject.getString("status"), requestBody.getString("status"));
        return this;
    }


    public Response updatePet(int petId) {
        String requestBody = String.format("{\"id\": %d, \"name\": \"%s\", \"status\": \"%s\"}",
                petId, Constants.UPDATED_ANIMAL_NAME, Constants.UPDATED_ANIMAL_STATUS);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/pet");

        response.then().statusCode(Constants.OK_STATUS_CODE);
        return response;
    }


    public PetStoreApiTest2Steps validatePetName(Response response){
        response
                .then()
                .assertThat()
                .body("name", Matchers.is(Constants.UPDATED_ANIMAL_NAME));
        return this;
    }


    public PetStoreApiTest2Steps validatePetStatus(Response response){
        response
                .then()
                .assertThat()
                .body("status", Matchers.is(Constants.UPDATED_ANIMAL_STATUS));
        return this;
    }



}




