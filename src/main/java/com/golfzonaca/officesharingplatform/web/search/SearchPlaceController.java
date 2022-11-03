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

        log.info("addressIds={}", addressIds);

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

        /**
         * 2-1
         * (1) Place 테이블에서 placeName 을 찾기 -> placeId, placeName, addressId, placeAddInfo
         * (2) AddressId 가 담긴 리스트 만들기
         * (2) Location 테이블에서 Where In 으로 addressId 를 찾기 -> Address, postalCode
         * (3) SearchResponseData 타입의 변수에 set(placeId, placeName, address, postalCode)
         * (4) LinkedList<SearchResponseData> 에 add
         * (5) (2)~(4)의 작업을 for문으로 '(1)의 결과가 담긴 List' 의 길이만큼 반복
         *
         *
         * 2-2
         * (1) Location 테이블에서 address 를 찾기 -> addressId, address, postalCode (select * )
         * (2) Place 테이블에서 addressId 를 찾기 -> placeId, placeName, placeAddInfo
         *  (2-1)의 후처리와 비슷함
         *
         */


        return responseDataList;
    }
}
