package com.example.femilyship.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger; // ◀◀◀ import 추가
import org.slf4j.LoggerFactory; // ◀◀◀ import 추가

import java.io.IOException;

@Component
@RequiredArgsConstructor // Replaces @Autowired with a final constructor (better practice)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // ▼▼▼ 로거(Logger)를 추가합니다. ▼▼▼
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService; // <-- We now inject the interface, not the old class

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // ▼▼▼ 로그 추가 1: 필터가 시작되었는지 확인 ▼▼▼
        log.info("JwtAuthenticationFilter 시작: URI: {}", request.getRequestURI());

        final String jwt = getJwtFromRequest(request);

        // ▼▼▼ 로그 추가 2: 토큰을 제대로 추출했는지 확인 ▼▼▼
        log.info("요청에서 추출된 JWT: {}", jwt);


        if (jwt == null) {
            // ▼▼▼ 로그 추가 3: 토큰이 없을 경우 ▼▼▼
            log.info("토큰 없음, 다음 필터로 진행...");
            filterChain.doFilter(request, response);
            return;
        }

        // ▼▼▼ 로그 추가 4: 토큰 검증 직전 ▼▼▼
        log.info("토큰 발견! 토큰 검증 및 인증 절차 시작...");

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = tokenProvider.getUsernameFromJWT(jwt);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("인증 성공! SecurityContext에 사용자 등록: {}", username); // 인증 성공 로그

            request.setAttribute("userDetails", userDetails);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}