package com.example.jennie.semiprojectv1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    //SecurityFilterChain :  시큐리티에서 적용할 보안규칙들을 필터로 구현해놓음
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF 필터 끔
                    .userDetailsService(userDetailsService)
                    .authorizeRequests() // URL 기반 인가 설정
                    .antMatchers("/member/logout", "/member/myinfo", "/board/list").hasRole("USER")  // USER 권한 사용자만 접근 가능
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/logout").authenticated() // 인증받은 사용자만 접근 가능
                    .antMatchers("/**","/member/**").permitAll() // 인증/인가 여부와 상관없이 접근 가능
                .and()
                .formLogin()
                    .loginPage("/member/login")    // 커스텀 로그인 페이지 경로
                    //.loginProcessingUrl("/api/login")  // 로그인 처리 URL
                    .usernameParameter("userid")   // 아이디 매개변수 지정
                    .passwordParameter("passwd")   // 비밀번호 매개변수 지정
                    .defaultSuccessUrl("/member/myinfo")    // 로그인 성공시 리다이렉트 URL
                    .failureUrl("/login?error=true")    // 로그인 실패시 리다이렉트 URL
                    .permitAll()
                    .and()
                .logout()// 로그아웃 설정
                    .logoutRequestMatcher(new AntPathRequestMatcher("member/logout"))
                    .logoutSuccessUrl("/") // 로그아웃 성공후 리다이렉트 될 URL
                    .invalidateHttpSession(true) // 세션 무효화
                    .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){   // 비밀번호 암호화에 사용할 인코더
        return new BCryptPasswordEncoder();
    }


}
