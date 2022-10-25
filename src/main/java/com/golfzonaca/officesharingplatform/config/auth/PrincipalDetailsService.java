package com.golfzonaca.officesharingplatform.config.auth;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

//    @RequiredArgsConstructor 사용
//    public PrincipalDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    // 1. 패스워드는 알아서 체킹하니까 신경쓸필요 없다.
    // 2. 리턴이 잘 되면 자동으로 UserDetails 타입 세션으로 만든다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(username);
        if (userEntity == null){
//            System.out.println("login error 발생");
            return null;
        }
        else {
            return new PrincipalDetails(userEntity);
        }
    }
}
