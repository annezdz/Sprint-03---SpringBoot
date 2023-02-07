package br.com.uol.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegisterLocationDTO(
        @NotBlank(message = "Campo nome obrigatório")
        @Size(min = 4)
        @JsonProperty("nome")
        String name,

        @NotNull(message = "Campo região obrigatório")
        @JsonProperty("regiao")
        String region,

        @Positive
        @JsonProperty("populacao")
        @NotNull(message = "Campo população obrigatório")
        long population,

        @NotNull(message = "Campo capital obrigatório")
        @JsonProperty("capital")
        String capital,
        @Positive
        @NotNull(message = "Campo area obrigatório")
        @JsonProperty("area")
        long area) {
}
