package de.vontrostorff.domain.service;

import de.vontrostorff.domain.ports.incoming.RateCommands;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class RateService implements RateCommands {
    private final LocationService locationService;
    private final CarTypeService carTypeService;
    private final KilometersService kilometersService;

    public long getRate(long locationId, long carTypeId, int kilometersYearly){
        return (long) Math.floor(locationService.getMultiplier(locationId)
                        * carTypeService.getMultiplier(carTypeId)
                        * kilometersService.getMultiplier(kilometersYearly));
    }
}
