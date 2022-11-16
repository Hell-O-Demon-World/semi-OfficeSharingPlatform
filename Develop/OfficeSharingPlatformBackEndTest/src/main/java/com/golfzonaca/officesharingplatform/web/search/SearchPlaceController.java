package com.golfzonaca.officesharingplatform.web.search;

import com.golfzonaca.officesharingplatform.domain.Address;
import com.golfzonaca.officesharingplatform.service.address.AddressService;
import com.golfzonaca.officesharingplatform.service.search.SearchService;
import com.golfzonaca.officesharingplatform.web.search.form.SearchPlaceResultData;
import com.golfzonaca.officesharingplatform.web.search.form.SearchRequestData;
import com.golfzonaca.officesharingplatform.web.search.form.SearchResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchPlaceController {

    private final SearchService searchService;
    private final AddressService addressService;

    @PostMapping("/main/search")
    public List<SearchResponseData> findRoomByWord(@RequestBody SearchRequestData searchRequestData) {
        List<SearchResponseData> responseDataList = new LinkedList<>();
        List<Long> addressIds = new ArrayList<>();
        List<SearchPlaceResultData> placeSearchList = searchService.findPlaceBySearchWord(searchRequestData.getSearchWord());

        for (SearchPlaceResultData resultData : placeSearchList) {
            addressIds.add(resultData.getAddressId());
        }

        List<Address> addressSearchList = addressService.findByAddressIds(addressIds);

        for (SearchPlaceResultData resultData : placeSearchList) {
            for (Address address : addressSearchList) {
                if (resultData.getAddressId() == address.getId()) {
                    SearchResponseData responseData = new SearchResponseData();
                    responseData.setKey(resultData.getId());
                    responseData.setName(resultData.getPlaceName());
                    responseData.setOption(resultData.getPlaceAddinfo());
                    responseData.setAddress(address.getAddress());
                    responseData.setPostcode(address.getPostalCode());
                    responseDataList.add(responseData);
                }
            }
        }
        return responseDataList;
    }
}
