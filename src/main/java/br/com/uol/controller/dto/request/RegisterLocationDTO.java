package br.com.uol.controller.dto.request;

import br.com.uol.entities.enums.RegionEnum;
import br.com.uol.utils.validators.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

public record RegisterLocationDTO(
        @NotBlank(message = "Campo nome obrigatório")
        @Size(min = 4)
        @Validated
        @JsonProperty("nome")
        String name,

        @NotNull(message = "Campo região obrigatório")
        @JsonProperty("regiao")
        @ValueOfEnum(enumClass = RegionEnum.class)
        String region,

        @Positive
        @JsonProperty("populacao")
        @NotNull(message = "Campo população obrigatório")
        @Validated
        double population,

        @NotNull(message = "Campo capital obrigatório")
        @JsonProperty("capital")
        @Validated
        String capital,
        @Positive
        @Validated
        @NotNull(message = "Campo area obrigatório")
        @JsonProperty("area")
        double area) {

}
