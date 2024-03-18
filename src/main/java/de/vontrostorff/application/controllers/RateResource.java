package de.vontrostorff.application.controllers;

import de.vontrostorff.application.dtos.Rate;
import de.vontrostorff.domain.ports.incoming.RateCommands;
import de.vontrostorff.domain.service.RateService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/rate")
@RequiredArgsConstructor
public class RateResource {
    private final RateCommands rateCommands;
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Rate getRate(@QueryParam("carTypeId") Integer carTypeId,
                        @QueryParam("locationId") Integer locationId,
                        @QueryParam("yearlyKilometersDriven") Integer yearlyKilometersDriven) {
        return new Rate(rateCommands.getRate(locationId, carTypeId, yearlyKilometersDriven));
    }
}
