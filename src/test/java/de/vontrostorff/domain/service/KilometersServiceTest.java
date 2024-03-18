package de.vontrostorff.domain.service;

import de.vontrostorff.domain.model.Kilometers;
import de.vontrostorff.domain.ports.outgoing.KilometersRepo;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class KilometersServiceTest {

    de.vontrostorff.infrastructure.repository.KilometersRepo mock;

    @Inject
    KilometersService kilometersService;

    @BeforeEach
    public void setup() {
        mock = Mockito.mock(de.vontrostorff.infrastructure.repository.KilometersRepo.class);
        QuarkusMock.installMockForType(mock, KilometersRepo.class);
    }

    @Test
    public void given_kilometerIsInserted_when_overlappingExists_then_exceptionIsThrown() {
        // Arrange
        Kilometers kilometers = new Kilometers();
        kilometers.setYearlyKilometersDrivenLower(1000);
        kilometers.setYearlyKilometersDrivenUpper(2000);

        Kilometers overlappingKilometers = new Kilometers();
        overlappingKilometers.setYearlyKilometersDrivenLower(1500);
        overlappingKilometers.setYearlyKilometersDrivenUpper(null);

        Mockito.when(mock.listAll()).thenReturn(Collections.singletonList(overlappingKilometers));


        // Act and Assert
        assertThrows(ConstraintViolationException.class, () -> kilometersService.createOrUpdateKilometers(kilometers));
    }

    @Test
    public void given_kilometerIsInserted_when_noOverlappingExists_then_noExceptionIsThrown() {
        // Arrange
        Kilometers kilometers = new Kilometers();
        kilometers.setYearlyKilometersDrivenLower(1000);
        kilometers.setYearlyKilometersDrivenUpper(2000);

        Kilometers notOverlappingKilometers = new Kilometers();
        notOverlappingKilometers.setYearlyKilometersDrivenLower(null);
        notOverlappingKilometers.setYearlyKilometersDrivenUpper(10);

        Mockito.when(mock.listAll()).thenReturn(Collections.singletonList(notOverlappingKilometers));


        // Act and Assert no exception
        kilometersService.createOrUpdateKilometers(kilometers);
    }
}
