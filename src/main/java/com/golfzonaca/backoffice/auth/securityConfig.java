package com.golfzonaca.backoffice.auth;

import com.golfzonaca.backoffice.auth.filter.JsonIdPwAuthenticationProcessingFilter;
import com.golfzonaca.backoffice.auth.filter.JwtAuthenticationFilter;
import com.golfzonaca.backoffice.auth.handler.JwtSuccessHandler;
import com.golfzonaca.backoffice.auth.provider.IdPwAuthenticationProvider;
import com.golfzonaca.backoffice.repository.mybatis.CompanyMapper;
import com.golfzonaca.backoffice.repository.mybatis.MyBatisCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private static final RequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/signin", "POST");

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthService(new MyBatisCompanyRepository(new CompanyMapper));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(userDetailsService);
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return new NullAuthoritiesMapper();
    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new JwtSuccessHandler();
    }
    @Bean IdPwAuthenticationProvider idPwAuthenticationProvider() {
        return new IdPwAuthenticationProvider(userDetailsService, new BCryptPasswordEncoder(),grantedAuthoritiesMapper());
    }
    @Bean
    public JsonIdPwAuthenticationProcessingFilter jsonIdPwAuthenticationProcessingFilter() throws Exception {
        JsonIdPwAuthenticationProcessingFilter jsonAuthenticationFilter = new JsonIdPwAuthenticationProcessingFilter(LOGIN_REQUEST_MATCHER);
        jsonAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        jsonAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return jsonAuthenticationFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin()
                .loginPage("/signin").permitAll()
                .defaultSuccessUrl("/places")
                .loginProcessingUrl("/signin")
                .and()
                .authenticationProvider(idPwAuthenticationProvider());
        http.authorizeRequests()
                .antMatchers("/places", "/places/**").permitAll()
                .antMatchers("/").permitAll();
//        http.addFilterAt(jsonIdPwAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(jwtAuthenticationFilter(), JsonIdPwAuthenticationProcessingFilter.class);
//        http.userDetailsService(userDetailsService);
    }
}
