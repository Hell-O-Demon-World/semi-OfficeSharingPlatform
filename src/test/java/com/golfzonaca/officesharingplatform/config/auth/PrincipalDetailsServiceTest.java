package com.golfzonaca.officesharingplatform.config.auth;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.MemoryUserRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PrincipalDetailsServiceTest {
    private UserRepository userRepository = new MemoryUserRepository();

    @Test
    void loadUserByUsername() {
        //given
        String username = "kim@hanmail.net";
        User user = new User("kim", username, "asdasd", "", "", new ArrayList<>());
        User failedUser = new User("","","","","", new ArrayList<>());
//        User user = new User("","","","","", new ArrayList<>());
        userRepository.save(user);

        if (user.equals(failedUser)) {
            throw new UsernameNotFoundException("User Not Found");
        }
        User findUser = userRepository.findByEmail("asd");
        Optional<User> opt = Optional.of(findUser);
        opt.orElseThrow(() -> new UsernameNotFoundException("Failed: No User Info"));

        if (findUser == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}