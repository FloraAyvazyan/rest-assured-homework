package DataProvider;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import io.restassured.RestAssured;
import java.util.List;
import java.util.Map;

public class DataProviderClass {
    @DataProvider(name = "dynamicInfoProvider")
    public Object[][] dynamicInfoProvider() {
        Response response = RestAssured.get("/BookStore/v1/Books");
        response.then().statusCode(200);

        List<Map<String, Object>> allBooks = response.jsonPath().getList("books");

        Object[][] data = new Object[allBooks.size()][2];

        for (int i = 0; i < allBooks.size(); i++) {
            Map<String, Object> book = allBooks.get(i);
            data[i][0] = i;
            data[i][1] = book.get("isbn");
        }

        return data;
    }


    @DataProvider
    public Object[][] infoProvider(){
        return new Object[][]{
                {0, "9781449325862"},
                {1, "9781449331818"},
                {2, "9781449337711"},
                {3, "9781449365035"},
                {4, "9781491904244"},
                {5, "9781491950296"},
                {6, "9781593275846"},
                {7, "9781593277574"},
        };
    }

}
