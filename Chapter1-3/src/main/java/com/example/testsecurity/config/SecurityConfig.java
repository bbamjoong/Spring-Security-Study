package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // BCrypt Password Encoder 객체를 생성하여 반환하는 Bean 생성
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) -> auth
                        // permitAll : 로그인을 하지 않아도 해당 경로 접근 가능
                        .requestMatchers("/",
                                "/login", "/loginProc",
                                "/join", "/joinProc").permitAll()
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

        // 다중 로그인을 방지하기 위한 설정
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 최대 세션은 1개로 하겠음.
                        // true : 최대 세션을 초과하면 새로운 로그인 차단
                        // false : 최대 세션을 초과하면 기존 세션 하나 삭제
                        .maxSessionsPreventsLogin(true)
                );

        // 세션 고정 공격 보호
        // none() : 로그인 시 세션 정보 변경 안함 -> 세션 고정 공격을 당할 수 있음.

        // newSession() : 로그인 시 세션 새로 생성
        // changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId()
                );

        return http.build();
    }
}