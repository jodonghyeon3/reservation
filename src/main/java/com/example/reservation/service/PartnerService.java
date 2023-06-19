package com.example.reservation.service;

import com.example.reservation.data.dto.ShopDTO;
import com.example.reservation.data.entity.ShopEntity;
import com.example.reservation.data.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final ShopRepository shopRepository;


    public List<ShopDTO> findAll() {
        List<ShopEntity> shopEntities = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for (ShopEntity shopEntity : shopEntities) {
            shopDTOList.add(ShopDTO.builder()
                    .shopName(shopEntity.getShopName())
                    .description(shopEntity.getDescription())
                    .address(shopEntity.getAddress())
                    .lng(shopEntity.getLng())
                    .lat(shopEntity.getLat())
                    .build());
        }
        return shopDTOList;
    }
}
