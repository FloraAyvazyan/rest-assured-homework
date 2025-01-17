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
//1. Send a GET request to `https://bookstore.toolsqa.com/BookStore/v1/Books` to retrieve the list of books.
//
//2. Extract the ISBN and author information of the first and second books.
//
//3. For each book:
//
//   - Send a GET request to `https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN={isbn}`, where `{isbn}` is the extracted ISBN.
//   - Ensure that the response status code is 200 (OK).
//   - Parse the response and extract the author information.
//   - Compare the retrieved author information with the expected author.
//   - Validate that the response contains all necessary information about the book, such as `title`, `ISBN`, `publish_date`, and `pages`.

//ამ ტესტში 1-2-3 ტესტი გაერთიანებული მაქვს
    @Test
    public void testBooks() {
        Response booksResponse = booksSteps.getBooks();
//1
        List<Map<String, Object>> books = booksResponse.jsonPath().getList(Constants.BOOKS_TEXT);
        System.out.println(books.size());
        Assert.assertTrue(books.size() >= 2, Constants.ASSERTION_MESSAGE);
//2

        for(int i = 0; i < 2; i++){
            String firstBookISBN = books.get(i).get(Constants.ISBN).toString();
            String firstBookAuthor = books.get(i).get(Constants.AUTHOR).toString();
            booksSteps.
                    validateBookDetails(firstBookISBN, firstBookAuthor);

        }

    }


    //4. Implement a `data provider` to iterate over different `index` and `ISBN` combinations.
    //აქ მაქვს ორი დატაპროვაიდერი, ერთში დინამიურად წანოიღებს მონაცემებს და იმით ამოწმებს და მეორეში ჰარდად მაქვს
    //დინამიური დატაპროვაიდერის შემთხვევაში როცა ერტად ვუშვებ ტესტი მუშაობს მარა ცალკე არა ....
    @Test(dataProvider = "infoProvider", dataProviderClass = DataProviderClass.class)
    public void validateBooks(int index, String expectedIsbn) {
        Response response = RestAssured.get(Constants.BASE_PATH);
        response.then().statusCode(200);

        String retrievedISBN = response.jsonPath().getString("books[" + index + "].isbn");

        Assert.assertEquals(retrievedISBN, expectedIsbn, Constants.ISBN_MISMATCH_MESSAGE + index);
    }

//5. Send a DELETE request to `BookStore/v1/Book` to remove the book.
//
//   - Validate that the response is an Unauthorized `401` status code.
//   - Ensure that the `message` is "User not authorized!".
    @Test
    public void deleteBook(){
        Response response = booksSteps.deleteBook();
        assertThat(response.body().asString(), containsString(Constants.USER_NOT_AUTHORIZED_MESSAGE));

    }


}
