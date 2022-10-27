package com.golfzonaca.officesharingplatform.config.auth;

import com.golfzonaca.officesharingplatform.config.auth.repository.User2Repository;
import com.golfzonaca.officesharingplatform.config.auth.token.User2;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final User2Repository user2Repository;

    // 1. 패스워드는 알아서 체킹하니까 신경쓸필요 없다.
    // 2. 리턴이 잘 되면 자동으로 UserDetails 타입 세션으로 만든다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User2 user2 = user2Repository.findByUsername(username);
        if (user2 == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user2.getAuthority());
        grantedAuthorityList.add(simpleGrantedAuthority);

        return new PrincipalDetails(user2.getUsername(), user2.getPassword(), grantedAuthorityList);
    }
}
