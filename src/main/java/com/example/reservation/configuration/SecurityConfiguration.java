package com.example.reservation.configuration;

import com.example.reservation.mainService.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final MainService mainService;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable(); // csrf 보호를 비활성화
        http.headers().frameOptions().sameOrigin();
        // X-Frame-Options 헤더를 "SAMEORIGIN"으로 구성하여 브라우저는 요청이 동일한 출처에서 오는 경우에만
        // 웹사이트가 iframe에 표시되도록 허용하여 클릭재킹 공격을 방지

        // 모든 사용자가 루트페이지와 회원가입 페이지 요청 가능
        http.authorizeRequests()
                .antMatchers( //
                        "/"
                        , "/save"
                )
                .permitAll();

        // ROLE_PARTNER 권한을 가지고 있는 사용자만 /partner/ 경로에 있는 모든페이지 요청가능
        http.authorizeRequests()
                .antMatchers("/partner/**") //
                .hasAuthority("ROLE_PARTNER");

        http.authorizeRequests()
                .antMatchers("/member/**") //
                .hasAuthority("ROLE_USER");

        // 로그인 페이지 지정 , 로그인 실패 및 성공시 핸들러 호출, 권한 설정
        http.formLogin()
                .loginPage("/login")
                .failureHandler(getFailureHandler())
                .successHandler(new LoginSuccessHandler()) // 로그인 성공시 핸들러호출
                .permitAll();

        http.logout()
                // 로그아웃을 실행할 페이지
                // 사용자가 요청한 요청정보를 확인하여 요청정보 url이 /logout으로 시작하는지 확인
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        // 권한이 없는 페이지에 들어갔을 경우에
        http.exceptionHandling()
                .accessDeniedPage("/error/denied");

        return http.build();
    }

    /*
    AuthenticationProvider
    - 인증을 담당하는 인터페이스이며 사용자의 인증정보를 확인하고 인증에 성공했을 때 인증 객체를 반환

    DaoAuthenticationProvider
    - 사용자 정보를 데이터베이스 등의 소스로부터 가져와 인증을 수행하는 구현체

    memberService를 통해 사용자 정보를 가져와 비밀번호 일치 여부를 확인하고 사용자의 인증정보를 반환

     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.mainService);
        provider.setPasswordEncoder(this.getPasswordEncoder());

        return provider;
    }


}


