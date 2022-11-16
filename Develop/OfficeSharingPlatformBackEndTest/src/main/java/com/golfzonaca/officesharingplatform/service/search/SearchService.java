package com.golfzonaca.officesharingplatform.service.search;

import com.golfzonaca.officesharingplatform.web.search.form.SearchPlaceResultData;

import java.util.List;

public interface SearchService {
    List<SearchPlaceResultData> findPlaceBySearchWord(String searchWord);
}
