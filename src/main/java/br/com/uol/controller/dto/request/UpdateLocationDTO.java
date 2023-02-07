package br.com.uol.controller.dto.request;

import br.com.uol.entities.Location;
import jakarta.validation.constraints.Positive;

public record UpdateLocationDTO(

        @Positive
        long population,
        @Positive
        long area){

    public UpdateLocationDTO(Location location) {
        this(location.getPopulation(), location.getArea());
    }
}
