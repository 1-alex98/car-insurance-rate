package de.vontrostorff.application.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CarTypeResourceTest {

    @Test
    public void testGetCarTypesEndpoint() {
        given()
                .when().get("/car-type")
                .then()
                .statusCode(200)
                .body(
                        "", containsInAnyOrder(
                                allOf(hasEntry("name", "Model 3"), hasEntry("brand", "Telsa")),
                                allOf(hasEntry("name", "Model 2"), hasEntry("brand", "Telsa")),
                                allOf(hasEntry("name", "B-Max"), hasEntry("brand", "Ford"))
                        )
                );
    }

}
