package com.golfzonaca.officesharingplatform.config;

import com.golfzonaca.officesharingplatform.config.auth.token.JwtAuthenticateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IoC
public class securityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticateFilter jwtAuthenticateFilter;

    @Bean
    public BCryptPasswordEncoder encode(){ // 비밀번호를 hash값으로 생성하기 위해 생성자 초기화
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super 삭제 - 기존 시큐리티가 가지고 있는 기능 비활성화
        http.csrf().disable(); //csrf token 삭제 postman에서의 request 이던 홈페이지에서 보낸 request 이던간에 구분 안함
        // Stateless (세션 사용 x)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //
        // UsernamePasswordAuthenticationFilter 에 도달하기 전에 커스텀한 필터를 먼저 동작시킴
        http.addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/user/**","/img/**","/search/**","/comment/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") //get
                .loginProcessingUrl("/auth/signin") //post -> 스프링 시큐리티가 로그인 프로세스를 진행
                .defaultSuccessUrl("/");
    }
}
