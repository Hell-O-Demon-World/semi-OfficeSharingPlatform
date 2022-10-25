package com.golfzonaca.officesharingplatform.web.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SearchRoomController {

    @PostMapping("/search/test") // 요청 데이터를 json 으로 받을 예정
    public ResultData findRoomByWord(@RequestBody RequestData requestData) throws Exception{
        System.out.println("requestData = " + requestData);
        log.info("requestData = {}", requestData);
        log.info("Keyword = {}, Desk = {}, Conference = {}, Office = {}", requestData.getKeyword(), requestData.getDesk(), requestData.getConference(), requestData.getOffice());

        /*
        * 1. requestData에서 get해서 검색 조건 뽑아내기
        * 2. 뽑은 검색 조건 데이터로 JPA를 활용하여 검색
        * query : select * from PLACE
         where PLACE.ADDRESS_ID IN (
             SELECT Address.ID FROM Address
                               WHERE Address.ADDRESS like '%판교%'
             );
        * 3. 결과를 json 형태의 resultData로 저장
        * 4. return resultData;
        * */

        ResultData resultData = new ResultData();
        resultData.setPlaceName("PlaceA");
        resultData.setPlaceAddress("서울특별시 강남구");
        resultData.setAlwaysOpen(true);
        resultData.setWifi(true);
        resultData.setManageDirect(true);

        return resultData;
    }
}
