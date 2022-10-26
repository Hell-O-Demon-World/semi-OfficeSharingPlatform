package com.golfzonaca.backoffice.service;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBatisPlaceService implements PlaceService{

    private final PlaceRepository placeRepository;

    @Override
    public Place save(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public void update(Long id, PlaceUpdateDto updateParam) {
        placeRepository.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        placeRepository.delete(id);
    }

    @Override
    public Optional<Place> findById(Long Id) {
        return placeRepository.findById(Id);
    }

    @Override
    public Optional<List<Place>> findAll(Long companyId) {
        return placeRepository.findAll(companyId);
    }
}