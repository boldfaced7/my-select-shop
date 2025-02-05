package com.sparta.myselectshop.user.adapter.in.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.myselectshop.user.domain.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

    private final JwtProperties jwtProperties;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtProperties jwtProperties, JwtUtils jwtUtils) {
        this.jwtProperties = jwtProperties;
        this.jwtUtils = jwtUtils;
        setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {

        var loginRequest = readLoginRequest(request);
        var token = new UsernamePasswordAuthenticationToken(
                loginRequest.username(),
                loginRequest.password(),
                null
        );
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        log.info("successfulAuthentication : {}", authResult);
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
        registerAccessToken(response, username, role);
        // registerRefreshToken(request, response, username, role);
    }

    private void registerAccessToken(
            HttpServletResponse response,
            String username,
            UserRole role
    ) {
        var generated = jwtUtils.generateAccessToken(username, role);
        response.addHeader(JwtUtils.AUTHORIZATION_HEADER, generated);
    }

    private void registerRefreshToken(
            HttpServletRequest request,
            HttpServletResponse response,
            String username,
            UserRole role
    ) {
        var maxAge = (int) jwtProperties.getRefreshExpiration();
        var generated = jwtUtils.generateRefreshToken(username, role);
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, generated, maxAge);
    }


    private LoginRequest readLoginRequest(HttpServletRequest request) {
        try {
            return new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public record LoginRequest(
            String username,
            String password
    ) {}
}
