package de.vontrostorff.domain.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RateServiceTest {

    @Inject
    RateService rateService;

    @Test
    void given_idsFroMtestData_when_givenToService_the_floorsAccordingMultipliers() {
        final var rate = rateService.getRate(
                1, 1, 1000
        );
        assertEquals((long)Math.floor(0.8 *100.2 * 0.8), rate); //from test sql data
    }
}