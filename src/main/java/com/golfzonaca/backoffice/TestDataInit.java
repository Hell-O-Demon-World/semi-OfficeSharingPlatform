package com.golfzonaca.backoffice;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PlaceRepository placeRepository;

    @PostConstruct
    public void init() {
        placeRepository.save(new Place(1, "placeA", "place A located Pangyo", ));
        placeRepository.save(new Place());
    }
}