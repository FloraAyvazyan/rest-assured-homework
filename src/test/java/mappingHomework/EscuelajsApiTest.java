package mappingHomework;

import data.Constants;
import models.UserProfile.UserProfile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.mappingHomework.EscuelajsApiTest.AuthService;
import steps.mappingHomework.EscuelajsApiTest.ProfileService;
import steps.mappingHomework.EscuelajsApiTest.UserService;

import static io.restassured.RestAssured.baseURI;

public class EscuelajsApiTest {
    private final UserService userService = new UserService();
    private final AuthService authService = new AuthService();
    private final ProfileService profileService = new ProfileService();

    private static String accessToken;


    @BeforeClass
    public void setup() {
        baseURI = Constants.BASE_URL;
    }

    @Test(priority = 1)
    public void createUserAndAuthenticate() {
        String userPayload = """
            {
                "email": "john@mail.com",
                "name": "John Doe",
                "password": "securePassword123",
                "role": "customer",
                "avatar": "https://i.imgur.com/LDOO4Qs.jpg"
            }
        """;
        userService.createUser(userPayload);

        String loginPayload = """
            {
                "email": "john@mail.com",
                "password": "changeme"
            }
        """;
        accessToken = authService
                .loginUser(loginPayload);
        Assert.assertNotNull(accessToken, Constants.ACCESS_TOKE_SHOULD_NOT_BE_NULL_MESSAGE);

        UserProfile userProfile = profileService.getUserProfile(accessToken);
        profileService
                .validateUserProfileEmail(userProfile,  Constants.EXCEPTED_MAIL )
                .validateUserProfileRole(userProfile, Constants.EXCEPTED_ROLE)
                .validateUserProfileAvatar(userProfile,  Constants.EXCEPTED_AVATAR);


    }
}