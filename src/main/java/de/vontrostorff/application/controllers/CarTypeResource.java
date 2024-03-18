package de.vontrostorff.application.controllers;

import de.vontrostorff.domain.model.CarType;
import de.vontrostorff.domain.ports.incoming.CarTypeCommands;
import de.vontrostorff.domain.service.CarTypeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/car-type")
@RequiredArgsConstructor
public class CarTypeResource {
    private final CarTypeCommands carTypeCommands;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarType> getCarTypes() {
        return carTypeCommands.getAllCarTypes();
    }
}
