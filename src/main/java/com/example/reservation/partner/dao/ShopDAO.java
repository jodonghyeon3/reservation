package com.example.reservation.partner.dao;

import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;

import java.util.List;

public interface ShopDAO {

     List<ShopDTO> findShopListByUserId(String userId);

    void saveShop(ShopEntity shopEntity, String userId);
}
