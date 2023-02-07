package br.com.uol.controller.dto.response;


import br.com.uol.entities.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nome", "regiao", "populacao", "capital", "area" })
public record DetailedLocationDTO(
        Long id,
        @JsonProperty("nome") String nome,
        @JsonProperty("regiao") String region,
        @JsonProperty("populacao") long population,
        @JsonProperty("capital") String capital,
        @JsonProperty("area") long area) {

    public DetailedLocationDTO(Location location) {
        this(location.getId(), location.getName(), location.getRegion().getRegionEnum(), location.getPopulation(), location.getCapital(),
                location.getArea());
    }
}
