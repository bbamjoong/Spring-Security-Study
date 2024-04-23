package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) -> auth
                        // permitAll : 로그인을 하지 않아도 해당 경로 접근 가능
                        .requestMatchers("/", "/login").permitAll()
                        // hasRole : 로그인 후 해당 Role이 있어야 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // hasAnyRole : 로그인 후 여러 Role중 하나라도 있어야 접근 가능
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        // anyRequest : 다른 모든 요청, authenticated() : 로그인을 하면 모두 접근 가능
                        .anyRequest().authenticated()
                // DenyAll : 로그인을 해도 모든 사용자의 접근을 막음
        );

        http
                // 로그인 uri 경로 입력
                .formLogin((auth) -> auth.loginPage("/login")
                        // 로그인 form에서 입력한 정보를 넘겨줄 uri 경로
                        // permitAll : 로그인을 하지 않아도 해당 경로 접근 가능
                        .loginProcessingUrl("/loginProc").permitAll()
                );

        // csrf : 위변조 방지 기능. 토큰을 주고 받으며 인증
        // 개발 단계에서는 해당 옵션을 켜두면 로그인이 진행되지 않으니 일단 disable
        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }
}