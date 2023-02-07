package br.com.uol.controller;

import br.com.uol.controller.dto.request.RegisterLocationDTO;
import br.com.uol.controller.dto.request.UpdateLocationDTO;
import br.com.uol.controller.dto.response.DetailedLocationDTO;
import br.com.uol.entities.Location;
import br.com.uol.entities.enums.RegionEnum;
import br.com.uol.repository.LocationRepository;
import br.com.uol.utils.LocationUtils;
import br.com.uol.utils.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/states")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid RegisterLocationDTO locationDTO,
                                      UriComponentsBuilder uriComponentsBuilder) {
        try {
            var location = new Location(locationDTO);
            String name = location.getName().toLowerCase();
            String region = location.getRegion().name();
            String capital = location.getCapital();
            TreeMap<String, String> getRegionsAndCapitals = LocationUtils.getStatesByRegion(region);
            if (getRegionsAndCapitals.containsKey(name) && getRegionsAndCapitals.get(name).equalsIgnoreCase(capital)) {
                repository.save(location);
                var uri = uriComponentsBuilder.path("/api/states/{id}").buildAndExpand(location.getId()).toUri();
                return ResponseEntity.created(uri).body(new DetailedLocationDTO(location));
            } else {
                throw new ResourceNotFoundException("Registro inválido. Verifique se os campos "
                        + location.getName() + ", " + location.getCapital() + " e " + location.getRegion()
                        + " estão corretos.");
            }
        } catch (final DataIntegrityViolationException exception) {
            throw new ResourceNotFoundException(locationDTO.name() + " duplicado.");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedLocationDTO> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(element -> ResponseEntity.ok(new DetailedLocationDTO(element)))
                .orElseThrow(() -> new ResourceNotFoundException("Registro " + id + " não encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<DetailedLocationDTO>> getAll(@RequestParam (required = false) String region) {
        List<DetailedLocationDTO> locationList;
        Sort sort = Sort.by("region").descending().and(Sort.by("area").descending());
        if(region != null) {
            locationList =  repository.findAllByRegion(RegionEnum.valueOf(String.valueOf(region.toUpperCase())), sort)
                    .stream()
                    .map(DetailedLocationDTO::new)
                    .collect(Collectors.toList());
        } else {
            locationList = repository.findAll(sort)
                    .stream()
                    .map(DetailedLocationDTO::new)
                    .collect(Collectors.toList());
        }
        if(locationList.isEmpty()) {
            throw new ResourceNotFoundException("Registros não encontrados");
        }
        return ResponseEntity.ok(locationList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailedLocationDTO> updateLocation(@RequestBody @Valid UpdateLocationDTO dados,
                                                              @PathVariable Long id) {
        return repository.findById(id).map(item -> {
                    item.updateLocation(dados);
                    return ResponseEntity.ok(new DetailedLocationDTO(item));
                }).orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar. Registro " + id +
                " não localizado."));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return repository.findById(id).map(item -> {
            repository.deleteById(item.getId());
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar. Registro " + id +
                " não localizado."));
    }
}
