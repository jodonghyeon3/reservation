package com.example.reservation.member.controller;

import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.member.service.MemberService;
import com.example.reservation.partner.data.dto.ShopDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @RequestMapping("/main")
    public String main() {
        return "/member/main";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<ShopDTO> shopList = memberService.shopList();
        model.addAttribute("shopList", shopList);
        return "/member/list";
    }

    @PostMapping("/booking")
    public String shopReservationPage(@RequestParam("shopName") String shopName, Model model) {
        model.addAttribute("shopName", shopName);
        return "/member/booking";
    }

    @PostMapping("/reservation")
    public String makeReservation(@RequestParam("shopName") String shopName,
                                  @RequestParam("reservationTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date,
                                  Model model, Principal principal
    ) {
        // iso = DateTimeFormat.ISO.DATE
        String userId = principal.getName();
        System.out.println(shopName);
        memberService.reservation(shopName, date, userId);

        return "redirect:/member/main";
    }

    @GetMapping("/reservationList")
    public String reservationList(Principal principal, Model model) {
        String userId = principal.getName();
        List<ReservationEntity> byUserId = memberService.findByUserId(userId);
        model.addAttribute("reser", byUserId);
        return "/member/reservationList";
    }

    @PostMapping("/review")
    public String reviewPage(@RequestParam("reserId") Long reserId,
                             Model model) {
        model.addAttribute("reserId", reserId);
        return "/member/review";
    }

    @PostMapping("/checkin")
    public String checkIn(@RequestParam("reserId") Long reserId,
                             Model model) {
        memberService.checkIn(reserId);
        return "redirect:/member/reservationList";
    }

    @PostMapping("/reviewComplete")
    public String reviewComplete(@RequestParam("reserId") Long resId,
                                 @RequestParam("comments") String comments,
                                 @RequestParam("star") Long star) {

        memberService.saveReview(resId, comments, star);
        return "/member/main";
    }

    @PostMapping("/listSort")
    public String listSort(@RequestParam("sort") String sort,
                           Model model) {
//        System.out.println(sort);
        List<ShopDTO> shopDTOList = memberService.sortShopList(sort);
        model.addAttribute("shopList", shopDTOList);
        return "/member/list";
    }


}
