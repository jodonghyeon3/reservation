package com.example.reservation.mainController;


import com.example.reservation.member.data.dto.MemberDTO;
import com.example.reservation.mainService.impl.MainServiceImpl;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MainServiceImpl mainService;

    @GetMapping("/save")
    public String saveGet() {
        return "/save";
    }

    @PostMapping("/save")
    public String savePost(Model model, MemberDTO memberDTO) {
        boolean result = mainService.save(memberDTO);
        if (result) {
            return "/login";
        }
        return "/save";
    }

    @RequestMapping("/login")
    public String loginGet() {
        return "/login";
    }
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "/error/denied";
    }




}
