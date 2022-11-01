package com.golfzonaca.officesharingplatform.service;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetails;
import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsRepository;
import com.golfzonaca.officesharingplatform.domain.Mileage;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final MileageService mileageService;
    private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final PrincipalDetailsRepository principalDetailsRepository;

    public boolean emailCheck(String email) {
        return userRepository.countContainByEmail(email) == 0;
    }

    public Boolean join(User user) {
        boolean isJoin = true;

        if (!emailCheck(user.getMail())) {
            isJoin = false;
        }

        String rawPassword = user.getPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        Mileage mileage = mileageService.join();
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.setPw(encPassword);
        user.setMileageId(mileage.getId());
        user.setAuthority("ROLE_USER");
        User userEntity = userRepository.save(user);
        authorities.add(new SimpleGrantedAuthority(user.getAuthority()));

        PrincipalDetails principalDetails = new PrincipalDetails(userEntity.getMail(), userEntity.getPw(), authorities);
        principalDetailsRepository.save(user.getId(), principalDetails);

        return isJoin;
    }

}
