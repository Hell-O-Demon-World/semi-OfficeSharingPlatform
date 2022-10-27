package com.golfzonaca.officesharingplatform.config;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsService;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtAuthenticateFilter;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtTokenProvider;
import com.golfzonaca.officesharingplatform.config.auth.token.MyCustomFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//@RequiredArgsConstructor
//@Configuration
@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() { // 비밀번호를 hash값으로 생성하기 위해 생성자 초기화
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        http.csrf().disable();
        http.addFilter(usernamePasswordAuthenticationFilter);
//        super 삭제 - 기존 시큐리티가 가지고 있는 기능 비활성화
//        http
//                .httpBasic().disable()
//                .formLogin().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/auth/signup").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterAt(myCustomFilter, UsernamePasswordAuthenticationFilter.class)
//                .userDetailsService(userDetailsService);
    }
    @Override
    public void configure(WebSecurity web){
        web.debug(true);
    }

}
