package com.example.reservation.partner.service;

import com.example.reservation.partner.data.dto.ShopDTO;

import java.util.List;

public interface PartnerService {
    void saveShop(ShopDTO shopDTO, String userId);

    List<ShopDTO> findByUserId(String userId);
}
