package de.vontrostorff.application.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RateResourceTest {

    @Test
    public void testGetRateEndpoint() {
        given()
                .queryParam("carTypeId", 1)
                .queryParam("locationId", 1)
                .queryParam("yearlyKilometersDriven", 1000)
                .when().get("/rate")
                .then()
                .statusCode(200)
                .body("rate", is(64)); // Validate the returned rate
    }

}
