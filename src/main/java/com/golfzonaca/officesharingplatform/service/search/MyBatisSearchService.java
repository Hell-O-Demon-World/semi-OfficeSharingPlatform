package com.golfzonaca.officesharingplatform.service.search;

import com.golfzonaca.officesharingplatform.domain.Address;
import com.golfzonaca.officesharingplatform.repository.search.SearchRepository;
import com.golfzonaca.officesharingplatform.web.search.form.SearchPlaceResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisSearchService implements SearchService {

    private final SearchRepository searchRepository;

    @Override
    public List<SearchPlaceResultData> findPlaceBySearchWord(String searchWord) {
        return searchRepository.findPlaceBySearchWord(searchWord);
    }

    @Override
    public List<Address> findByAddressIds(List addressIds) {
        return searchRepository.findByAddressIds(addressIds);
    }
}
