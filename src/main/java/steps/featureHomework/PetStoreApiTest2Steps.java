package steps.featureHomework;

import com.github.javafaker.Faker;
import data.Constants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.PetStore.Category;
import models.PetStore.Pet;
import models.PetStore.TagsItem;
import org.hamcrest.Matchers;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;


public class PetStoreApiTest2Steps {
    private final Faker faker = new Faker();
    Category category = new Category();
    Pet pet = new Pet();
    TagsItem tagsItem = new TagsItem();


    public Pet createPetRequest(){
        pet = new Pet();
        pet.setId(Constants.ID);

        category = new Category();
        category.setId(Constants.ID);
        category.setName(faker.animal().name());
        pet.setCategory(category);

        pet.setName(faker.animal().name());
        pet.setPhotoUrls(List.of("photos"));

        tagsItem = new TagsItem();
        tagsItem.setId(Constants.ID);
        tagsItem.setName(faker.animal().name());
        pet.setTags(List.of(tagsItem));
        pet.setStatus(Constants.AVAILABLE_STATUS);
        return pet;
    }

    public Response sendPetRequest(RequestSpecification requestSpec, Pet pet) {
        return given(requestSpec)
                .body(pet)
                .post("/pet");
    }

    public PetStoreApiTest2Steps validateStatusCode(Response response) {
        response.then().statusCode(Constants.OK_STATUS_CODE);
        return this;
    }

    public PetStoreApiTest2Steps validatePetId(Response response, Pet pet) {
        response.then()
                .body("id", Matchers.is((int) pet.getId()));
        return this;
    }
    public PetStoreApiTest2Steps validatePetName(Response response, Pet pet) {
        response.then()
                .body("name", Matchers.is(pet.getName()));

        return this;
    }
    public PetStoreApiTest2Steps validatePetStatus(Response response, Pet pet) {
        response.then()
                .body("status", Matchers.is(pet.getStatus()));
        return this;
    }

    public Response findPets(RequestSpecification requestSpec) {
        return given(requestSpec)
                .queryParam("status", Constants.AVAILABLE_STATUS)
                .get("/pet/findByStatus");
    }

    public Pet extractPet(Response response) {
        return response.jsonPath().getObject("find { it.id == 4000 }", Pet.class);
    }

    public PetStoreApiTest2Steps validateExtractedPetId(Pet extractedPet, Pet originalPet) {
        Assert.assertEquals(extractedPet.getId(), originalPet.getId());
        return this;
    }

    public PetStoreApiTest2Steps validateExtractedPetName(Pet extractedPet, Pet originalPet) {
        Assert.assertEquals(extractedPet.getName(), originalPet.getName());
        return this;
    }

    public PetStoreApiTest2Steps validateExtractedPetStatus(Pet extractedPet, Pet originalPet) {
        Assert.assertEquals(extractedPet.getStatus(), originalPet.getStatus());
        return this;
    }
}




