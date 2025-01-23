package steps.featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.BookStore.BooksItem;
import models.BookStore.BooksResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class BooksStoreSteps {


    public Response getAllBook(){
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books");
    }

    public BooksResponse getBooks(Response response){
        return response
                .then()
                .extract()
                .as(BooksResponse.class);
    }

    public List<BooksItem> extractBooksFromResponse(Response response) {
        return response.jsonPath().getList("books", BooksItem.class);
    }

    public BooksStoreSteps validateFirstAuthor(BooksResponse booksResponse){
        assertThat(booksResponse.getBooks().get(0).getAuthor(), equalTo(Constants.RICHARD_SILVERMAN));
        return this;
    }

    public BooksStoreSteps validateSecondAuthor(BooksResponse booksResponse){
        assertThat(booksResponse.getBooks().get(1).getAuthor(), equalTo(Constants.ADDY_OSMANI));
        return this;
    }




    public BooksStoreSteps validatePages(BooksResponse booksResponse){
        for (BooksItem book : booksResponse.getBooks()){
            assertThat(book.getPages(), lessThan(1000));
        }
        return this;
    }
}