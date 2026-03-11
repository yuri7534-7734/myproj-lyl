package com.study.Ex16Security02;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

//시큐리티 관련 설정 클래스
@Configuration //환경설정 클래스로 등록한다.
@EnableWebSecurity //웹 보안 활성화 어노테이션
public class SecurityConfig {
    @Bean //메소드 반환객체를 빈으로 등록한다.
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //csrf 보안 설정을 비활성화(개발편의시)
                //csrf( (auth)-> auth.disable() )
                //활성화(기본)
                //CSRF 보안 방식 2가지
                // 1. HttpSession 방식(기본) : 서버에 인증정보를 저장
                // 2. Cookie Token 방식 : 자바스크립트 기반 앱 제작시 쿠키에 CsrfToken 저장해야된다.
                .csrf((auth)->auth
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )

                //HTTP 요청에 대한 보안을 설정한다. Security 6버전.
                .authorizeHttpRequests( (auth) -> auth
                    //permitAll() : 모두에게 허용
                    //authenticated() : 인증된 사용자에게 허용
                    //hasRole("ADMIN") : ADMIN 권한을 가진 사용자에게 허용
                    .requestMatchers("/loginForm").permitAll()
                    //.requestMatchers("/").authenticated()
                    .requestMatchers("/").permitAll()
                    // .hasRole("ADMIN") => "ROLE_ADMIN" 문구가 DB에 기록됨.
                    //.requestMatchers("/admin").hasRole("ADMIN")
                    // 인증된 사용자에게만 허용한다.
                    .anyRequest().authenticated()
            )
                .formLogin((formLogin)->formLogin
                        .loginPage("/loginForm") //로그인폼 요청
                        //loginAction에 대한 인증처리는 시큐리티가 다 한다. 코드 필요없다.
                        .loginProcessingUrl("/loginAction") //로그인 액션 요청 URL
                        .defaultSuccessUrl("/") //로그인 성공 시 리다이렉트 URL
                        //로그인 성공 커스텀 핸들러
                        .successHandler( (request,response,auth)->{
                            System.out.println("로그인 성공했습니다.");
                            response.sendRedirect("/");
                        })
                        //로그인 실패 에러페이지 커스텀
                        .failureUrl("/loginForm?error")
                        .permitAll() //모두에게 허용한다.
                )
                .logout((auth)->auth
                        .logoutRequestMatcher(
                                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,"/logoutAction")
                        )
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) //JSESSIONID 정보(쿠키)제거해야함
                        .deleteCookies("JSESSIONID") //쿠키 삭제
            );
        return http.build();
    }
}
