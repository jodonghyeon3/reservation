package com.example.reservation.member.service;

import com.example.reservation.partner.data.dto.ShopDTO;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {

    List<ShopDTO> shopList();

    ShopDTO shopInfo(String shopName);

    void reservation(String shopName, LocalDate date, String userId);
}
