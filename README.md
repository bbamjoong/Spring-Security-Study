# Spring-Security-Study

## Chapter8
- [DB기반 로그인 검증 로직](https://www.youtube.com/watch?v=U3Jkuy5Hc00&list=PLJkjrxxiBSFCKD9TRKDYn7IE96K2u3C3U&index=11&ab_channel=%EA%B0%9C%EB%B0%9C%EC%9E%90%EC%9C%A0%EB%AF%B8)<br>
- [노션](https://substantial-park-a17.notion.site/8-DB-147df9c034ba495cad1c62869408be8f)

### SpringSecurity의 동작과정
로그인 시 스프링 시큐리티 내부적으로 `UsernamePasswordAuthenticationFilter`가 동작되고<br>
`AuthenticationProvider`에 의해 `CustomUserDetailsService`의 `loadUserByUsername`을 호출함.
    
![](https://velog.velcdn.com/images/platinouss/post/d86607de-2fc8-4cfa-94d2-6fff3fb40c52/image.png)
