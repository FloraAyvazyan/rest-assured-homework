package featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.featureHomework.UpdateImageSteps;
import java.io.File;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;


public class UploadPictureTest {
    UpdateImageSteps updateImageSteps = new UpdateImageSteps();

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

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
    public void uploadImageTest() {

        File dogImage = new File(Constants.JPG_URL);
        Response updatedPetWithImage = updateImageSteps
                .UploadImage(dogImage);
        updateImageSteps
                .validatePetStatusCodeWithImage(updatedPetWithImage)
                .validatePetFileNameWithImage(updatedPetWithImage, dogImage)
                .validatePetMetaDateWithImage(updatedPetWithImage)
                .validatePetFileLengthWithImage(updatedPetWithImage, dogImage);

    }

}