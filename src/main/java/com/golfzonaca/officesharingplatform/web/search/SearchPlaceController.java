package com.golfzonaca.officesharingplatform.web.search;

import com.golfzonaca.officesharingplatform.domain.Address;
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

    @PostMapping("/main/search") // 요청 데이터를 json 으로 받을 예정
    public List<SearchResponseData> findRoomByWord(@RequestBody SearchRequestData searchRequestData) throws Exception {
        List<SearchResponseData> responseDataList = new LinkedList<>();
        List<Long> addressIds = new ArrayList<>();
        List<SearchPlaceResultData> placeSearchList = searchService.findPlaceBySearchWord(searchRequestData.getSearchWord());

        for (SearchPlaceResultData resultData : placeSearchList) {
            addressIds.add(resultData.getAddressId());
        }
        
        List<Address> addressSearchList = searchService.findByAddressIds(addressIds); // 공간의 주소 찾기

        for (SearchPlaceResultData resultData : placeSearchList) { // 응답데이터리스트에 값 추가
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
