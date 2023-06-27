package com.example.reservation.partner.controller;

import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private final PartnerService partnerService;


    @RequestMapping("/main")
    public String main() {
        return "/partner/main";
    }

    @GetMapping("/shop/save")
    public String save() {
        return "/partner/shop/save";
    }

    @PostMapping("/shop/save")
    public String saveShopPost(ShopDTO shopDTO, Principal principal) {
        String userId = principal.getName();
        partnerService.saveShop(shopDTO, userId);
        return "/partner/main";
    }

    @GetMapping("/shop/list")
    public String listShop(Model model, Principal principal) {

        List<ShopDTO> shopList = partnerService.findByUserId(principal.getName());
        model.addAttribute("shopList", shopList);

        return "/partner/shop/list";
    }

    @GetMapping("/shop/reservation")
    public String reservationShop(Model model, Principal principal) {
        String userId = principal.getName();
        List<ShopEntity> shops = partnerService.findReservationByUserId(userId);

        model.addAttribute("shops", shops);
        return "/partner/shop/reservationList";
    }

    @PostMapping("/shop/reservationStatus")
    public String reservationUpdate(@RequestParam("status") String status, @RequestParam("reserId") Long reserId) {

        partnerService.updateStatus(status, reserId);

        return "redirect:/partner/main";
    }
}
