package br.com.uol.entities;

import br.com.uol.controller.dto.request.RegisterLocationDTO;
import br.com.uol.controller.dto.request.UpdateLocationDTO;
import br.com.uol.entities.enums.RegionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "locations")
@Entity(name = "Location")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;
    private long population;
    private String capital;

    private long area;

    public Location(RegisterLocationDTO locationDTO) {
        this.name = locationDTO.name();
        this.region = RegionEnum.valueOf(locationDTO.region().toUpperCase());
        this.population = locationDTO.population();
        this.capital = locationDTO.capital();
        this.area = locationDTO.area();
    }

    public void updateLocation(UpdateLocationDTO dados) {
        if(dados.area() != this.area) {
            this.area = dados.area();
        }
        if(dados.population()!= this.population) {
            this.population = dados.population();
        }
    }
}
