import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.OpenLibrarySteps;

public class OpenLibraryApiTest {
    OpenLibrarySteps openLibrarySteps = new OpenLibrarySteps();

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = Constants.OPEN_LIBRARY_BASE_URL;

    }
    @Test
    public void testCase() {
        Response response = openLibrarySteps.getBooksByKeyword(Constants.HARRY_POTTER);
        response.then().statusCode(200);
        openLibrarySteps
                .validateFirstBookPlaces(response)
                .validateFirstBookTitleAndAuthor(response);
    }


}