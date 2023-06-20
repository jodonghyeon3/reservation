package com.example.reservation.controller;

import com.example.reservation.data.dto.ShopDTO;
import com.example.reservation.service.MemberService;
import com.example.reservation.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;


    @GetMapping("/main")
    public String main() {
        return "/partner/main";
    }

    @GetMapping("/shop/save")
    public String save(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        return "/partner/shop/save";
    }

    @PostMapping("/shop/save")
    public String saveShopPost(ShopDTO shopDTO) {

        return "/partner/main";
    }

    @GetMapping("/shop/list")
    public String listShop(Model model) {
        List<ShopDTO> shopDTOList = partnerService.findAll();
        model.addAttribute("shopList", shopDTOList);

        return "/partner/shop/list";
    }
}
