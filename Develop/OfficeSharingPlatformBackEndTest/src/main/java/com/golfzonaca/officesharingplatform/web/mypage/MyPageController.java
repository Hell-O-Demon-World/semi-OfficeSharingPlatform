package com.golfzonaca.officesharingplatform.web.mypage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtManager;
import com.golfzonaca.officesharingplatform.domain.MyPage;
import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.repository.reservation.ReservationRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import com.golfzonaca.officesharingplatform.service.mypage.MyPageReservationFormService;
import com.golfzonaca.officesharingplatform.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("mypage")
public class MyPageController {
    private final MyPageReservationFormService myPageReservationFormService;
    private final UserRepository userRepository;
    private final MyPageService myPageService;

    @GetMapping
    public MyPage myPageForm(@RequestParam("accessToken") String accessToken) throws JsonProcessingException {
        return MyPage.builder()
                .userName(userRepository.findById(JwtManager.getIdByToken(accessToken)).getUserName())
                .myPageReservationList(myPageReservationFormService.getMyPageReservationListByUserId(JwtManager.getIdByToken(accessToken)))
                .build();
    }

    @PostMapping("/cancel")
    public void cancelReservation(@RequestParam String accessToken, @RequestParam Integer order) throws JsonProcessingException {
        myPageService.cancelByOrderAndUserId(order, JwtManager.getIdByToken(accessToken));
    }
}
