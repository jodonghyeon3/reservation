package com.example.reservation.member.dao;

import com.example.reservation.partner.data.dto.ShopDTO;

import java.time.LocalDate;
import java.util.List;

public interface MemberDAO {

    void reservation(String shopName, LocalDate date, String userId);

    List<ShopDTO> shopListAll();

    ShopDTO shopInfo(String shopName);
}
