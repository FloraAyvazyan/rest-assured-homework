package steps;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

public class PetStoreSteps {
    public String createRequestBody() {
        return """
            {
                "id": 101,
                "petId": 202,
                "quantity": 5,
                "shipDate": "2025-01-18T15:30:00.000+0000",
                "status": "approved",
                "complete": false
            }
        """;
    }

    public Response createOrder(String requestBody) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .accept(ContentType.JSON)
                .when()
                .post("/store/order");
    }

    //აქ ვერ მივხვდი ამ შემთხვევაში როგორაა კარგი პრაქტიკა ყველაფერი ერთად რო მქონდე თუ მაქსიმალურად ჩაშლილად რო მქონდე სტეპები
    //ამიტომ ორივე დავტოვე
    public PetStoreSteps validateOrderResponse(Response response) {
        response.then()
                .statusCode(Constants.OK_STATUS_CODE)
                //ამ ველების გადატანა კონსტატებში არ ვიცი საჭირო იყო თუ არა, და ამიტომ ყველგან ასე დავტოვე (⊙_⊙;)
                .body("id", Matchers.is(101))
                .body("petId", Matchers.is(202))
                .body("quantity", Matchers.is(5))
                .body("shipDate", Matchers.is(Constants.QUANTITY_MATCHER))
                .body("status", Matchers.is("approved"))
                .body("complete", Matchers.is(false));
        return this;
    }


    //პარამეტრის გაცემაც ვიცი რომ კარგი პრაქტიკა არაა, მარა ბევრგან მაქვს პარამეტრების გარეშე ვერ ვქენი ＞﹏＜
    public PetStoreSteps validateOrderResponseComplete(Response response) {
        response.then()
                .body("complete", Matchers.is(false));
        return this;
    }


    public PetStoreSteps validateOrderResponseStatus(Response response) {
        response.then()
                .body("status", Matchers.is("approved"));
        return this;
    }


    public PetStoreSteps validateOrderResponseShipDate(Response response) {
        response.then()
                .body("shipDate", Matchers.is("2025-01-18T15:30:00.000+0000"));
        return this;
    }


    public PetStoreSteps validateOrderResponseQuantity(Response response) {
        response.then()
                .body("quantity", Matchers.is(5));
        return this;
    }


    public PetStoreSteps validateOrderResponseStatusCode(Response response) {
        response.then()
                .statusCode(Constants.OK_STATUS_CODE);
        return this;
    }

    public PetStoreSteps validateOrderResponsePetId(Response response) {
        response.then()
                .body("petId", Matchers.is(202));

        return this;
    }

    public PetStoreSteps validateOrderResponseId(Response response) {
        response.then()
                .statusCode(200)
                .body("id", Matchers.is(101));
        return this;
    }



    public Response  update(int Index){
        return RestAssured
                .given()
                .formParams("petId", Index,
                        "name", "Boody",
                        "status", "unapproved")
                .accept(ContentType.JSON)
                .when()
                .post("/pet/{petId}", Index);
    }


    public Response login(String username, String password) {
        return RestAssured
                .given()
                .queryParam(Constants.USERNAME, username)
                .queryParam(Constants.PASSWORD, password)
                .accept(ContentType.JSON)
                .when()
                .get("/user/login");
    }

    public PetStoreSteps validateLoginStatusCode(Response response) {
        response.then().statusCode(200);
        return this;
    }


    public PetStoreSteps validateSignificantDigitsLength(String significantDigits) {
        Assert.assertEquals(significantDigits.length(), Constants.EXCEPTED_NUMBERS, Constants.EXTRACTED_NUMBERS_LENGTH_MESSAGE);
        return this;
    }


    //ამევის გადატანაც util-ში უმჯობესია იყოს თუ აქ?
    public String extractMessage(Response response) {
        return response.jsonPath().getString(Constants.MESSAGE);
    }

    public String extractSignificantDigits(String responseMessage) {
        String significantNumber = responseMessage.replaceAll("[^0-9]", "");
        Assert.assertTrue(significantNumber.length() >= Constants.EXCEPTED_NUMBERS, Constants.DOES_NOT_CONTAINS_10_NUMBERS_MESSAGE);
        return significantNumber.substring(0, 10);
    }

}
