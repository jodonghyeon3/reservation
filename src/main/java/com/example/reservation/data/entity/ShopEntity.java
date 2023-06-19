package com.example.reservation.data.entity;

import com.example.reservation.data.dto.MemberDTO;
import com.example.reservation.data.dto.ShopDTO;
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
