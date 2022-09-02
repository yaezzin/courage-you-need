package com.app.zero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록 됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* 비밀번호 암호화 처리 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin();

        http.authorizeRequests()
                //.antMatchers("/user/**").authenticated() // 로그인 해야 권한 o
                //.antMatchers("/admin/**").access("hasRole("ADMIN")") // 로그인 + admin 권한까지 있어야 함
                .anyRequest().permitAll();
    }
}
