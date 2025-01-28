package steps.featureHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.BookerHerokuApp.Booking;
import models.BookerHerokuApp.BookingDates;
import org.json.JSONObject;



public class BookingHerokuAppSteps {
    JSONObject tokenRequest = getTokenRequestData();
    final String baseUri = Constants.HEROKU_APP_BASE_URL;


    public JSONObject JSONObjectParameterization (){
        return new JSONObject()
                .put("firstname", Constants.NAME)
                .put("lastname", Constants.SURNAME)
                .put("totalprice", Constants.TOTAL_PRICE)
                .put("depositpaid", false)
                .put("bookingdates", new JSONObject()
                        .put("checkin", Constants.CHECK_IN)
                        .put("checkout", Constants.CHECKOUT))
                .put("additionaleeds", Constants.ADDITIONAL_LEEDS);
    }

    public static JSONObject getTokenRequestData() {
        JSONObject tokenRequest = new JSONObject();
        tokenRequest.put("username", Constants.TOKEN_USERNAME);
        tokenRequest.put("password", Constants.TOKEN_PASSWORD);

        return tokenRequest;
    }

    public String getToken() {
        Response tokenResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(tokenRequest.toString())
                .post(baseUri + "/auth");

        String token = tokenResponse.jsonPath().getString("token");
        return token;
    }

    public Response BookingUpdate(RequestSpecification requestSpec, JSONObject jsonObject, String token){
        return RestAssured
                .given()
                .spec(requestSpec)
                .body(jsonObject.toString())
                .header("Cookie", "token=" + token)
                .when()
                .put("/booking/1");
    }

    public void validateLogAndStatusCode(Response response) {
        response
                .then()
                .log()
                .ifStatusCodeIsEqualTo(201)
                .extract()
                .statusCode();
    }

    /// ///////////////////

    public Booking createBookingPayload() {
        BookingDates bookingDates = new BookingDates();
//        bookingDates.setCheckin(Constants.CHECK_IN);
//        bookingDates.setCheckout(Constants.CHECKOUT);

        Booking booking = new Booking();
        booking.setFirstname(Constants.NAME);
        booking.setLastname(Constants.SURNAME);
        booking.setTotalprice(Constants.TOTAL_PRICE);
        booking.setDepositpaid(false);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds(Constants.ADDITIONAL_LEEDS);

        return booking;
    }

    public String getToken2() {
        String tokenRequestBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}",
                Constants.TOKEN_USERNAME, Constants.TOKEN_PASSWORD);

        Response tokenResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(tokenRequestBody)
                .post(Constants.HEROKU_APP_BASE_URL + "/auth");

        return tokenResponse.jsonPath().getString("token");
    }

    public Response BookingUpdate(RequestSpecification requestSpec, Booking booking, String token) {
        return RestAssured
                .given()
                .spec(requestSpec)
                .body(booking)
                .header("Cookie", "token=" + token)
                .when()
                .put("/booking/1");
    }



}


