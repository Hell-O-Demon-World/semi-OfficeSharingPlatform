package com.golfzonaca.backoffice.auth.filter;

import com.golfzonaca.backoffice.auth.token.IdPwAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JsonIdPwAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public JsonIdPwAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        }
        log.info("id={}", request.getParameter("id"));
        log.info("pw={}", request.getParameter("pw"));
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        IdPwAuthenticationToken idPwAuthenticationToken = new IdPwAuthenticationToken(id, pw);
        log.info("idPwAuthenticationToken={}", idPwAuthenticationToken);
        Object o = super.authenticationDetailsSource.buildDetails(request);
        log.info("o={}",o);
        idPwAuthenticationToken.setDetails(super.authenticationDetailsSource.buildDetails(request));
        Authentication authenticate = super.getAuthenticationManager().authenticate(idPwAuthenticationToken);
        log.info("authenticate={}",authenticate);
        return super.getAuthenticationManager().authenticate(idPwAuthenticationToken);
    }
}
