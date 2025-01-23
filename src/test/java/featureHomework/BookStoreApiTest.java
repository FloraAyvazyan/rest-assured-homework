package featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.BookStore.BooksResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.featureHomework.BooksStoreSteps;

public class BookStoreApiTest {
    BooksStoreSteps booksStoreSteps = new BooksStoreSteps();

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = Constants.BOOKS_BASE_URL;
    }

    @Test
    public void booksTest() {
        Response response = booksStoreSteps
                .getAllBook();
        BooksResponse books = booksStoreSteps
                .getBooks(response);
        booksStoreSteps
                .validatePages(books)
                .validateFirstAuthor(books)
                .validateSecondAuthor(books);
    }
}