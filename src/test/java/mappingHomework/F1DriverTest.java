package mappingHomework;

import data.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.F1.DriversItem;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.mappingHomework.F1DriverTest.F1DriverTestSteps;

public class F1DriverTest {
    F1DriverTestSteps f1DriverTestSteps = new F1DriverTestSteps();

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = Constants.ERGAST_COM_URL;
    }

    @Test
    public void driverTest(){
        Response response = f1DriverTestSteps
                .getResponse();
        DriversItem extractedDriver = f1DriverTestSteps
                .extractDriver(response);
        f1DriverTestSteps
                .validateDriverId(extractedDriver)
                .validatePermanentNumber(extractedDriver)
                .validateDriverCode(extractedDriver)
                .validateDriverUrl(extractedDriver)
                .validateGivenName(extractedDriver)
                .validateFamilyName(extractedDriver)
                .validateDateOfBirth(extractedDriver)
                .validateNationality(extractedDriver);
    }
}