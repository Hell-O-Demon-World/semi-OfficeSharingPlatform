package com.golfzonaca.officesharingplatform.web.mypage;

import com.golfzonaca.officesharingplatform.domain.MyPage;
import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.repository.reservation.ReservationRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import com.golfzonaca.officesharingplatform.service.mypage.MyPageReservationFormService;
import com.golfzonaca.officesharingplatform.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("mypage/")
public class MyPageController {
    private final MyPageReservationFormService myPageReservationFormService;
    private final UserRepository userRepository;
    private final MyPageService myPageService;

    @GetMapping("{userId}")
    public MyPage myPageForm(@Valid @PathVariable Long userId) {
        return MyPage.builder()
                .userName(userRepository.findById(userId).getUserName())
                .myPageReservationList(myPageReservationFormService.getMyPageReservationListByUserId(userId))
                .build();
    }

    @PostMapping("{userId}/cancel")
    public void cancelReservation(@PathVariable Long userId, @RequestParam Integer order) {
        myPageService.cancelByOrderAndUserId(order, userId);
    }
}
