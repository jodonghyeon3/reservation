package com.example.reservation.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // 로그인 성공했을떄 처리할 로직
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        List<String> roleNames = new ArrayList<>();

        authentication.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });

        if (roleNames.contains("ROLE_PARTNER")) {
            response.sendRedirect("/partner/main");
            return;
        }

        if (roleNames.contains("ROLE_USER")) {
            response.sendRedirect("/member/main");
            return;
        }

        response.sendRedirect("/");




    }
}

