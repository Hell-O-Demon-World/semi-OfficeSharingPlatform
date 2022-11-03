package com.golfzonaca.officesharingplatform.repository.search;

import com.golfzonaca.officesharingplatform.domain.Address;
import com.golfzonaca.officesharingplatform.web.search.form.SearchPlaceResultData;

import java.util.List;

public interface SearchRepository {
    List<SearchPlaceResultData> findPlaceBySearchWord(String searchWord);

    List<Address> findByAddressIds(List addressIds);
}
