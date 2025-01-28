package steps.mappingHomework.F1DriverTest;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.F1.DriversItem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class F1DriverTestSteps {

    public Response getResponse(){
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get();
    }

    public DriversItem extractDriver(Response response){
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        return jsonPath.getObject("MRData.DriverTable.Drivers[0]", DriversItem.class);
    }


    public F1DriverTestSteps validateDriverId(DriversItem actualDriver) {
        String expectedDriverId = Constants.DRIVER_ID;
        assertThat(actualDriver.getDriverId(), equalTo(expectedDriverId));
        return this;
    }

    public F1DriverTestSteps validatePermanentNumber(DriversItem actualDriver) {
        String expectedPermanentNumber = Constants.PERMANENT_NUMBER;
        assertThat(actualDriver.getPermanentNumber(), equalTo(expectedPermanentNumber));
        return this;

    }

    public F1DriverTestSteps validateDriverCode(DriversItem actualDriver) {
        String expectedDriverCode = Constants.CODE;
        assertThat(actualDriver.getCode(), equalTo(expectedDriverCode));
        return this;

    }

    public F1DriverTestSteps validateDriverUrl(DriversItem actualDriver) {
        String expectedUrl = Constants.URL;
        assertThat(actualDriver.getUrl(), equalTo(expectedUrl));
        return this;

    }

    public F1DriverTestSteps validateGivenName(DriversItem actualDriver) {
        String expectedGivenName = Constants.GIVEN_NAME;
        assertThat(actualDriver.getGivenName(), equalTo(expectedGivenName));
        return this;

    }

    public F1DriverTestSteps validateFamilyName(DriversItem actualDriver) {
        String expectedFamilyName =Constants.FAMILY_NAME;
        assertThat(actualDriver.getFamilyName(), equalTo(expectedFamilyName));
        return this;

    }

    public F1DriverTestSteps validateDateOfBirth(DriversItem actualDriver) {
        String expectedDateOfBirth = Constants.DATE_OF_BIRTH;
        assertThat(actualDriver.getDateOfBirth(), equalTo(expectedDateOfBirth));
        return this;

    }

    public F1DriverTestSteps validateNationality(DriversItem actualDriver) {
        String expectedNationality = Constants.NATIONALITY;
        assertThat(actualDriver.getNationality(), equalTo(expectedNationality));
        return this;

    }

}
