package com.golfzonaca.backoffice.auth.handler;

import com.golfzonaca.backoffice.auth.token.JwtManager;
import com.golfzonaca.backoffice.auth.token.JwtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
@RequiredArgsConstructor
public class JwtSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtRepository jwtRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Jwt jwt = JwtManager.createJwt((String) authentication.getPrincipal());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        if (iter.hasNext()) {
            response.setStatus(HttpStatus.ACCEPTED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.addHeader("Authorization",jwt.getEncoded());
            request.setAttribute("Authorization", jwt.getEncoded());
            jwtRepository.save((String) authentication.getPrincipal(), jwt.getEncoded());
            response.sendRedirect("/places");
        }

//        JsonObject json = new JsonObject();
//        json.addProperty("userId", (String) authentication.getPrincipal());
//
//        response.setHeader("Authorization",jwt.getEncoded());
//        request.setAttribute("userId", (String) authentication.getPrincipal());


//        request.getRequestDispatcher("/place/places").forward(request,response);
//        try( PrintWriter writer = response.getWriter()) {
//            Jwt jwt = JwtManager.createJwt((String) authentication.getPrincipal());
//            JsonObject json = new JsonObject();
//
//            json.addProperty("accessToken",jwt.getEncoded());
//            json.addProperty("userId", (String) authentication.getPrincipal());
//            response.setStatus(HttpStatus.ACCEPTED.value());
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//
////            request.setAttribute("json", json);
////            response.setHeader("Authorization",jwt.getEncoded());
////            request.getRequestDispatcher("/auth/signin").forward(request,response);
//
//            writer.write(json.toString());
//        } catch (IOException e) {
//            log.error("fail to process file={}", e);
//        }
    }

}
