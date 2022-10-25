package com.golfzonaca.officesharingplatform.service;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User join(User user) {
        String rawPassword = user.getPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPw(encPassword);
        User userEntity = userRepository.save(user);

        return userEntity;
    }

}
