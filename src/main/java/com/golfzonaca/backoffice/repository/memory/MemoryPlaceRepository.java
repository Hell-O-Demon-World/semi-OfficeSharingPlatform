package com.golfzonaca.backoffice.repository.memory;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryPlaceRepository implements PlaceRepository {

    private static final Map<Long, Place> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Place save(Place place) {
        place.setId(++sequence);
        store.put(place.getId(), place);
        return place;
    }

    @Override
    public void update(Long id, PlaceUpdateDto updateParam) {
        Place findPlace = findById(id).orElseThrow();
        findPlace.setName(updateParam.getPlaceName());
        findPlace.setDescription(updateParam.getPlaceDescription());
        findPlace.setPlaceOpen(updateParam.getBusinessDays());
        findPlace.setPlaceStartTime(updateParam.getPlaceOpenTime());
        findPlace.setPlaceEndTime(updateParam.getPlaceCloseTime());
        findPlace.setPlaceAddInfo(updateParam.getAddInformation());
        findPlace.setAddressId(updateParam.getAddressId());
    }

    @Override
    public void delete(Long id) {
        delete(id);
    }

    @Override
    public Optional<Place> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<List<Place>> findAll(Long companyId) {
        return Optional.ofNullable(
                store.values().stream()
                .filter(place -> {
                    if (ObjectUtils.isEmpty(companyId)) {
                        return true;
                    }
                    return place.getCompanyId().equals(companyId);
                })
                .collect(Collectors.toList()));
    }

    public void clearStore() {
        store.clear();
    }
}
