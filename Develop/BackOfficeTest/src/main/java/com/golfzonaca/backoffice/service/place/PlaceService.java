package com.golfzonaca.backoffice.service.place;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;

import java.util.List;
import java.util.Optional;


public interface PlaceService {

    Place save(Place place);

    void update(Long id, PlaceUpdateDto updateParam);

    void delete(Long id);

    Optional<Place> findById(Long Id);

    List<Place> findAll(Long companyId);
}
