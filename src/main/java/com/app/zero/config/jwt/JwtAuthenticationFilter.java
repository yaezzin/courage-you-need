package com.app.zero.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    /*
    * JwtAuthenticationFilter는 jwt가 유효한 토큰인지 검증 함
    * 요청으로 들어오는 jwt token의 유효성을 검증하는 필터를 필터 체인에 등록
    * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. request의 헤더에서 jwt를 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 2. 유효한 토큰인지 확인
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 토큰이 유효하면 토큰으로부터 유저 정보를 가져옴
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            // 4. SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }
}
