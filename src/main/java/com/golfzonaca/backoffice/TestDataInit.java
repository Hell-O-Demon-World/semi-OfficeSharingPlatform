package com.golfzonaca.backoffice;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PlaceRepository placeRepository;

    @PostConstruct
    public void init() {
        JSONObject openDays = new JSONObject();
        openDays.put("mon", true);

        JSONObject addInfo = new JSONObject();
        addInfo.put("wifi", true);

        LocalTime openTime = LocalTime.of(11, 00, 00);
        LocalTime closeTime = LocalTime.of(23, 00, 00);


        placeRepository.save(new Place(1L, "placeA", "place A located Pangyo", openDays, openTime, closeTime, addInfo, 2L));
    }
}