package mappingHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.BookStore.BooksResponse;
import steps.mappingHomework.BooksStoreApiTestSteps.BookStoreSteps;

public class BookStoreApiTest {

    private static final String BASE_URL = Constants.BOOKS_BASE_URL;
    private BookStoreSteps bookStoreSteps = new BookStoreSteps();

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testBooksApi() {
        Response response = bookStoreSteps.getBooksApiResponse();
        bookStoreSteps.assertStatusCode(response);
        BooksResponse booksResponse = response.as(BooksResponse.class);
        bookStoreSteps.validateBooksPages(booksResponse)
                .validateBookLastBookAuthor(booksResponse)
                .validateBookSecondLastBookAuthor(booksResponse);
    }
}
