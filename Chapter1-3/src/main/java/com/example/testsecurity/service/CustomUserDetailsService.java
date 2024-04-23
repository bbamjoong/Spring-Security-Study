package com.example.testsecurity.service;

import com.example.testsecurity.dto.CustomUserDetails;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    // 로그인 시 스프링 시큐리티 내부적으로 `UsernamePasswordAuthenticationFilter`가 동작되고
    // `AuthenticationProvider`에 의해 `CustomUserDetailsService`의 `loadUserByUsername`을 호출함.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("로그인 시 호출이 잘 됩니다."); // SQL문이 실행되기 전에 출력됨.
        Optional<UserEntity> userData = userRepository.findByUsername(username);

        return userData.map(userEntity -> new CustomUserDetails(userEntity)).orElse(null);

    }
}
