package com.example.reservation.controller;

import com.example.reservation.data.dto.MemberDTO;
import com.example.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/save")
    public String saveGet() {
        return "/save";
    }

    @PostMapping("/save")
    public String savePost(Model model, MemberDTO memberDTO) {
        boolean result = memberService.save(memberDTO);
        if (result) {
            return "/login";
        }
        return "/save";
    }

    @GetMapping("/login")
    public String loginGet() {
        return "/login";
    }
    @PostMapping("/login")
    public String loginPost(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        // 여기서 파트너 1번일경우 매니저 페이지로 아니면 유저페이지
        if (loginResult != null) {
            session.setAttribute("loginUserId", loginResult.getUserId());
            // login 성공
            if (loginResult.getIsPartner() == 1) {
                return "/partner/main";
            } else {
                return "/user/main";
            }
        } else {
            // login 실패
            return "/login";
        }

    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }


}
