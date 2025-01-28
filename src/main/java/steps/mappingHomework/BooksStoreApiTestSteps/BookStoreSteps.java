package steps.mappingHomework.BooksStoreApiTestSteps;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import models.BookStore.BooksItem;
import models.BookStore.BooksResponse;
import org.testng.Assert;

public class BookStoreSteps {

    public Response getBooksApiResponse() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get(Constants.BASE_PATH);
    }

    public BookStoreSteps validateBooksPages(BooksResponse booksResponse) {
        List<BooksItem> books = booksResponse.getBooks();
        for (BooksItem book : books) {
            Assert.assertTrue(book.getPages() < 1000, "Book " + book.getTitle() + " has more than 1000 pages");
        }
        return this;
    }

    public BookStoreSteps validateBookLastBookAuthor(BooksResponse booksResponse) {
        List<BooksItem> books = booksResponse.getBooks();
        int totalBooks = books.size();
        String lastBookAuthor = books.get(totalBooks - 1).getAuthor();
        Assert.assertEquals(lastBookAuthor, Constants.LAST_AUTHOR_NAME, Constants.LAST_AUTHOR_DONT_MATCH_MESSAGE);
        return this;
    }

    public BookStoreSteps validateBookSecondLastBookAuthor(BooksResponse booksResponse) {
        List<BooksItem> books = booksResponse.getBooks();
        int totalBooks = books.size();
        String secondLastBookAuthor = books.get(totalBooks - 2).getAuthor();
        Assert.assertEquals(secondLastBookAuthor, Constants.SECOND_LAST_AUTHOR, Constants.SECOND_LAST_AUTHOR_DONT_MATCH_MESSAGE);
        return this;
    }

    public BookStoreSteps assertStatusCode(Response response) {
        Assert.assertEquals(response.getStatusCode(), Constants.OK_STATUS_CODE, Constants.FAILED_TO_GET_BOOKS_MESSAGE);
        return this;
    }
}