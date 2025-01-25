package objectMappingHomework;

import data.Constants;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;
import steps.objectMappingHomework.planetStep.PlanetSteps;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@Epic("Planet API Testing")
public class PlanetsApiTest {
    PlanetSteps planetSteps = new PlanetSteps();

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        RestAssured.filters(new  AllureRestAssured());

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Constants.PLANET_BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(Constants.OK_STATUS_CODE)
                .expectBody(not(empty()))
                .build();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Planet API")
    @Story("Test to verify planets information")
    @Description("This test verifies that the Planet API returns the correct data " +
            "including validation of planet details and redirection to the first resident URL.")
    public void planetTest(){

        planetSteps
                .getPlanetsResponse(requestSpec);
        planetSteps
                .validateResponse(responseSpec)
                .deserializePlanets()
                .deserializeThreePlanets()
                //   - Create a test scenario encompassing at least five field validations.
                .validatePlanetName()
                .validatePlanetResidents()
                .validatePlanetTerrain()
                .validatePlanetGravity()
                .validatePlanetUrl()
                //   - Get top 1 planet by `rotation_period` and redirect to first url in `residents`.
                .findPlanetWithBiggestRotationPeriod()
                .redirectToFirstResidentUrl()
                //   - Use lombok to configure result pojo
                .deserializeResident()
                //and create some validations.
                .validateHeight()
                .validateBirthYear()
                .validateHairColor()
                .validateSkinColor()
                .validateGender()
                .validateHomeworId()
                .validateUrl();
    }
}
