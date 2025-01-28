package steps.mappingHomework.EscuelajsApiTest;

import data.Constants;
import io.restassured.response.Response;
import models.UserProfile.UserProfile;
import org.testng.Assert;
import static io.restassured.RestAssured.given;


public class ProfileService {
    public UserProfile getUserProfile(String accessToken) {
        Response profileResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/v1/auth/profile")
                .then()
                .statusCode(Constants.OK_STATUS_CODE)
                .log().all()
                .extract()
                .response();

        return profileResponse.as(UserProfile.class);
    }

    public ProfileService validateUserProfileEmail(UserProfile userProfile, String expectedEmail) {
        Assert.assertEquals(userProfile.getEmail(), expectedEmail, Constants.EMAIL_DOES_NOT_MATCH_MESSAGE);
        return this;
    }

    public ProfileService validateUserProfileRole(UserProfile userProfile, String expectedRole) {
        Assert.assertEquals(userProfile.getRole(), expectedRole, Constants.ROLE_DOES_NOT_MATCH_MESSAGE);
        return  this;
    }

    public ProfileService validateUserProfileAvatar(UserProfile userProfile,  String expectedAvatar) {
        Assert.assertEquals(userProfile.getAvatar(), expectedAvatar, Constants.AVATAR_DOES_NOT_MATCH_MESSAGE);
        return this;
    }

}