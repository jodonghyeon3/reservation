package com.example.reservation.partner.service.impl;

import com.example.reservation.partner.dao.ShopDAO;
import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.repository.ShopRepository;
import com.example.reservation.partner.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final ShopDAO shopDAO;

    @Override
    public void saveShop(ShopDTO shopDTO, String userId) {

        shopDAO.saveShop(ShopEntity.builder()
                .shopName(shopDTO.getShopName())
                .description(shopDTO.getDescription())
                .address(shopDTO.getAddress())
                .lng(shopDTO.getLng())
                .lat(shopDTO.getLat())
                .build(), userId);
    }

    @Override
    public List<ShopDTO> findByUserId(String userId) {
        return shopDAO.findShopListByUserId(userId);

    }
}
