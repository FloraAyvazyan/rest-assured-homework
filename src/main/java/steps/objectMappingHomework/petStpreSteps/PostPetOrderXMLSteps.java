package steps.objectMappingHomework.petStpreSteps;

import com.github.javafaker.Faker;
import data.Constants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.PetStoreXML.Category;
import models.PetStoreXML.Pet;
import models.PetStoreXML.TagsItem;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostPetOrderXMLSteps {
    Pet requestPet;
    Pet responsePet;
    Response response;
    Faker faker = new Faker();
    int id = faker.number().numberBetween(1000, 10000);
    int petId = faker.number().numberBetween(1, 100);
    String status = faker.options().option(Constants.AVAILABLE_STATUS, Constants.PENDING_STATUS, Constants.SOLD_STATUS);
    String name = faker.animal().name();

    @Step("Create a new pet with random details")
    public PostPetOrderXMLSteps createPet(){
        requestPet = new Pet()
                .setId(id)
                .setName(name)
                .setCategory(new Category()
                        .setId(petId)
                        .setName(faker.animal().name()))
                .setPhotoUrls(List.of("String"))
                .setTags(List.of(new TagsItem()
                        .setId(id)
                        .setName(faker.animal().name())))
                .setStatus(status);
        return this;
    }

    @Step("Send POST request to create the pet")
    public PostPetOrderXMLSteps postPet (RequestSpecification requestSpec){
        response = given()
                .spec(requestSpec)
                .contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body(requestPet)
                .when()
                .post("/api/v3/pet");
        return this;
    }

    @Step("Validate the response using the provided response specification")
    public PostPetOrderXMLSteps validateResponse(ResponseSpecification responseSpec){
        response.then().assertThat().spec(responseSpec);
        return this;

    }

    @Step("Deserialize the response into Pet object")
    public PostPetOrderXMLSteps deserialize(){
        responsePet = response
                .then()
                .extract()
                .as(Pet.class);
        return this;
    }

    @Step("Validate Pet ID")
    public PostPetOrderXMLSteps validatePetId() {
        assertThat(responsePet.getId(), equalTo(requestPet.getId()));
        return this;
    }

    @Step("Validate Pet Name")
    public PostPetOrderXMLSteps validatePetName() {
        assertThat(responsePet.getName(), equalTo(requestPet.getName()));
        return this;
    }

    @Step("Validate Pet Photo URLs")
    public PostPetOrderXMLSteps validatePetPhotoUrls() {
        assertThat(responsePet.getPhotoUrls(), equalTo(requestPet.getPhotoUrls()));
        return this;
    }

    @Step("Validate Pet Status")
    public PostPetOrderXMLSteps validatePetStatus() {
        assertThat(responsePet.getStatus(), equalTo(requestPet.getStatus()));
        return this;
    }

    @Step("Validate Pet Category ID")
    public PostPetOrderXMLSteps validatePetCategoryId() {
        assertThat(responsePet.getCategory().getId(), equalTo(requestPet.getCategory().getId()));
        return this;
    }

    @Step("Validate Pet Category Name")
    public PostPetOrderXMLSteps validatePetCategoryName() {
        assertThat(responsePet.getCategory().getName(), equalTo(requestPet.getCategory().getName()));
        return this;
    }

    @Step("Validate Pet Tags Size")
    public PostPetOrderXMLSteps validatePetTagsSize() {
        assertThat(responsePet.getTags().size(), equalTo(requestPet.getTags().size()));
        return this;
    }

}
