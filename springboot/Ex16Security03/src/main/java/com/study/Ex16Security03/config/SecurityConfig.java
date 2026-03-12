package com.study.Ex16Security03.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

//시큐리티 관련 설정 클래스
@Configuration //환경설정 클래스로 등록한다.
@EnableWebSecurity //웹 보안 활성화 어노테이션
public class SecurityConfig {
    @Bean //메소드 반환객체를 빈으로 등록한다.
    //                            //보안설정 쌓아가는 도구
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //csrf 보안 설정을 비활성화(개발편의시)
                //csrf( (auth)-> auth.disable() )
                //활성화(기본)
                //CSRF 보안 방식 2가지
                // 1. HttpSession 방식(기본) : 서버에 인증정보를 저장
                // 2. Cookie Token 방식 : 자바스크립트 기반 앱 제작시 쿠키에 CsrfToken 저장해야된다.

                //CSRF 설정 : 람다매개변수 타입은 생략 가능함. 타입 추정으로
                //CsrfCon
                .csrf((csrf)-> csrf //설정을 기본값 그대로 사용
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        //어디에 저장하고 어떻게 꺼낼지 정하는 부분 //CSRF 토큰을 쿠키에 저장하겠다.
                        //HttpOnly를 false로 둬서 자바스크립트에서도 읽을 수 있게 한다.
                )
                //경로별 접근 권한 설정(인가)
                //authz : Authorization(인가), authn : Authentication(인증)
                // AuthorizationManagerRequestMatcherRegistry 타입이다.
                //HTTP 요청에 대한 보안을 설정한다. Security 6버전.
                .authorizeHttpRequests( (authz) -> authz
                    //permitAll() : 모두에게 허용
                    //authenticated() : 인증된 사용자에게 허용
                    //hasRole("ADMIN") : ADMIN 권한을 가진 사용자에게 허용
                    .requestMatchers("/loginForm").permitAll()
                    //.requestMatchers("/").authenticated()
                    .requestMatchers("/admin").hasRole("ADMIN") //403 Forbidden
                        .requestMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                    .requestMatchers("/").permitAll()
                    // .hasRole("ADMIN") => "ROLE_ADMIN" 문구가 DB에 기록됨.
                    //
                    // 인증된 사용자에게만 허용한다.
                    .anyRequest().authenticated()
            )

                    //로그인 페이지/액션 설정
                    //(FormLoginConfigurer<HttpSecurity> login) 타입이다.
                    .formLogin((login)-> login
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
                //로그아웃 URL/세션 설정
                .logout((logout)->logout
                        .logoutRequestMatcher( //스프링부트 4.x 업데이트 된 클래스함수
                                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,"/logoutAction")
                        )
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) //JSESSIONID 정보(쿠키)제거해야함
                        .deleteCookies("JSESSIONID") //쿠키 삭제
            );
        return http.build(); //완성된 보안 체인을 반환한다.
    }
    //여러 곳에서 같은 암호화 방식을 공유하기 위해
    //시큐리티 기본암호화 객체
    //BCrypt 암호화 엔코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        //스프링 시큐리티 6.4.x에서 공식 지원하는 PasswordEncoder 구현 클래스들
        //Bcrypt,Argon2,Pbkdf2,SCrypt
        //암호화 강도는 4 ~ 31까지 지정 가능. (몇번 섞는가?)
        return new BCryptPasswordEncoder(10);
        //return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        //return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        //return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        //Argon2Password4jPasswordEncoder : 외부 라이브러리를 이용한다. 공식이 아니므로 비추천. 시큐리티 7에 추가.
        //                                : 알고리즘(Argon2)는 동일하고, 그걸 구현한 라이브러리만 다른 것!
    }
    //보안강도 Argon2(최신표준) > BCrypt

} //class
