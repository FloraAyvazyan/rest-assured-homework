package steps.basicHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class BooksSteps {

    public Response getBooks() {
        return RestAssured
                .given()
                .log().all()
                .when()
                .get(Constants.BASE_PATH)
                .then()
                .log().all()
                .statusCode(Constants.OK_STATUS_CODE)
                .extract()
                .response();
    }

    public Response deleteBook() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .delete(Constants.BASE_PATH)
                .then()
                .statusCode(401)
                .extract()
                .response();
    }

    public Response getBookByIsbn(String isbn) {
       return RestAssured
                .given()
                .log().all()
                .when()
                .get("/BookStore/v1/Book?ISBN=" + isbn)
                .then()
                .log().all()
                .statusCode(Constants.OK_STATUS_CODE)
                .extract()
                .response();
    }

    public BooksSteps validateBookDetails(String isbn, String expectedAuthor) {
        Response bookResponse = getBookByIsbn(isbn);

        //ამეების გადატანაც არ ვიცი კონსტატნტებში საწიროა თუ არა (⊙_⊙;)?
        String retrievedAuthor = bookResponse.jsonPath().getString("author");
        Assert.assertEquals(retrievedAuthor, expectedAuthor, "Author mismatch for ISBN: " + isbn);
        Assert.assertNotNull(bookResponse.jsonPath().getString("title"), Constants.TITLE_IS_MISSING_MESSAGE);
        Assert.assertNotNull(bookResponse.jsonPath().getString("isbn"), Constants.ISBN_IS_MISSING_MESSAGE);
        Assert.assertNotNull(bookResponse.jsonPath().getString("publish_date"), Constants.PUBLISH_DATA_IS_MISSING);
        Assert.assertNotNull(bookResponse.jsonPath().getInt("pages"), Constants.PAGE_INFORMATION_IS_MISSING_MESSAGE);
        return this;
    }


}
