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




    @DataProvider(name = "bookingData")
    public Object[][] createBookingData() {
        return new Object[][]{
                {"James", "Brown", 111, true, "2023-01-01", "2024-01-01", "Breakfast", 150, "P12345"},
                {"Jane", "Doe", 200, false, "2025-01-01", "2025-12-01", "Lunch", 200, null},  // passportNo is null
                {"Michael", "Smith", 500, true, "2023-06-01", "2023-06-30", "Dinner", 300, "P67890"},
                {"Sara", "Johnson", 150, false, "2024-03-01", "2024-03-10", "Snacks", 0, null},
        };
    }

}
