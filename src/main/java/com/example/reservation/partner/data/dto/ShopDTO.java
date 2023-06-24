package com.example.reservation.partner.data.dto;

import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.partner.data.entity.ShopEntity;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ShopDTO {

    private String shopName;

    private String description;

    private String address;

    private Double lng;

    private Double lat;

    @ManyToOne
    @JoinColumn(name ="member_id")
    private MemberEntity memberEntity;

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
