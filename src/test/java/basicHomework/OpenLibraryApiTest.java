package basicHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.basicHomework.OpenLibrarySteps;

public class OpenLibraryApiTest {
    OpenLibrarySteps openLibrarySteps = new OpenLibrarySteps();

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = Constants.OPEN_LIBRARY_BASE_URL;

    }
    //10. Send a real-world GET request to `https://openlibrary.org/search.json?q={keyword}` where the `q` parameter `{keyword}` is `Harry Potter`.
    //
    //    - Validate that the response contains books.
    //    - Ensure that the first book in the `docs` section has the title "Harry Potter and the Philosopher's Stone" and the `author_name` is "J. K. Rowling".
    //    - Validate that the first book's `place` array contains ("England", "Hogwarts School of Witchcraft and Wizardry", "Platform Nine and Three-quarters").
    @Test
    public void testCase() {
        Response response = openLibrarySteps.getBooksByKeyword(Constants.HARRY_POTTER);
        response.then().statusCode(200);
        openLibrarySteps
                .validateFirstBookPlaces(response)
                .validateFirstBookTitleAndAuthor(response);
    }


}