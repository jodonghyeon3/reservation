package com.example.reservation.partner.data.entity;

import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.partner.data.dto.ShopDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shop")
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(unique = true)
    private String shopName;

    @Column
    private String description;

    @Column
    private String address;

    @Column
    private Double lng;

    @Column
    private Double lat;

    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;


    public ShopDTO toDTO() {
        return ShopDTO.builder()
                .shopName(shopName)
                .description(description)
                .address(address)
                .lng(lng)
                .lat(lat)
                .build();
    }

}