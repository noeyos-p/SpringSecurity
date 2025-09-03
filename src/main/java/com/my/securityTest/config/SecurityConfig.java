package com.my.securityTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 비밀번호 암호 처리 기계 추가
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        // 누구나 다 볼 수 있게
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // 액세스가 거부됨 -> 어드민 인증을 받아야 들어올 수 있음
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                        // 위의 3개에서 안걸리면, 마지막줄 인증하라는 것이 뜸
                );
        // Login 요청 처리
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                        // 로그인 아이디와 패스워드를 가로채감
                        /* html -> loginProc로 받아서 유저 아이디와 패스워드를 통해
                         시큐리티 안에 로직을 통해 내부에서 확인하는 과정을 거침*/
                );
        http
                .csrf((auth) -> auth.disable());
        // csrf = 사이트 간의 요청을 위조
        /* 특정한 사이트에 로그인이 되어있는 상태를 탈취해서
        로그인 정보를 이용해서 가상의 다른 사이트로 끌어들임
        ex) 로봇이 아닙니다.
        위변조 방지를 요청을 함*/

        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        // 로그아웃 페이지에서 로그아웃이 정상적으로 되면 / 페이지로 돌아옴


        return http.build();
    }
}
