package steps.featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.FileUpload.FileUploadMetadata;
import org.hamcrest.Matchers;

import java.io.File;

import static io.restassured.RestAssured.requestSpecification;

public class UpdateImageSteps {

    public Response uploadImage(FileUploadMetadata metadata, File file) {
        return RestAssured.given()
                .spec(requestSpecification)
                .pathParams("petId", Constants.UPDATED_PET_ID)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", metadata.getAdditionalMetadata())
                .multiPart("file", file)
                .post("/pet/{petId}/uploadImage");
    }

    public UpdateImageSteps validatePetStatusCodeWithImage(Response response) {
        response
                .then()
                .assertThat()
                .statusCode(Constants.OK_STATUS_CODE);
        return this;
    }

    public UpdateImageSteps validatePetMetaDateWithImage(Response response, FileUploadMetadata metadata) {
        response
                .then()
                .assertThat()
                .body("message", Matchers.containsString(metadata.getAdditionalMetadata()));
        return this;
    }

    public UpdateImageSteps validatePetFileNameWithImage(Response response, FileUploadMetadata metadata) {
        response
                .then()
                .assertThat()
                .body("message", Matchers.containsString(metadata.getFileName()));
        return this;
    }

    public UpdateImageSteps validatePetFileLengthWithImage(Response response, FileUploadMetadata metadata) {
        response
                .then()
                .assertThat()
                .body("message", Matchers.containsString(metadata.getFileLength() + " bytes"));
        return this;
    }
}
