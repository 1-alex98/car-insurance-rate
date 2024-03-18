package de.vontrostorff.application.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LocationResourceTest {

    @Test
    public void testGetPlacesEndpoint() {
        given()
          .queryParam("plz", 1234)
          .when().get("/place")
          .then()
             .statusCode(200)
             .body("$.size()", is(1), // Check that one place is returned
                   "[0].id", is(3), // Validate the properties of the returned place
                   "[0].region1", is("region 1"),
                   "[0].region3", is("region 3"),
                   "[0].region4", is("region 4"),
                   "[0].plz", is(1234),
                   "[0].location", is("location"));
    }
    @Test
    public void testGetPlacesEndpointNoPLZ() {
        given()
          .when().get("/place")
          .then()
             .statusCode(400);
    }
}
