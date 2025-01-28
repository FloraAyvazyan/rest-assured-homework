package steps.objectMappingHomework.planetStep;

import data.Constants;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Planets.Planet;
import models.Planets.PlanetResponse;
import models.Planets.Resident;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlanetSteps {
    Response getPlanets;
    PlanetResponse planetsResponse;
    List<Planet> planets;
    Response residentResponse;
    Optional<Planet> topPlanet;
    Resident resident;

    @Step("Get planets response from API")
    public PlanetSteps getPlanetsResponse(RequestSpecification requestSpec){
        getPlanets = given(requestSpec)
                .when()
                .get("/planets/?format=json");
        return this;
    }

    @Step("Validate response against specification")
    public PlanetSteps validateResponse(ResponseSpecification responseSpec){
        getPlanets.then().assertThat().spec(responseSpec);
        return this;

    }

    @Step("Deserialize planets response to PlanetResponse object")
    public PlanetSteps deserializePlanets() {
        planetsResponse = getPlanets
                .then()
                .extract()
                .as(PlanetResponse.class);
        planets = planetsResponse.results();
        return this;
    }

    @Step("Deserialize the top three most recent planets")
    public PlanetSteps deserializeThreePlanets(){
        List<Planet> threeMostRecent = planets.stream()
                .sorted(Comparator.comparing(Planet::created).reversed())
                .limit(3)
                .toList();

        this.planets = threeMostRecent;
        return this;
    }


    @Step("Validate that each planet has a valid name")
    public PlanetSteps validatePlanetName(){
        for (Planet planet : planets) {
            assertThat(Constants.PLANET_NAME_SHOULD_NOT_BE_NULL_MESSAGE, planet.name(), notNullValue());
        }
        return this;
    }

    @Step("Validate that gravity matches the expected pattern")
    public PlanetSteps validatePlanetGravity(){
        for (Planet planet : planets) {
            assertThat
                    (Constants.GRAVITY_DISMATCHED_MESSAGE,
                            planet.gravity(), matchesPattern("\\d+\\sstandard"));
        }
        return this;
    }

    @Step("Validate that terrain is not null or empty")
    public PlanetSteps validatePlanetTerrain(){
        for (Planet planet : planets) {
            assertThat(Constants.TERRAIN_IS_NULL_MESSAGE, planet.terrain(), not(emptyString()));
        }
        return this;
    }

    @Step("Validate that the planet has a non-empty residents list")
    public PlanetSteps validatePlanetResidents(){
        for (Planet planet : planets) {
            assertThat(Constants.RESIDENTS_SHOULD_NOT_BE_EMPTY_MESSAGE, planet.residents(), not(empty()));

        }
        return this;
    }

    @Step("Validate that each planet URL starts with 'https://' ")
    public PlanetSteps validatePlanetUrl(){
        for (Planet planet : planets) {
            assertThat(Constants.URL_SHOULD_BE_VALID_MESSAGE, planet.url(), startsWith(Constants.URL_STARTS_WITH));
        }
        return this;
    }

    @Step("Find planet with the biggest rotation period")
    public PlanetSteps findPlanetWithBiggestRotationPeriod() {
       topPlanet = planets.stream()
                .max(Comparator.comparingInt(Planet::rotation_period));

        return this;
    }

    @Step("Redirect to the first resident URL of the planet with the biggest rotation period")
    public PlanetSteps redirectToFirstResidentUrl() {
        if (topPlanet.isPresent() && !topPlanet.get().residents().isEmpty()) {
            String residentUrl = topPlanet.get().residents().get(0);
            residentResponse = given()
                    .get(residentUrl);
        } else {
            System.out.println(Constants.NO_RESIDENTS_AVAILABLE);
        }
        return this;
    }

    @Step("Deserialize resident data from the response")
    public PlanetSteps deserializeResident(){
        resident = residentResponse
                .then()
                .extract()
                .as(Resident.class);
        return this;
    }

    @Step("Validate that resident height is greater than 150")
    public PlanetSteps validateHeight() {
        assertThat(resident.getHeight(), greaterThan(150));
        return this;
    }

    @Step("Validate that resident birth year is not null")
    public PlanetSteps validateBirthYear() {
        assertThat(resident.getBirthYear(), notNullValue());
        return this;
    }

    @Step("Validate that resident's hair color matches the expected pattern")
    public PlanetSteps validateHairColor() {
        assertThat(resident.getHairColor(), matchesPattern("^(brown|black|blonde|red|grey)$"));
        return this;
    }

    @Step("Validate that resident's skin color is not null")
    public PlanetSteps validateSkinColor() {
        assertThat(resident.getSkinColor(), notNullValue());
        return this;
    }

    @Step("Validate that resident's gender matches the expected pattern")
    public PlanetSteps validateGender() {
        assertThat(resident.getGender(), matchesPattern("^(male|female|n/a)$"));
        return this;
    }

    @Step("Validate that resident's homeworld URL starts with 'https://' ")
    public PlanetSteps validateHomeworId() {
        assertThat(resident.getHomeworld(), startsWith(Constants.URL_STARTS_WITH));
        return this;
    }

    @Step("Validate that resident's URL starts with 'https://' ")
    public PlanetSteps validateUrl() {
        assertThat(resident.getUrl(), startsWith(Constants.URL_STARTS_WITH));
        return this;
    }


}
