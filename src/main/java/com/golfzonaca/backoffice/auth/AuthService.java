package com.golfzonaca.backoffice.auth;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.repository.CompanyRepository;
import com.golfzonaca.backoffice.repository.mybatis.MyBatisCompanyRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final MyBatisCompanyRepository myBatisCompanyRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        myBatisCompanyRepository.findByCompanyLoginId(username);
        Company company = myBatisCompanyRepository.findByCompanyLoginId(username).get();
        log.info("company={}",company);
        if (company == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_REGISTER"));

        return CompanyPrincipalDetails.builder()
                .username(company.getCompanyLoginId())
                .password(company.getCompanyPw())
                .authorities(grantedAuthorityList)
                .build();
    }
}
