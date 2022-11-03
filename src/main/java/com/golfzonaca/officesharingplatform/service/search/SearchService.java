package com.golfzonaca.officesharingplatform.service.search;

import com.golfzonaca.officesharingplatform.domain.Address;
import com.golfzonaca.officesharingplatform.web.search.form.SearchPlaceResultData;

import java.util.List;

public interface SearchService {
    List<SearchPlaceResultData> findPlaceBySearchWord(String searchWord);

    List<Address> findByAddressIds(List addressIds);


    /**
     * 2-1
     * (1) Place 테이블에서 placeName 을 찾기 -> placeId, placeName, addressId, placeAddInfo
     * (2) Location 테이블에서 addressId 를 찾기 -> addressId, Address, postalCode
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
}
