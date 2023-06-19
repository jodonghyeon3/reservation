//package com.example.reservation.config;
//
//import com.example.reservation.service.ManagerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final ManagerService managerService;
//
//    @Bean
//    PasswordEncoder getPasswordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    UserAuthenticationFailureHandler getFailureHandler() {
//        return new UserAuthenticationFailureHandler();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//        // 인증 없이도 들어갈 수 있는 페이지 설정
//        http.authorizeRequests()
//                .antMatchers(
//                        "/",
//                        "/member/register"
//                        , "/member/email-auth")
//                .permitAll();
//
//        // 로그인 페이지
//        http.formLogin()
//                .loginPage("/member/login") // 로그인 페이지 설정
//                .failureHandler(getFailureHandler()) // 실패했을 경우
//                .permitAll();
//
//        // 로그아웃
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //  로그아웃 페이지
//                .logoutSuccessUrl("/") // 로그아웃되면 루트로 이동한다
//                .invalidateHttpSession(true); // 세션초기화
//        super.configure(http);
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////
////
////        auth.userDetailsService(managerService)
////                .passwordEncoder(getPasswordEncoder());
////
////        super.configure(auth);
////    }
//
//
//}
