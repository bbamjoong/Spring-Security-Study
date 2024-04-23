package com.example.testsecurity.service;

import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    public String getSessionUsername() {
        // SecurityContextHolder에서 context를 얻고
        // authentication(권한)을 얻은 후 이름을 얻는다.
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getSessionRole() {
        // SecurityContextHolder에서 context를 얻고 authentication(권한)을 얻는다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authorities(권한들)을 Collection에 담는다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        // 이 예제의 경우 권한이 하나이기 때문에 next()를 통해 얻은 authority를 반환해주면 된다.
        GrantedAuthority auth = iter.next();

        return auth.getAuthority();
    }
}
