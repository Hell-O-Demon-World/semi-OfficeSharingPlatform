package com.golfzonaca.officesharingplatform.service.auth;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetails;
import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsRepository;
import com.golfzonaca.officesharingplatform.domain.Mileage;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import com.golfzonaca.officesharingplatform.service.mileage.MileageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryAuthService implements AuthService{
    private final UserRepository userRepository;
    private final MileageService mileageService;
    private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final PrincipalDetailsRepository principalDetailsRepository;
    @Override
    public boolean emailCheck(String email) {
        return userRepository.countContainByEmail(email) == 0;
    }

    @Override
    public Boolean join(User user) {
        boolean isJoin = true;

        if (!emailCheck(user.getUserMail())) {
            return false;
        }

        String rawPassword = user.getUserPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        Mileage mileage = mileageService.join();
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.setUserPw(encPassword);
        user.setMileageId(mileage.getId());
        User userEntity = userRepository.save(user);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        PrincipalDetails principalDetails = new PrincipalDetails(userEntity.getUserMail(), userEntity.getUserPw(), authorities);
        principalDetailsRepository.save(user.getId(), principalDetails);

        return isJoin;
    }

}
