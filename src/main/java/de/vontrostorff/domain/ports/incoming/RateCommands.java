package de.vontrostorff.domain.ports.incoming;

public interface RateCommands {
    long getRate(long locationId, long carTypeId, int kilometersYearly);
}
