package steps.featureHomework;

import data.Constants;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class BooksStoreSteps {

    public BooksStoreSteps validateFirstAuthor(Response response) {
        response
                .then()
                .assertThat()
                .body("books[0].author", Matchers.is(Constants.RICHARD_SILVERMAN));
        return this;
    }

    public BooksStoreSteps validateSecondAuthor(Response response) {
        response
                .then()
                .assertThat()
                .body("books[1].author", Matchers.is(Constants.ADDY_OSMANI));
        return this;
    }

    public  BooksStoreSteps validatePages (Response response) {
        response
                .then()
                .assertThat()
                .body("books.pages", everyItem(lessThan(Constants.BOOKS_PAGE)));

        return this;
    }
}
