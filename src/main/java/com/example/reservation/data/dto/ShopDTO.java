package com.example.reservation.data.dto;

import com.example.reservation.data.entity.ShopEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDTO {

    private String shopName;

    private String description;

    private String address;

    private Double lng;

    private Double lat;

    public ShopEntity shopEntity() {
        return ShopEntity.builder()
                .shopName(shopName)
                .description(description)
                .address(address)
                .lng(lng)
                .lat(lat)
                .build();
    }

}
