package steps.basicHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;


public class OpenLibrarySteps {
    public Response getBooksByKeyword(String keyword) {
        return RestAssured
                .given()
                .queryParam("q", keyword)
                .accept(ContentType.JSON)
                .when()
                .get("/search.json");
    }

    //აქ სოფტ ასსერტით გავაკეთე ვალიდაცია
    public OpenLibrarySteps validateFirstBookPlaces(Response response) {
        response.then()
                .body("docs[0].place", Matchers.hasItems(Constants.FIRST_BOOK_PLACE,
                        Constants.FIRST_BOOK_PLACE_2,
                        Constants.FIRST_BOOK_PLACE_3));
        return this;
    }

    public OpenLibrarySteps validateFirstBookTitleAndAuthor(Response response) {
        response.then()
                .body("docs[0].author_name[0]", Matchers.is(Constants.HARRY_POTTER_AUTHOR),
                        "docs[0].title", Matchers.is(Constants.FIRST_BOOK_TITLE));
        return this;
    }

}
