package featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.basicHomework.BooksSteps;
import steps.featureHomework.BooksStoreSteps;


public class BookStoreApiTest {
    BooksSteps booksSteps = new BooksSteps();
    BooksStoreSteps booksStoreSteps = new BooksStoreSteps();

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = Constants.BOOKS_BASE_URL;
    }


    @Test
    public void booksTest(){
        Response booksResponse = booksSteps.getBooks();
        booksStoreSteps
                .validateFirstAuthor(booksResponse)
                .validateSecondAuthor(booksResponse)
                .validatePages(booksResponse);
    }

}
