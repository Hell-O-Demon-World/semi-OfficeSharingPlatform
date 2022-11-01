package com.golfzonaca.officesharingplatform.repository;

import com.golfzonaca.officesharingplatform.domain.Place;

public interface PlaceRepository {

    Place save(Place place);
    Place findById(long placeId);
}