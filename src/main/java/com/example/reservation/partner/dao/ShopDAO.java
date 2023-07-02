package com.example.reservation.partner.dao;

import com.example.reservation.member.data.dto.MemberDTO;
import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;

import java.util.List;

public interface ShopDAO {

     List<ShopDTO> findShopListByUserId(String userId);

    void saveShop(ShopEntity shopEntity, String userId);

    List<ShopEntity> findReservationListByUserId(String userId);

    void updateStatus(String status, Long reserId);

    MemberDTO findByReservationId(Long resId);
}
