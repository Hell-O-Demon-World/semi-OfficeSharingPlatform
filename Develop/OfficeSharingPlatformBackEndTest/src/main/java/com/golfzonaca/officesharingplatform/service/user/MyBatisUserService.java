package com.golfzonaca.officesharingplatform.service.user;

import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBatisUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public Boolean validateUserByUserId(Long UserId) {
        return userRepository.validateUserByUserId(UserId);
    }
}
