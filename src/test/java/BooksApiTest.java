import DataProvider.DataProviderClass;
import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.BooksSteps;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class BooksApiTest {
    BooksSteps booksSteps = new BooksSteps();
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = Constants.BOOKS_BASE_URL;
    }


//ამ ტესტში 1-2-3 ტესტი გაერთიანებული მაქვს
    @Test
    public void testBooks() {
        Response booksResponse = booksSteps.getBooks();
//1
        List<Map<String, Object>> books = booksResponse.jsonPath().getList(Constants.BOOKS_TEXT);
        Assert.assertTrue(books.size() >= 2, Constants.ASSERTION_MESSAGE);
//2
        String firstBookISBN = books.get(0).get(Constants.ISBN).toString();
        String firstBookAuthor = books.get(0).get(Constants.AUTHOR).toString();
        String secondBookISBN = books.get(1).get(Constants.ISBN).toString();
        String secondBookAuthor = books.get(1).get(Constants.AUTHOR).toString();

        booksSteps.
                 validateBookDetails(firstBookISBN, firstBookAuthor)
                .validateBookDetails(secondBookISBN, secondBookAuthor);
    }


    //აქ მაქვს ორი დატაპროვაიდერი, ერთში დინამიურად წანოიღებს მონაცემებს და იმით ამოწმებს და მეორეში ჰარდად მაქვს
    //დინამიური დატაპროვაიდერის შემთხვევაში როცა ერტად ვუშვებ ტესტი მუშაობს მარა ცალკე არა ....
    @Test(dataProvider = "infoProvider", dataProviderClass = DataProviderClass.class)
    public void validateBooks(int index, String expectedIsbn) {
        Response response = RestAssured.get(Constants.BASE_PATH);
        response.then().statusCode(200);

        String retrievedISBN = response.jsonPath().getString("books[" + index + "].isbn");

        Assert.assertEquals(retrievedISBN, expectedIsbn, Constants.ISBN_MISMATCH_MESSAGE + index);
    }


    @Test
    public void deleteBook(){
        Response response = booksSteps.deleteBook();
        assertThat(response.body().asString(), containsString(Constants.USER_NOT_AUTHORIZED_MESSAGE));

    }


}
