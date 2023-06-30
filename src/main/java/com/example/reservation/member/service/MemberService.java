package com.example.reservation.member.service;

import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.partner.data.dto.ShopDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MemberService {

    List<ShopDTO> shopList();

    ShopDTO shopInfo(String shopName);

    void reservation(String shopName, LocalDateTime date, String userId);

    List<ReservationEntity> findByUserId(String userId);

    void saveReview(Long resId, String comments, Long star);

    List<ShopDTO> sortShopList(String sort);

    void checkIn(Long reserId);
}
