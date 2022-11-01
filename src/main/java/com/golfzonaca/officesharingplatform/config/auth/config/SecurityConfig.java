package com.golfzonaca.officesharingplatform.config.auth.config;

import com.golfzonaca.officesharingplatform.config.auth.filter.JsonIdPwAuthenticationProcessingFilter;
import com.golfzonaca.officesharingplatform.config.auth.filter.JwtAuthenticationFilter;
import com.golfzonaca.officesharingplatform.config.auth.handler.JwtSuccessHandler;
import com.golfzonaca.officesharingplatform.config.auth.provider.IdPwAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final RequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/signin", "POST");

    @Bean
    public PasswordEncoder passwordEncoder() {  // passwordEncoder라는 인터페이스를 BcryptPasswordEncoder가 implement 하기 떄문에 new 가능!
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JsonIdPwAuthenticationProcessingFilter jsonIdPwAuthenticationProcessingFilter() throws Exception {
        JsonIdPwAuthenticationProcessingFilter jsonAuthenticationFilter = new JsonIdPwAuthenticationProcessingFilter(LOGIN_REQUEST_MATCHER);
        jsonAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        jsonAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return jsonAuthenticationFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new JwtSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new IdPwAuthenticationProvider(userDetailsService,
                passwordEncoder(),
                new SimpleAuthorityMapper()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/user/mypage").hasRole("USER");
        http.addFilterAt(jsonIdPwAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter, JsonIdPwAuthenticationProcessingFilter.class);
        http.userDetailsService(userDetailsService);
    }

}
