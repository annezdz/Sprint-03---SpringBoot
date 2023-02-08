package br.com.uol.controller.dto.response;


import br.com.uol.entities.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.validation.annotation.Validated;

@JsonPropertyOrder({ "id", "nome", "regiao", "populacao", "capital", "area" })
public record DetailedLocationDTO(
        long id,
        @JsonProperty("nome") String nome,
        @JsonProperty("regiao") String region,
        @JsonProperty("populacao") double population,
        @JsonProperty("capital") String capital,
        @JsonProperty("area") @Validated double area) {

    public DetailedLocationDTO(Location location) {
        this(location.getId(), location.getName(), location.getRegion().getRegionEnum(), location.getPopulation(), location.getCapital(),
                location.getArea());
    }
}
