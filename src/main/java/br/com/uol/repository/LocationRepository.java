package br.com.uol.repository;

import br.com.uol.entities.Location;
import br.com.uol.entities.enums.RegionEnum;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByRegion(RegionEnum region, Sort sort);
}
