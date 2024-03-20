package de.vontrostorff.infrastructure;

import com.opencsv.*;
import de.vontrostorff.domain.model.Location;
import de.vontrostorff.domain.ports.incoming.LocationCommands;
import de.vontrostorff.domain.service.LocationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * We do not have too much information about how the csv import works.
 * Let's only do it when a special env variable is set. Otherwise, it would be done in the test as well.
 * Let's just import it like this for the time being.
 * Also let's random generate multipliers because we have no info about where they come from.
 */
@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
@Named("customListener")
public class CSVImport {
    private final LocationCommands locationCommands;

    @Transactional
    public void eagerInit(@Observes Startup unused) {
        if(System.getenv("CSVImport") == null){
            return;
        }
        final var locations = readFile();
        locationCommands.createLocations(locations);
    }

    @SneakyThrows
    public List<Location> readFile() {
        //Needs to be read as a stream, works better in prod packaging
        try(InputStream resourceStream = getClass().getResourceAsStream("/" + "import.csv")){
            assert resourceStream != null; // if file not found
            try (Reader reader = new InputStreamReader(resourceStream)) {
                try (final var csvReader = new CSVReaderHeaderAware(reader)) {
                    return parseLocations(csvReader);
                }
            }
        }
    }

    @SneakyThrows
    private List<Location> parseLocations(CSVReaderHeaderAware csvReader) {
        String[] value;
        final var res = new ArrayList<Location>();
        while ((value = csvReader.readNext(" REGION1"," REGION3"," REGION4", " ORT", " POSTLEITZAHL")) != null)
        {
            final var location = new Location();
            mapArrayToLocation(location, value);
            res.add(location);
        }
        return res;
    }

    private static void mapArrayToLocation(Location location, String[] value) {
        location.setRegion1(value[0]);
        location.setRegion3(value[1]);
        location.setRegion4(value[2]);
        location.setLocation(value[3]);
        location.setPlz(Integer.parseInt(value[4]));
        location.setMultiplier(getMultiplier());
    }

    /**
     * Who knows where that comes from…
     */
    private static double getMultiplier() {
        return Math.random() * 500; // 500 weil Baum
    }
}
