package de.vontrostorff.application.controllers;

import de.vontrostorff.domain.model.Location;
import de.vontrostorff.domain.ports.incoming.LocationCommands;
import de.vontrostorff.domain.service.LocationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/place")
@RequiredArgsConstructor
public class LocationResource {
    private final LocationCommands locationCommands;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Location> getPlaces(@QueryParam("plz") Integer plz) {
        if (plz == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("plz parameter is mandatory")
                            .build()
            );
        }
        return locationCommands.searchByPLZ(plz);
    }
}
