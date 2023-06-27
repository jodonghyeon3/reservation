package com.example.reservation.partner.service;

import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;

import java.util.List;

public interface PartnerService {
    void saveShop(ShopDTO shopDTO, String userId);

    List<ShopDTO> findByUserId(String userId);

    List<ShopEntity> findReservationByUserId(String userId);

    void updateStatus(String status, Long reserId);
}
