package com.example.reservation.partner.dao.impl;

import com.example.reservation.partner.dao.ShopDAO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopDAOImpl implements ShopDAO {

    private final ShopRepository shopRepository;

    @Override
    public void saveShop(ShopEntity shopEntity) {
        saveShop(shopEntity);
    }
}
