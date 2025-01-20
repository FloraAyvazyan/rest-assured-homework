package steps.featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import java.io.File;
import static io.restassured.RestAssured.requestSpecification;

public class UpdateImageSteps {
    public Response UploadImage(File file){

        return RestAssured.given()
                .spec(requestSpecification)
                .pathParams("petId", Constants.UPDATED_PET_ID)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", Constants.PICTURE_DATA)
                .multiPart("file", file)
                .post("/pet/{petId}/uploadImage");
    }


    public UpdateImageSteps validatePetStatusCodeWithImage(Response response){
        response
                .then()
                .assertThat()
                .statusCode(Constants.OK_STATUS_CODE);
        return this;
    }

    public UpdateImageSteps validatePetMetaDateWithImage(Response response){
        response
                .then()
                .assertThat()
                .body("message", Matchers.containsString(Constants.PICTURE_DATA));
        return this;
    }

    public UpdateImageSteps validatePetFileNameWithImage(Response response,  File pictureFile){
        response
                .then()
                .assertThat()
                .body("message", Matchers.containsString(pictureFile.getName()));
        return this;
    }

    public UpdateImageSteps validatePetFileLengthWithImage(Response response, File pictureFile){
        response
                .then()
                .assertThat()
                .body("message", Matchers.containsString(pictureFile.length() + " bytes"));
        return this;
    }
}
