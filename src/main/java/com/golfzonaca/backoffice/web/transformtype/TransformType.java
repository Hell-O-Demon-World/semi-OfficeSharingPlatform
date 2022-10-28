package com.golfzonaca.backoffice.web.transformtype;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.web.transformtype.form.PlaceViewForm;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransformType {

    public Place listToString(PlaceViewForm placeViewForm) {
        Place place = new Place();
        place.setPlaceName(placeViewForm.getPlaceName());
        place.setPlaceDescription(placeViewForm.getPlaceDescription());
        place.setPlaceOpenDays(placeViewForm.getPlaceOpenDays().toString().replace("[", "").replace("]",""));
        place.setPlaceStart(placeViewForm.getPlaceStart());
        place.setPlaceEnd(placeViewForm.getPlaceEnd());
//        place.setPlaceAddInfo(placeViewForm.getPlaceAddInfo().toString());
//        place.setAddressId(place.getAddressId());
        return place;
    }

    public PlaceViewForm stringToList(Place place) {
        PlaceViewForm placeViewForm = new PlaceViewForm();
        placeViewForm.setId(place.getId());
        placeViewForm.setPlaceName(place.getPlaceName());
        placeViewForm.setPlaceDescription(place.getPlaceDescription());
        // daysString 배열을 daysList 로 변환하기
        placeViewForm.setPlaceOpenDays(new ArrayList<>(Arrays.asList(place.getPlaceOpenDays().split(", "))));
        placeViewForm.setPlaceStart(place.getPlaceStart());
        placeViewForm.setPlaceEnd(place.getPlaceEnd());
//        placeViewForm.setPlaceAddInfo(List.of(place.getPlaceAddInfo().split(",")));
        return placeViewForm;
    }
}
