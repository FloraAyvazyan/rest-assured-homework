package objectMappingHomework;

import data.Constants;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.objectMappingHomework.petStpreSteps.PostPetOrderSteps;
import steps.objectMappingHomework.petStpreSteps.PostPetOrderXMLSteps;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@Epic("Pet Store API")
public class PetStoreApiTest {
    PostPetOrderSteps postPetOrderSteps = new PostPetOrderSteps();
    PostPetOrderXMLSteps postPetOrderXMLSteps = new PostPetOrderXMLSteps();

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.PET_STORE_URL)
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(Constants.OK_STATUS_CODE)
                .expectBody(not(empty()))
                .build();
    }

    @Test
    @Feature("Create and validate pet in Pet Store API")
    @Story("As a user, I want to create a new pet and validate its details.")
    @Description("This test validates the full lifecycle of creating a pet in the Pet Store API, including data creation, posting, and response validation.")
    @Severity(SeverityLevel.NORMAL)
    public void petStoreTest(){
        postPetOrderSteps
                .createPet()
                .postPet(requestSpec)
                .validateResponse(responseSpec)
                .deserialize()
                .validatePetId()
                .validateQuantity()
                .validateId()
                .validateShipDate()
                .validateCompleteStatus()
                .validateStatus();

    }


    @Test
    @Feature("Create and validate pet in Pet Store API using XML")
    @Story("As a user, I want to create a new pet and validate its details using XML response format.")
    @Description("This test validates the full lifecycle of creating a pet in the Pet Store API using XML, including data creation, posting, and response validation.")
    @Severity(SeverityLevel.NORMAL)
    public void petStoreXMLTest(){
        postPetOrderXMLSteps
                .createPet()
                .postPet(requestSpec)
                .validateResponse(responseSpec)
                .deserialize()
                .validatePetId()
                .validatePetName()
                .validatePetPhotoUrls()
                .validatePetStatus()
                .validatePetCategoryId()
                .validatePetCategoryName()
                .validatePetTagsSize();
    }
}
